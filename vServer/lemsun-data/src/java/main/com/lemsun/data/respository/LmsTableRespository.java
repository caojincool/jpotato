package com.lemsun.data.respository;

import com.lemsun.core.*;
import com.lemsun.core.repository.AbstractLocalFsRepository;
import com.lemsun.core.repository.ResourceAttachFileRepository;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.data.connection.ConnectionException;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.connection.DbManager;
import com.lemsun.data.connection.LemsunDataSource;
import com.lemsun.data.lmstable.*;
import com.mongodb.*;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 *
 * 五代数据表组件的数据库操作类
 * User: 宗旭东
 * Date: 13-6-6
 * Time: 下午4:27
 */

@Repository
public class LmsTableRespository extends AbstractLocalFsRepository {

    private ResourceRepository resourceRepository;
    private DbManager dbManager;
    private static final Logger log = LoggerFactory.getLogger(LmsTableRespository.class);
    private static final String FORM_SERIAL_NUMBER="FormSerialNumber";//单据号存储集合


    @Autowired
    public LmsTableRespository(ResourceRepository resourceRepository,
                               MongoTemplate template,
                               DbManager dbManager,
                               GridFsOperations operations) {
        super(template, operations);
        this.resourceRepository = resourceRepository;
        this.dbManager = dbManager;
    }


    /**
     * 保存一个表组
     */
    public void saveTableGroup(Table5GroupResource groupResource) throws Exception {
        resourceRepository.update(groupResource);
//        resourceRepository.create(groupResource);
    }

    /**
     * 保持表格组件
     */
    public void saveTable(Table5Resource tableResource) throws Exception {


        if(tableResource.getParent() == null || StringUtils.isEmpty(tableResource.getParent().getPid()))
        {
            throw new LemsunException("不能保存一个没有表组的表格组件" + tableResource.getName(), "002", LemsunException.ResourceException);
        }


        if(tableResource.getColumns() == null || tableResource.getColumns().size() == 0)
        {
            throw new Exception("数据表必须要有物理列");
        }


        resourceRepository.create(tableResource);
    }

    /**
     * 更新表格数据
     * @param tableResource
     */
      public void updateTable(Table5Resource tableResource){
          resourceRepository.update(tableResource);
      }
    /**
     * 保存表格设置的皮肤信息
     * @param table
     * @param face
     */
    public void saveOrUpdateTableFace(Table5Resource table, String face)
    {
        resourceRepository.getAttachFileRepository().updateAttach(ResourceAttachFileRepository.getSettingName(table.getPid()), face);
    }


    /**
     * 更新表格
     * @param table
     * @param face
     * @param dataModel
     * @throws Exception
     */
    public void updateTableResource(Table5Resource table, TableFace face, DataModel dataModel) throws Exception
    {


        dataModel.setResource(table);

        getTemplate().remove(query(where("resource.$id").is(table.getId())), DataModel.class);

        getTemplate().save(dataModel);

        saveOrUpdateTableFace(table, face.toJson());

    }

    /**
     * 获取表格的皮肤信息
     * @param pid
     * @return
     */
    public String getTableFace(String pid)
    {
        try {
            GridFSDBFile file = resourceRepository.getAttachFileRepository().getSettingFile(pid);

            if(file != null)
            {
                return IOUtils.toString(file.getInputStream(), "UTF-8");
            }
            else
            {
                return null;
            }


        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 清除一个表格组件
     * @param table
     */
    public void removeTable(Table5Resource table)
    {
        resourceRepository.remove(table.getPid());
    }

    /**
     * 清除表组的组件
     * @param groupResource
     */
    public void removeTableGroup(Table5GroupResource groupResource)
    {
        resourceRepository.remove(groupResource.getPid());
        for( Table5Resource  tables :groupResource.getTables()){
            this.removeTable(tables);
        }
    }


    public Table5Resource get(String pid) {

        final Table5Resource[] rc = { null };
        final MongoConverter converter = getTemplate().getConverter();

        getTemplate().executeQuery(query(where("pid").is(pid)), ResourceRepository.ResourceCollectionName, new DocumentCallbackHandler() {
            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                if(dbObject == null) return;

                String type = (String) dbObject.get("_class");
                try {
                    Class<?> cls = Class.forName(type);
                    Object resource = converter.read(cls, dbObject);
                    Table5Resource tr = null;

                    if (resource instanceof IResource) {
                        tr = (Table5Resource) resource;
                    } else {
                        throw new ClassCastException("获取五代表格组件, 类型没有实现 IResource 接口: " + cls.toString());
                    }

                    String parentPid = tr.getParentPid();

                    Table5GroupResource groupResource = getGroup(parentPid);


                    DBRef dbConfig = (DBRef) dbObject.get("dbConfig");
                    LemsunDataSource dataSource = null;
                    try {
                        dataSource = dbManager.getDataSource((ObjectId) dbConfig.getId());

                    } catch (ConnectionException ignored) {

                    }

                    tr.setParent(groupResource);
                    tr.setDbConfig(dataSource.getConfigResource());

                    rc[0] = tr;

                } catch (ClassNotFoundException e) {
                    if(log.isErrorEnabled())
                    log.error("组件类型不能实例:{}, {}", type, e);
                }

            }
        });


        return rc[0];
    }

    /**
     * 获取表格打包组件
     * @param pid
     * @return
     */
    public Table5GroupResource getGroup(String pid) {
        return getTemplate().findOne(query(where("pid").is(pid)), Table5GroupResource.class, ResourceRepository.ResourceCollectionName);
    }

    /**
     * 获取组件下的全部表格信息
     * @param pid
     * @return
     */
    public LemsunResource selectCurrentResource(String pid, Date date) {

        LemsunResource parentResource = resourceRepository.getBaseResource(pid);

        String cate = parentResource.getCategory();

        if(StringUtils.equals(cate, BaseCategory.DBTABEL_GROUP_5.getCategory())) {

            Table5GroupResource groupResource = getGroup(pid);
            String code = TableCategory.createCode(groupResource.getCodeFormat(), date);

            List<LemsunResource> res = getTemplate().find(query(where("parentPid").is(pid).and("code").is(code)),
                                LemsunResource.class,
                                ResourceRepository.ResourceCollectionName);

            if(res != null && res.size() > 0) {
                return res.get(0);
            }
            else
            {
                return parentResource;
            }

        }
        else {

        }
        return parentResource;
    }

    public List<Table5Resource> getTable5Resources(String parentPid) throws Exception {

        Query query = new Query();
        query.addCriteria(Criteria.where("_class").is("com.lemsun.data.lmstable.Table5Resource"));
        query.addCriteria(Criteria.where("parentPid").is(parentPid));
        return getTemplate().find(query,Table5Resource.class,ResourceRepository.ResourceCollectionName);
    }
    public List<Table5Resource> getTable5Resources(String parentPid,String adate) throws Exception {

        Query query = new Query();
        query.addCriteria(Criteria.where("_class").is("com.lemsun.data.lmstable.Table5Resource"));
        query.addCriteria(Criteria.where("parentPid").is(parentPid));
        query.addCriteria(Criteria.where("code").gte(adate));
        return getTemplate().find(query,Table5Resource.class,ResourceRepository.ResourceCollectionName);
    }
    public List<Table5Resource> queryCurrentResource(String parentPid,String adate) throws Exception {

        Query query = new Query();
        query.addCriteria(Criteria.where("_class").is("com.lemsun.data.lmstable.Table5Resource"));
        query.addCriteria(Criteria.where("parentPid").is(parentPid));
        query.addCriteria(Criteria.where("code").is(adate));
        return getTemplate().find(query,Table5Resource.class,ResourceRepository.ResourceCollectionName);
    }
    public List<Table5Resource> getTable5Resources(DbConfigResource dbConfigResource) throws Exception {

        Query query = new Query();
        query.addCriteria(Criteria.where("_class").is("com.lemsun.data.lmstable.Table5Resource"));
        query.addCriteria(Criteria.where("dbConfig.$id").is(dbConfigResource.getId()));
        return getTemplate().find(query,Table5Resource.class,ResourceRepository.ResourceCollectionName);
    }
    public List<Table5GroupResource> getTable5GroupResource(DbConfigResource dbConfigResource) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("_class").is("com.lemsun.data.lmstable.Table5GroupResource"));
        query.addCriteria(Criteria.where("dbConfig.$id").is(dbConfigResource.getId()));
        return getTemplate().find(query,Table5GroupResource.class,ResourceRepository.ResourceCollectionName);
    }
    public Page<Table5GroupResource> getPageing(AbstractPageRequest query){
        return resourceRepository.getPagging(query,Table5GroupResource.class);
    }
    /**
     * 获取组件编码为PID的单据号
     *
     * @param pid
     * @return 序列值
     */
    public synchronized long getLastFormNo(String pid, String adate) {

        long index = 0;
        MongoTemplate template = getTemplate();
        BasicDBObject row = new BasicDBObject();
        row.put("pid", pid);
        row.put("adate", adate);
        String valueName = "value";

        if (!template.collectionExists(FORM_SERIAL_NUMBER)) {
            DBCollection coll = template.createCollection(FORM_SERIAL_NUMBER);
            row.put(valueName, index);
            coll.save(row);
        } else {
            DBCollection coll = template.getCollection(FORM_SERIAL_NUMBER);
            DBObject tempRow = coll.findOne(row);
            if(tempRow==null){
                row.put("value",index);
                coll.save(row);
            }else{
                index = (long) tempRow.get(valueName);
            }
        }
        return index;
    }

    /**
     * 更新组件编码为PID的单据号
     *
     * @param adate 操作日期
     * @return 序列值
     */
    public synchronized void updateLastFormNo(String pid, String adate, long no) {

        MongoTemplate template = getTemplate();
        BasicDBObject row = new BasicDBObject();
        row.put("pid", pid);
        row.put("adate", adate);
        String valueName = "value";
        DBCollection coll = template.getCollection(FORM_SERIAL_NUMBER);
        DBObject tempRow = coll.findOne(row);
        tempRow.put(valueName, no);
        coll.save(tempRow);

    }
    /**
     * 更新组件编码为PID的单据号
     *
     * @param
     * @return 序列值
     */
    public synchronized void removeLastFormNo(String pid) {

        MongoTemplate template = getTemplate();
        BasicDBObject row = new BasicDBObject();
        row.put("pid", pid);
        DBCollection coll = template.getCollection(FORM_SERIAL_NUMBER);
        coll.remove(row);


    }
}
