package com.lemsun.data.service.impl;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.Host;
import com.lemsun.core.events.ResourceEvent;
import com.lemsun.core.service.ICodecService;
import com.lemsun.core.util.ResourceUtil;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.connection.DbManager;
import com.lemsun.data.connection.LemsunDataSource;
import com.lemsun.data.respository.DbConfigRespository;
import com.lemsun.data.respository.TableGroupResourceRepository;
import com.lemsun.data.service.IDbProcess;
import com.lemsun.data.service.IDbService;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.ldbc.DbCategory;
import com.lemsun.ldbc.TableData;
import com.lemsun.ldbc.service.ISqlOperaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午2:03
 */
@Service
public class DbServiceImpl implements IDbService {

    private static final Logger log = LoggerFactory.getLogger(DbServiceImpl.class);
    private DbManager dbManager;
    private DbConfigRespository configRespository;
    private TableGroupResourceRepository tableGroupResourceRepository;
    private ICodecService codecService;
    private Collection<IDbProcess> processes;
    private HashMap<DbCategory, ISqlOperaterService> sqlOperaterServiceHashMap = new HashMap<>();
    @Autowired
    public DbServiceImpl(DbManager dbManager,
                         DbConfigRespository configRespository,
                         TableGroupResourceRepository tableGroupResourceRepository,
                         Collection<ISqlOperaterService> sqlOperaterServices,
                         ICodecService codecService,
                         Collection<IDbProcess> processes) {

        this.dbManager = dbManager;

        this.configRespository = configRespository;
        this.tableGroupResourceRepository = tableGroupResourceRepository;

        this.codecService = codecService;
        this.processes = processes;

        for (ISqlOperaterService service : sqlOperaterServices) {
            sqlOperaterServiceHashMap.put(service.getSupportCategory(), service);
        }

        reloadDb();
    }


    public void reloadDb() {
        dbManager.clear();

        List<DbConfigResource> dbResources = configRespository.getAll();
        if (log.isDebugEnabled()) log.debug("初始Db");
        for (DbConfigResource d : dbResources) {
            d.setPassword(codecService.decode(d.getPassword()));
            dbManager.addDbConnection(d);
        }
        if (log.isDebugEnabled()) log.debug("初始Db完成");
    }


    /**
     * @return 返回数据库管理
     */
    @Override
    public DbManager getDbManager() {
        return dbManager;
    }


    /**
     * 增加一个数据库配置
     *
     * @param configResource 数据库配置
     * @throws Exception 增加出错
     */
    @Override
    public void addConfig(DbConfigResource configResource) throws Exception {
        String password=configResource.getPassword();
        configResource.setPassword(codecService.encode(configResource.getPassword()));
        configRespository.save(configResource);
        configResource.setPassword(password);
        dbManager.addDbConnection(configResource);
    }

    /**
     * 修改数据连接资源
     *
     * @param configResource DbConfigResource
     * @throws Exception
     */
    @Override
    public void updateConfig(DbConfigResource configResource) throws Exception {
        String password=configResource.getPassword();
        configResource.setPassword(codecService.encode(configResource.getPassword()));
        configRespository.update(configResource);
        configResource.setPassword(password);
        dbManager.addDbConnection(configResource);
    }


    @Override
    public String[] getDbNames(DbConfigResource resource) throws Exception {

        try {
            for (IDbProcess p : processes) {
                if (p.getCategory() == resource.getDbCategory()) {
                    return p.getDatabaseNames(resource);
                }
            }

            throw new Exception("没有对应的数据库实现");
        } catch (Exception ex) {
            throw new Exception("数据库访问出现异常: " + ex.getMessage());
        }

    }

    @Override
    public TableData select(String pid, String sql) throws Exception {

        LemsunDataSource dataSource = dbManager.getDataSource(pid);

        if (dataSource == null) {
            throw new Exception("没有对应的数据库 :" + pid);
        }

        DbCategory category = dataSource.getConfigResource().getDbCategory();

        if (!sqlOperaterServiceHashMap.containsKey(category)) {
            throw new Exception("没有对应数据库的执行类 :" + category.getName());
        }


        return sqlOperaterServiceHashMap.get(category).select(dataSource, sql);

    }

    @Override
    public void execute(String pid, String sql) throws Exception {
        LemsunDataSource dataSource = dbManager.getDataSource(pid);

        if (dataSource == null)
            throw new Exception("没有找到对应的数据源组件:" + pid);

        DbCategory category = dataSource.getConfigResource().getDbCategory();
        if (!sqlOperaterServiceHashMap.containsKey(category))
            throw new Exception("没有对应数据库的执行类 :" + category.getName());

        sqlOperaterServiceHashMap.get(category).execute(dataSource, sql);
    }


    /**
     * 根据PID获得一个数据库配置
     */
    @Override
    public DbConfigResource getDbconfigResource(String pid) {
        return configRespository.getDbconfigResource(pid);
    }

    @Override
    public List<DbConfigResource> getAll() {
        Collection<LemsunDataSource> lms = dbManager.getAllDataSource();
        List<DbConfigResource> res = new ArrayList<>(lms.size());
        for (LemsunDataSource d : lms)
            res.add(d.getConfigResource());
        return res;
    }

    /**
     * 根据PID获得一个表组
     */
    public TableGroupResource getTableGroupResourceByPid(String pid) {
        return tableGroupResourceRepository.getTableGroupResourceByPid(pid);
    }

    /**
     * 1、获取在数据库上创建表或表组
     * 2、处理应用关系
     * 3、删除数据库配置
     * 4、更新数据库连接缓存
     * @param pid
     * @throws Exception
     */
    @Override
    public void deleteResource(String pid) throws Exception {
        if (!ResourceUtil.isRecourcePid(pid))
            throw new Exception("编码不规范");
        //获取组件对象
        DbConfigResource resource = getDbconfigResource(pid);
        ApplicationContext context = Host.getInstance().getContext();
        try
        {

            //处理引用关系
            context.publishEvent(new ResourceEvent(this, resource, ResourceEvent.Action.BefreDelete));
            //移除本地的MonogoDB对象
            configRespository.remove(resource);
            //清理缓存
            dbManager.removeDbConnection(resource);
        }
        catch (Exception ex) {
            if(log.isErrorEnabled())
            log.error("删除组件失败:"+ex.getMessage());
        }
    }

    @Override
    public Page<DbConfigResource> getPageing(AbstractPageRequest query) {
        return configRespository.getPageing(query);
    }
}
