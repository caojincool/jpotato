package com.lemsun.data.respository;

import com.lemsun.core.LemsunResource;
import com.lemsun.core.repository.AbstractLocalFsRepository;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.data.connection.*;
import com.lemsun.data.tables.*;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午4:22
 */
@Repository
@Deprecated
public class TableResourceRepository extends AbstractLocalFsRepository {

    private ResourceRepository resourceRepository;
    private DbManager dbManager;
    private ApplicationContext context;
    private static Logger log = LoggerFactory.getLogger(TableResourceRepository.class);

    @Autowired
    public TableResourceRepository(ResourceRepository resourceRepository,
                                   GridFsOperations gridFsOperations,
                                   MongoTemplate template,
                                   DbManager dbManager,
                                   ApplicationContext context) {
        super(template, gridFsOperations);
        this.resourceRepository = resourceRepository;
        this.dbManager = dbManager;
        this.context = context;
    }


    public void save(TableResource tableResource) throws Exception {
        //设置表格的日期检索格式
        resourceRepository.create(tableResource);
        if (tableResource.getControl() != null) {
            String xaml = tableResource.getControl().toXaml();
            if (StringUtils.isNotEmpty(xaml))
                this.updateResource(tableResource, xaml);
        }

    }


    public ResourceRepository getResourceRepository() {
        return resourceRepository;
    }

    public TableResource get(String pid) {
        return resourceRepository.get(pid, TableResource.class);
    }

    /**
     * 获取对象提供查询使用. 没有皮肤界面信息. 只有针对数据查询所需的必要信息
     */
    public TableResource getByQuery(final String pid) {
        return getTemplate().execute(ResourceRepository.ResourceCollectionName, new CollectionCallback<TableResource>() {
            @Override
            public TableResource doInCollection(DBCollection collection) throws MongoException, DataAccessException {
                DBObject obj = collection.findOne(query(where("pid").is(pid)).getQueryObject(), new Field().exclude("face").getFieldsObject());


                if (obj == null)
                    return null;

                DBRef dbConfig = (DBRef) obj.get("dbConfig");
                LemsunDataSource dataSource = null;
                try {
                    dataSource = dbManager.getDataSource((ObjectId) dbConfig.getId());

                } catch (ConnectionException ignored) {

                }

                TableResource resource = getTemplate().getConverter().read(TableResource.class, obj);
                if (dataSource != null)
                    resource.setDbConfig(dataSource.getConfigResource());
                return resource;
            }
        });
    }

    /**
     * 获取表格的数据
     *
     * @param query    查询信息
     * @param resource 表格组件
     * @return 数据信息
     */
    public TableSet getResourceData(TableDataQuery query, TableResource resource) throws Exception {

        StringBuilder sql = new StringBuilder("select ");

        if (resource.getVersion() == TableGroupResource.VI) {
            for (TableColumn c : resource.getColumns()) {
                if (c.getCategory() != ColumnCategory.STREAM && !StringUtils.equals(c.getCol(), "A0")) {
                    sql.append(c.getCol()).append(",").append(" ");
                }
            }

            sql.append("B3")
                    .append(" from ")
                    .append(resource.getDbtable())
                    .append(createSQlFilter(query, resource));

        } else if (resource.getVersion() == TableGroupResource.V) {
            for (TableColumn c : resource.getColumns()) {
                if (c.getCategory() != ColumnCategory.STREAM && c.getCategory() != ColumnCategory.KEY) {
                    sql.append(c.getCol()).append(",").append(" ");
                }
            }

            sql.append("_key")
                    .append(" from ")
                    .append(resource.getDbtable())
                    .append(createSQlFilter(query, resource))
                    .append(" order by _code");
        }

        TableSet ts = null;

        try (Connection conn = dbManager.getConnection(resource.getDbConfig().getPid())) {

            Statement state = conn.createStatement();

            String pSql = sql.toString();

            if (log.isDebugEnabled())
                log.debug("开始一个表格组件的数据查询 : {}", pSql);

            //传播到特点的数据库执行
            DbPageStatementEvent event = new DbPageStatementEvent(this, null, StatementTypes.Query, state, resource, pSql, null);

            context.publishEvent(event);

            ResultSet rs = state.executeQuery(event.getSql());
            ts = new TableSet(rs);
            rs.close();
            state.close();
            //if (log.isDebugEnabled())
                //log.debug("完成查询 : 列({}), 行({})", ts, ts.getRowCount());
        } catch (Exception e) {
            throw new Exception("数据库执行异常 : " + e.getMessage());
        }
        return ts;
    }

    /**
     * 创建SQL查询语句的过滤条件
     *
     * @param query    查询对象
     * @param resource 表格对象
     * @return 查询条件
     */
    public String createSQlFilter(TableDataQuery query, TableResource resource) {
        StringBuilder where = new StringBuilder(" ");

        if (resource.getVersion() == TableGroupResource.V) {
            return "";
        }

        Date setTime = query.getDate();

        int cate = resource.getCate();


        if (cate == TableCategory.YEAR) {
            where.append("where A2 = ").append(DateFormatUtils.format(setTime, "yyyy"))
                    .append(" and A3 = ").append(DateFormatUtils.format(setTime, "M"));

        } else if (cate == TableCategory.FORM) {

        }

        return where.toString();
    }

    /**
     * 返回一个当前的数据表格对象
     *
     * @param resource 资源
     * @return 简单的数据对象
     */
    public LemsunResource getCurrentSimpleResource(TableGroupResource resource) {
        String pid = resource.getPid();
        Query query = query(where("parentPid").is(pid));

        return resourceRepository.getBaseResource(query);
    }

    /**
     * 根据表组信息获取当前表组件
     *
     * @param resource 表组
     * @return 表组件
     */
    public TableResource getCurrentTableResource(TableGroupResource resource) {

        String pid = resource.getPid();

        //注意这里目前只是做了5代表的查询
        Query query = query(where("parentPid").is(pid));

        return getTemplate().findOne(query, TableResource.class, ResourceRepository.ResourceCollectionName);
    }

    /**
     * 根据组件编码获取表组信息
     */
    public TableGroupResource getTableGroupResourceByPid(String pid) {
        return getTemplate().findOne(query(where("pid").is(pid)), TableGroupResource.class, ResourceRepository.ResourceCollectionName);
    }

}
