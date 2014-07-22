package com.lemsun.data.service.impl;

import com.lemsun.core.IAccount;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.repository.ResourcePrimaryRepository;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.core.service.IAccountCoreService;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.connection.DbManager;
import com.lemsun.data.connection.TableSet;
import com.lemsun.data.respository.TableGroupResourceRepository;
import com.lemsun.data.respository.TableResourceRepository;
import com.lemsun.data.service.IDbProcess;
import com.lemsun.data.service.ITableService;
import com.lemsun.data.tables.TableDataQuery;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.data.tables.TableResource;
import com.lemsun.data.viproject.*;
import com.lemsun.ldbc.DbCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 表格资源挂载创建
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午4:16
 */
@Service
@Deprecated
public class TableServiceImpl implements ITableService {

    private TableResourceRepository tableResourceRepository;
    private ResourceRepository resourceRepository;
    private TableGroupResourceRepository groupResourceRepository;
    private Map<DbCategory, IDbProcess> processMap = new HashMap<>();
    private DbManager dbManager;
    private IFourTableGroupService fourTableGroupService;
    private IFourIndexService fourIndexService;
    private IFourAccountService fourAccountService;
    private IImportTempService importTempService;
    private IAccountCoreService accountService;
    private ResourcePrimaryRepository primaryRepository;

    @Autowired
    public TableServiceImpl(TableResourceRepository tableResourceRepository,
                            ResourceRepository resourceRepository,
                            TableGroupResourceRepository groupResourceRepository,
                            ResourcePrimaryRepository primaryRepository,
                            DbManager dbManager,
                            Collection<IDbProcess> processes,
                            IFourTableGroupService fourTableGroupService,
                            IFourIndexService fourIndexService,
                            IFourAccountService fourAccountService,
                            IImportTempService importTempService,
                            IAccountCoreService accountService) {
        this.tableResourceRepository = tableResourceRepository;
        this.resourceRepository = resourceRepository;
        this.groupResourceRepository = groupResourceRepository;
        this.dbManager = dbManager;
        this.fourTableGroupService = fourTableGroupService;
        this.fourIndexService = fourIndexService;
        this.fourAccountService = fourAccountService;
        this.importTempService = importTempService;
        this.accountService = accountService;
        this.primaryRepository = primaryRepository;
        for (IDbProcess p : processes) {
            processMap.put(p.getCategory(), p);
        }
    }

    /**
     * 在数据库初始化数据资源, 在提交给各自的数据库执行类前. 进行表格的资源初始化
     *
     * @param resource 数据资源
     * @param owner
     * @throws Exception
     */
    @Override
    public void initDbTable(TableResource resource, IAccount owner) throws Exception {


        DbCategory category = resource.getDbConfig().getDbCategory();

        //获取数据源的类型
        processMap.get(category).initTable(resource, owner);
    }

    @Override
    public void createTable(TableResource resource, IAccount owner) throws Exception {

        resource.setCreateUser(owner.getAccount());
        try {

            initDbTable(resource, owner);

            tableResourceRepository.save(resource);
            //获取数据源的类型
            DbCategory category = resource.getDbConfig().getDbCategory();
            processMap.get(category).createTable(resource, owner);

        } catch (Exception ex) {
            tableResourceRepository.getResourceRepository().remove(resource.getPid());
            throw ex;
        }
    }

    @Override
    public void linkTable(String parentPid, String code) throws Exception {


    }


    public void initTableGroup(TableGroupResource resource, IAccount owner) throws Exception {
        DbCategory category = resource.getDbConfig().getDbCategory();
        processMap.get(category).initTableGroup(resource, owner);
    }

    @Override
    public void createTableGroup(TableGroupResource resource) throws Exception {

        IAccount owner = accountService.getCurrentAccountManager().getAccount();

        resource.setPid(primaryRepository.generator(resource));

        //初始化表组
        initTableGroup(resource, owner);

        //调用平台创建表格�
        DbCategory category = resource.getDbConfig().getDbCategory();
        processMap.get(category).createTableGroup(resource, owner);

        //int index = 1;

        //内部保存表格组
        groupResourceRepository.save(resource);

        //循环创建子表组件
        for (TableResource table : resource.getTables()) {
            //table.setPid(resource.getPid() + "." + Integer.toString(index));
            table.setPid(primaryRepository.generator(table));
            table.setParentPid(resource.getPid());
            createTable(table, owner);
        }
    }

    @Override
    public void initTableGroup(TableGroupResource resource) throws Exception {
        IAccount owner = accountService.getCurrentAccountManager().getAccount();
        //调用平台创建表格�
        DbCategory category = resource.getDbConfig().getDbCategory();
        processMap.get(category).initTableGroup(resource, owner);
    }

    @Override
    public TableResource get(String pid) {
        return tableResourceRepository.get(pid);
    }

    /**
     * 根据组件编码获取表组信息
     *
     * @param pid 组件编码
     * @return 表组组件
     */
    @Override
    public TableGroupResource getTableGroupResource(String pid) {
        TableGroupResource tableGroupResource = tableResourceRepository.getTableGroupResourceByPid(pid);
        return tableGroupResource;
    }

    @Override
    public TableResource getSimple(String pid) {
        TableResource resource = tableResourceRepository.getByQuery(pid);

        if (resource == null) {
            LemsunResource currentResource = getCurrentTable(pid);
            if (currentResource != null) {
                resource = tableResourceRepository.getByQuery(pid);
            }
        }

        return resource;

    }

    @Override
    public TableSet getTableData(TableDataQuery query) throws Exception {

        //query.setDate(DateUtils.parseDate("20121201", "yyyyMMdd"));s
        TableResource resource = tableResourceRepository.getByQuery(query.getPid());

        return tableResourceRepository.getResourceData(query, resource);
    }

    @Override
    public void deleteTableGroup(String pid) throws Exception {
        TableGroupResource resource = groupResourceRepository.getResourceRepository().get(pid, TableGroupResource.class);
        if (resource == null)
            throw new Exception("没有表格组的组件存在. :" + pid);

        groupResourceRepository.remove(pid);
    }

    @Override
    public Collection<TableImportInfo> importTableGroups(String[] codes, DbConfigResource dbResource, HttpServletRequest request) throws Exception {
        List<TableImportInfo> tbinfos = new ArrayList<>();
        importTempService.Init();
        for (String code : codes) {
            if (!StringUtils.isEmpty(code)) {
                TableGroupResource tableGroupResource = getFourByCode(dbResource, code); //在本地库中查找是否有该组
                if (tableGroupResource != null) {
                    //有删除   (更新)
                    resourceRepository.remove(tableGroupResource.getPid());
                }
                try {
                    FourTableGroup fourTableGroup = fourTableGroupService.getVinavigateBycode(getJdbcTemplate(dbResource), code);//4代库中找到表组
                    if (fourTableGroup != null) {
                        tableGroupResource = new TableGroupResource(fourTableGroup.getName(), fourTableGroup.getCode(), dbResource);
                        tableGroupResource.setVersion(4);
                        tableGroupResource.setParentPid(dbResource.getPid());
                        tableGroupResource.setDbConfig(dbResource);
                        createTableGroup(tableGroupResource);
                        //得到导入成功的组件的原始目录树结构
                        importTempService.addFourTableGroup(getJdbcTemplate(dbResource), fourTableGroup.getCode(), tableGroupResource.getName(), tableGroupResource.getPid());
                        AddTableGroupMes(tbinfos, fourTableGroup.getCode(), "导入成功", true);
                    } else {
                        AddTableGroupMes(tbinfos, code, "没有找到要导入的表组", false);
                    }
                } catch (Exception ex) {
                    AddTableGroupMes(tbinfos, code, StringUtils.isEmpty(ex.getMessage()) ? "导入出错" : ex.getMessage(), false);
                }
            }
        }
        //将原始目录树结构保存进SESSION
        request.getSession().setAttribute("importTempModel", importTempService.getModels());
        return tbinfos;
    }

    private void AddTableGroupMes(List<TableImportInfo> tableImportInfos, String code, String msg, Boolean issuccess) {
        TableImportInfo tableImportInfo = new TableImportInfo();
        tableImportInfo.setCode(code);
        tableImportInfo.setMsg(msg);
        tableImportInfo.setSuccess(issuccess);
        tableImportInfos.add(tableImportInfo);
    }

    /**
     * 导入所有的表组
     *
     * @param dbResource
     * @return
     */
    @Override
    public Collection<TableImportInfo> importTableGroups(DbConfigResource dbResource) {
        List<TableImportInfo> tbinfos = new ArrayList<>();
        List<FourTableGroup> fourTableGroups = fourTableGroupService.getAllNvNavigate(getJdbcTemplate(dbResource));
        for (FourTableGroup v : fourTableGroups) {
            try {
                if (StringUtils.isEmpty(v.getCode())) continue;
                TableGroupResource tableGroupResource = getFourByCode(dbResource, v.getCode());
                if (tableGroupResource != null) {
                    //有删除  (更新)
                    resourceRepository.remove(tableGroupResource.getPid());
                }
                try {
                    tableGroupResource = new TableGroupResource(v.getName(), v.getCode(), dbResource);
                    tableGroupResource.setVersion(4);
                    tableGroupResource.setParentPid(dbResource.getPid());
                    tableGroupResource.setDbConfig(dbResource);
                    createTableGroup(tableGroupResource);
                    AddTableGroupMes(tbinfos, v.getCode(), "导入成功", true);
                } catch (Exception ex) {
                    AddTableGroupMes(tbinfos, v.getCode(), "导入出错", false);
                }

            } catch (Exception ex) {
                AddTableGroupMes(tbinfos, v.getCode(), "导入出错", false);
            }
        }
        return tbinfos;
    }


    private JdbcTemplate getJdbcTemplate(DbConfigResource resource) {
        if (resource == null) return null;
        String name = resource.getName();
        return dbManager.getSpringTemplate(name);
    }

    @Override
    public TableGroupResource getFourByCode(DbConfigResource dbConfigResource, String code) {
        if (dbConfigResource == null) return null;
        return groupResourceRepository.getFourByCode(dbConfigResource.getId(), code);
    }

    @Override
    public FourIndex getFourIndexTree(DbConfigResource dbConfigResource, String accountCode, Date date) {
        return fourIndexService.getFourIndexTree(getJdbcTemplate(dbConfigResource), accountCode, date);
    }

    @Override
    public List<FourTableGroup> getFourTableGroupByIndex(DbConfigResource dbConfigResource, String accountCode, Date date) {
        return fourTableGroupService.getFourTableGroupByIndex(getJdbcTemplate(dbConfigResource), accountCode, date);
    }

    @Override
    public List<FourAccount> getFourAccount(DbConfigResource dbConfigResource, String accountname) {
        return fourAccountService.getFourAccount(getJdbcTemplate(dbConfigResource), accountname);
    }

    @Override
    public LemsunResource getCurrentTable(TableGroupResource resource) {
        return tableResourceRepository.getCurrentSimpleResource(resource);
    }

    @Override
    public LemsunResource getCurrentTable(String pid) {
        TableGroupResource resource = groupResourceRepository.getTableGroupResourceByPid(pid);
        return getCurrentTable(resource);
    }

    @Override
    public TableResource getCurrentTableResource(TableGroupResource resource) {
        TableResource temp = tableResourceRepository.getCurrentTableResource(resource);

        //屏蔽系统内置列

        return temp;
    }
}
