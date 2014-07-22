package com.lemsun.data.service.impl;

import com.lemsun.core.*;
import com.lemsun.core.events.ResourceEvent;
import com.lemsun.core.service.IAccountCoreService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.ITransactionManager;
import com.lemsun.core.util.Pid;
import com.lemsun.core.util.ResourceUtil;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.lmstable.*;
import com.lemsun.data.respository.LmsTableRespository;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.data.tables.TableDataQuery;
import com.lemsun.ldbc.*;
import com.lemsun.ldbc.service.ITableOperaterService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * 五代表格接口实现
 * User: 宗旭东
 * Date: 13-6-6
 * Time: 上午11:55
 */
@Service
public class LmsTableSerivceImpl implements ILmsTableService{
    private static final Logger log = LoggerFactory.getLogger(LmsTableSerivceImpl.class);
    private Map<DbCategory, ITableOperaterService> tableServiceMap = new HashMap<>();
    private LmsTableRespository tableRespository;
    private IAccountCoreService accountService;
    private IResourceService resourceService;



    @Autowired
    public LmsTableSerivceImpl(Collection<ITableOperaterService> tableServices,
                               IAccountCoreService accountService,
                               LmsTableRespository tableRespository,
                               IResourceService resourceService) {

        this.tableRespository = tableRespository;
        this.accountService = accountService;
        this.resourceService = resourceService;

        for (ITableOperaterService service : tableServices) {
            tableServiceMap.put(service.getSupportCategory(), service);
        }


    }

    @Override
    public void create(Table5GroupResource groupResource) throws Exception {

        LemsunResource temp = resourceService.getBaseResource(groupResource.getPid());

        groupResource.setParentPid(groupResource.getDbConfig().getPid());

        groupResource.copyResource(temp);
        groupResource.setCreateTime(new Date());
        tableRespository.saveTableGroup(groupResource);


        List<Table5Resource> tables = groupResource.getTables();

        //没有子表信息将退出创建
        if(tables == null || tables.size() == 0) return;

        //获取子表的模板信息
        Table5Resource table = tables.get(0);

        Date adate = accountService.getCurrentAdate();

        //循环完整的日期进行表格切割

        Calendar c = Calendar.getInstance();
        c.setTime(adate);
        HashSet<String> codes = new HashSet<>();
        ArrayList<Table5Resource> cloneTables = new ArrayList<>();
        int days = c.getActualMaximum(Calendar.DAY_OF_YEAR);
        for(int i=1; i <= days; i++) {

            c.set(Calendar.DAY_OF_YEAR, i);
            Date tempDate = c.getTime();

            String code = TableCategory.createCode(table, tempDate);

            if(!codes.contains(code)) {
                codes.add(code);

                Table5Resource clone = (Table5Resource)table.clone();
                clone.setCode(code);
                clone.setDateTime(tempDate);
                cloneTables.add(clone);
            }
        }

        groupResource.setTables(cloneTables);

        //开始创建表格
        for (Table5Resource t : groupResource.getTables()) {
            table.setParent(groupResource);
            t.setCreateUser(groupResource.getCreateUser());
            t.setCreateTime(new Date());
            createTable(t);
        }
    }

    @Override
    public void createTable(Table5Resource table) throws Exception {


        DbConfigResource dbConfig = table.getDbConfig();

        try {
            if (!tableServiceMap.containsKey(dbConfig.getDbCategory())) {
                throw new Exception("没有支持的数据库类型实现, type:" + dbConfig.getDbCategory());
            }

            table.setDbtable(
                    table.getParent().getPid()
                            + "_" + table.getCode()
                            + "_" + (table.getCate() < 10 ? "0" : "") + Integer.toString(table.getCate()));
            ITableOperaterService serivce = tableServiceMap.get(table.getDbConfig().getDbCategory());
            serivce.createTableResource(table, dbConfig);
            tableRespository.saveTable(table);

            TableFace face = table.getFace();

            if (face != null)
                tableRespository.saveOrUpdateTableFace(table, face.toJson());
        } catch (Exception ex) {

            throw ex;
        }

    }

    @Override
    @Pid
    public Table5Resource getResource(String pid) {
        return tableRespository.get(pid);
    }

    /**
     * 根据某个表格组件获取表格对象
     *
     * @param lmsr 表格组件
     * @return 表格对象
     */
    @Override
    public Table5Resource getResource(LemsunResource lmsr) {
        return getResource(lmsr.getPid());
    }

    @Override @Pid
    public LemsunResource getCurrentResource(String pid) {

        return getCurrentResource(pid, accountService.getCurrentAdate());
    }

    @Override
    @Pid
    public LemsunResource getCurrentResource(String pid, Date date) {


        if(!ResourceUtil.isRecourcePid(pid)) {
            LemsunResource resource = resourceService.getByBusinessCode(pid);

            if(resource == null)
                throw ResourceException.ResourcePidisNull;

            pid = resource.getPid();
        }

        Table5GroupResource group = tableRespository.getGroup(pid);

        if (group != null) {
            return tableRespository.selectCurrentResource(group.getPid(), date);
        }
        return resourceService.getBaseResource(pid);
    }

    @Override
    public String getTableFace(String pid) {
        return tableRespository.getTableFace(pid);
    }

    @Override
    public long getDataCount(TableDataQuery query) {

        Table5Resource table5Resource = getResource(query.getPid());

        if (table5Resource == null) return -1;

        try {
            DbCategory dbCategory = table5Resource.getDbConfig().getDbCategory();

            if (!tableServiceMap.containsKey(dbCategory)) {
                throw new Exception("没有支持的数据库类型实现, type:" + table5Resource.getDbConfig().getDbCategory());
            }
            //TableQuery ldbcQuery = new TableQuery(table5Resource);
            query.setTableResource(table5Resource);
            return tableServiceMap.get(dbCategory).getQueryCount(query);
        } catch (Exception ex) {

            return -1;
        }
    }

    @Override
    @Pid
    public long getLastFormNo(String pid, String adate) {

        //pid=getGroupByChirdPid(pid);
        return tableRespository.getLastFormNo(pid, adate);
    }

    @Override
    @Pid
    public void updateLastFormNo(String pid, String adate, long no) {

        //pid=getGroupByChirdPid(pid);
        tableRespository.updateLastFormNo(pid, adate, no);
    }

    @Override
    @Pid
    public void removeLastFormNo(String pid) {
        pid=getGroupByChirdPid(pid);
        tableRespository.removeLastFormNo(pid);
    }

    @Override
    public boolean isExistTable(TableDataQuery query) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Table5Resource queryNearestTable(TableDataQuery query) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 需要进行修改 需要添加分页 单据不需要
     * 命名规则不为空需要执行 1 2 3 5步骤
     *  1、查询前首先根据操作日期和表组PID（如：年表 PID_N%_表类型（不足俩位补全前边加0））  判断表是否存在 如果存在跳过步骤2、3
        2、 查找最近年份表
        3、 执行创建表组件
        4、执行查询数据
        5、如果不存在 提示前台是否结转数据 如果选择是 开始复制数据

     * @param query 查询对象
     * @return
     * @throws Exception
     */
    @Override
    public TableData query(TableDataQuery query) throws Exception {

        Table5Resource table5Resource = getResource(query.getPid());
        try {
            DbCategory dbCategory = table5Resource.getDbConfig().getDbCategory();

            if (!tableServiceMap.containsKey(dbCategory)) {
                throw new Exception("没有支持的数据库类型实现, type:" + table5Resource.getDbConfig().getDbCategory());
            }
            //TableQuery ldbcQuery = new TableQuery(table5Resource);
            query.setTableResource(table5Resource);

            return tableServiceMap.get(dbCategory).query(query);
        } catch (Exception ex) {

            throw ex;
        }
    }

    @Override
    public void copyTable(String groupPid, String adate, int cate, boolean isCopyData) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public void saveData(String pid, TableData data) throws Exception {
        Table5Resource table = getResource(pid);

        if (table == null) {
            throw new Exception("给定的PID (" + pid + ") 找不到对应的表格组件");
        }

        DbCategory dbCategory = table.getDbConfig().getDbCategory();

        if (!tableServiceMap.containsKey(dbCategory)) {
            throw new Exception("没有支持的数据库类型实现, type:" + table.getDbConfig().getDbCategory());
        }

        if(data.isDifferentTable()) {
            TableData syncData = data.getSyncTable();
            Table5Resource syncReosurce = getResource(syncData.getTablePid());
            DbCategory dbCategorySync = table.getDbConfig().getDbCategory();
            tableServiceMap.get(dbCategorySync).saveData(syncReosurce, syncData);
        }
        else {
            data.joinSycnTable();
        }

        tableServiceMap.get(dbCategory).saveData(table,data);
    }



    @Override
    public void uploadColumnString(String pid, String column, String rowid, String content) throws Exception {


        InputStream stream = null;

        if(StringUtils.isNotEmpty(content))
        {
            stream = new ByteArrayInputStream(content.getBytes("UTF-8"));
        }
        uploadColumnString(pid, column, rowid, stream);

    }

    @Override
    public void uploadColumnString(String pid, String column, String rowid, InputStream content) throws Exception {
        Table5Resource table = getResource(pid);

        InputStream stream = content;

        DbCategory dbCategory = table.getDbConfig().getDbCategory();

        if(!tableServiceMap.containsKey(dbCategory))
        {
            throw new Exception("没有支持的数据库类型实现, type:" + table.getDbConfig().getDbCategory());
        }
        tableServiceMap.get(dbCategory).uploadTableColumn(table, column, rowid, stream);
    }

    @Override
    public String uploadColumnImage(String pid, String column, String rowid, String filename, String reamrk, InputStream stream) throws Exception {
        return uploadColumnFile(pid, column, rowid, filename, "image", reamrk, stream);
    }

    @Override
    public String uploadColumnFile(String pid, String column, String rowid, String filename, String type, String reamrk, InputStream stream) throws Exception {

        if(StringUtils.isEmpty(filename) || StringUtils.isEmpty(type))
        {
            throw new Exception("上传的文件名, 或者类型不能为空");
        }

        Table5Resource table = getResource(pid);

        DbCategory dbCategory = table.getDbConfig().getDbCategory();

        if(!tableServiceMap.containsKey(dbCategory))
        {
            throw new Exception("没有支持的数据库类型实现, type:" + table.getDbConfig().getDbCategory());
        }

        String fileid = tableServiceMap.get(dbCategory).uploadTableColumn(table, column, rowid, filename, type, stream, reamrk);


        return fileid;
    }

    @Override
    public String getColumnContext(String pid, String column, String rowid) throws Exception {
        Table5Resource table = getResource(pid);

        DbCategory dbCategory = table.getDbConfig().getDbCategory();

        if(!tableServiceMap.containsKey(dbCategory))
        {
            throw new Exception("没有支持的数据库类型实现, type:" + table.getDbConfig().getDbCategory());
        }
        return tableServiceMap.get(dbCategory).getColumnContext(table, column, rowid);
    }

    @Override
    public void getColumnFile(String pid, String column, String fileid, OutputStream stream) throws Exception {
        Table5Resource table = getResource(pid);

        DbCategory dbCategory = table.getDbConfig().getDbCategory();

        if(!tableServiceMap.containsKey(dbCategory))
        {
            throw new Exception("没有支持的数据库类型实现, type:" + table.getDbConfig().getDbCategory());
        }
        tableServiceMap.get(dbCategory).getColumnFile(table, column, fileid, stream);
    }

    @Override
    public void updateResource(String pid, TableFace face, DataModel dataModel) throws Exception {
         Table5Resource resource = getResource(pid);

        if(resource == null)
            throw new Exception("执行更新找不到更新表格");

        tableRespository.updateTableResource(resource, face, dataModel);

    }

    @Override
    public List<ColumnFileInfo> getColumnFileInfos(String pid, String column, String rowid) throws Exception {


        Table5Resource table = getResource(pid);

        DbCategory dbCategory = table.getDbConfig().getDbCategory();

        if(!tableServiceMap.containsKey(dbCategory))
        {
            throw new Exception("没有支持的数据库类型实现, type:" + table.getDbConfig().getDbCategory());
        }
        return tableServiceMap.get(dbCategory).getColumnFileInfos(table, column, rowid);

    }

    @Override
    public List<Table5Resource> getTable5Resources(String parentPid) throws Exception {
        return tableRespository.getTable5Resources(parentPid);
    }

    @Override
    public Table5GroupResource getGroup(String pid)throws Exception {
        Table5GroupResource table5GroupResource=tableRespository.getGroup(pid);
        List<Table5Resource>  tables=this.getTable5Resources(pid);
        table5GroupResource.setTables(tables);
        return table5GroupResource;
    }

    /**
     * 转换pid 到一个表组pid
     * @param pid
     * @return
     */
    private String getGroupByChirdPid(String pid)  {
        LemsunResource lms= resourceService.getBaseResource(pid);

        String c = lms.getCategory();

        if(BaseCategory.DBTABEL_5.equals(c) || BaseCategory.DBTABEL_4.equals(c)) {
            return lms.getParentPid();
        }
        else if(BaseCategory.DBTABEL_GROUP_4.equals(c) || BaseCategory.DBTABEL_GROUP_5.equals(c)) {
            return lms.getPid();
        }
        else {
            throw new LemsunException("提供的 PId" + pid + "不是一个基本的PID");
        }
    }

    @Override
    public void deleteTableGroup(String pid)  throws Exception{
        if (!ResourceUtil.isRecourcePid(pid))
            throw new Exception("编码不规范");
        //获取组件对象
        Table5GroupResource resource = getGroup(pid);
        try
        {
            deletePhysicalTable(resource);

            removeTableGroup(resource);
        }
        catch (Exception ex) {
            if(log.isErrorEnabled())
                log.error("删除组件失败:"+ex.getMessage());
            throw ex;
        }
    }

    /**
     * 删除表组不删除物理表
     * @param resource
     */
    private void removeTableGroup(Table5GroupResource resource) {
        ApplicationContext context = Host.getInstance().getContext();
        //处理引用关系
        context.publishEvent(new ResourceEvent(this, resource, ResourceEvent.Action.BefreDelete));
        //移除本地的MonogoDB对象
        tableRespository.removeTableGroup(resource);
    }

    /**
     * 删除表组下物理表
     * @param resource
     * @throws SQLException
     */
    private void deletePhysicalTable(Table5GroupResource resource) throws SQLException {
        DbCategory dbCategory = resource.getDbConfig().getDbCategory();

        //移除物理的数据表
        ITableOperaterService operaterService = tableServiceMap.get(dbCategory);
        operaterService.removeTableGroup(resource);
    }

    @Override
    public void deleteTable(String pid) throws Exception{
        if (!ResourceUtil.isRecourcePid(pid))
            throw new Exception("编码不规范");
        //获取组件对象
        Table5Resource resource = getResource(pid);
        ApplicationContext context = Host.getInstance().getContext();
        try
        {

            DbCategory dbCategory = resource.getDbConfig().getDbCategory();

            //移除物理的数据表
            ITableOperaterService operaterService = tableServiceMap.get(dbCategory);
            operaterService.removeTable(resource);
            //处理引用关系
            context.publishEvent(new ResourceEvent(this, resource, ResourceEvent.Action.BefreDelete));
            //移除本地的MonogoDB对象
            tableRespository.removeTable(resource);
        }
        catch (Exception ex) {
            if(log.isErrorEnabled())
                log.error("删除组件失败:"+ex.getMessage());
        }
    }

    @Override
    public List<Table5Resource> getTable5Resources(DbConfigResource dbConfigResource) throws Exception {
        return tableRespository.getTable5Resources(dbConfigResource);
    }

    @Override
    public List<Table5GroupResource> getTable5GroupResource(IDbConnectionConfig dbConfigResource) throws Exception {
        if(dbConfigResource instanceof DbConfigResource){
            return tableRespository.getTable5GroupResource((DbConfigResource)dbConfigResource);
        }else{
            throw new LemsunException("不支持改类型"+dbConfigResource.getClass()+"查询");
        }

    }



    @Override
    public Page<Table5GroupResource> getPageing(AbstractPageRequest query) {
        return tableRespository.getPageing(query);
    }

    @Override
    public List<Column> getResourceColumns(String pid, int cate) {
        List<Table5Resource> tbales = queryTable5ResourcesByGroupPid(pid, cate);
        Map<String,Column> map=new LinkedHashMap<>();
        if(tbales!=null){
            for(Table5Resource resource:tbales){
                List<Column> cols=resource.getColumns();
                for(Column col:cols){
                    Column value= map.get(col.getCol());
                    if(map.get(col.getCol())!=null){
                        value.setCategory(ColumnCategory.Unkonw);
                        // map.put(col.getCol(),value);
                    }else{
                        map.put(col.getCol(),col);
                    }
                }
            }
        }
        List<Column> columns= new ArrayList<>();
        columns.addAll(map.values());
        return columns;

    }

    /**
     * 查询表组下表
     * @param pid 表组pid
     * @param cate 查询条件类型 cate 获取类型 类型定义在当前的常量  GetResourceColumnAll, GetResourceColumnDown, GetResourceColumnCurrent
     * @return
     */
    private List<Table5Resource> queryTable5ResourcesByGroupPid(String pid, int cate) {
        List<Table5Resource> tbales = null;
        Table5GroupResource groupResource = tableRespository.getGroup(pid);
        try{
            if(StringUtils.isEmpty(groupResource.getCodeFormat())){//说明只有一张表
                tbales = tableRespository.getTable5Resources(pid);
            }else{
                Date adate = accountService.getCurrentAdate();
                String adates = TableCategory.createCode(groupResource.getCodeFormat(), adate);
                switch (cate){
                    case GetResourceColumnAll:
                        tbales = tableRespository.getTable5Resources(pid);
                        break;
                    case GetResourceColumnDown:
                        tbales = tableRespository.getTable5Resources(pid, adates);
                        break;
                    case GetResourceColumnCurrent:
                        tbales = tableRespository.queryCurrentResource(pid, adates);
                        break;
                }
            }

        }catch (Exception e){
            throw new LemsunException("查询表组下表的列的集合出错"+e.getMessage());
        }
        return tbales;
    }

    @Override
    public void updateResourceColumns(String pid, int cate, List<Column> columns) {
        List<Table5Resource> tables = queryTable5ResourcesByGroupPid(pid, cate);
        if(tables !=null ){
          for(Table5Resource table:tables){
              try {
                  Table5Resource resource=(Table5Resource)table.clone();
                  resource.setColumns(columns);
                  updateResource(resource, table);
              } catch (Exception e) {
                  if(log.isErrorEnabled())
                      log.error("表{}修改出错:"+e.getMessage(), table.getDbtable());
            }
          }
        }
    }




    /**
     * 1、获取老表格
     * 2、更新表结构和触发器
     * 3、更新table及样式
     * @param table
     */
    @Override
    public void updateResource(Table5Resource table) {
        Table5Resource oldResource = getResource(table.getPid());
        if(oldResource==null){
           throw  ResourceException.ResourceIsNull;
        }else{
            updateResource(table, oldResource);
        }
    }

    /**
     * 更新表格
     * @param table 新表格
     * @param oldResource 原来的
     */
    private void updateResource(Table5Resource table, Table5Resource oldResource) {
        DbConfigResource dbConfig = oldResource.getDbConfig();
        ITableOperaterService operaterService = tableServiceMap.get(dbConfig.getDbCategory());
        operaterService.updatePhysicsTable(table,oldResource);
        tableRespository.updateTable(table);
        TableFace face = table.getFace();
        if (face != null)
            tableRespository.saveOrUpdateTableFace(table, face.toJson());
    }
}
