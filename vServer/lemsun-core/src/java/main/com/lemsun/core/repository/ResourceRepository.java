package com.lemsun.core.repository;

import com.lemsun.core.*;
import com.lemsun.core.events.ResourceEvent;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

/**
 * 组件的基本操作层. 并对全局的组件资源树进行维护.
 * <br/> 这个类型有点类似于公共的组件操作类.
 * <br/> 同时还负责管理全局的组件对 监听组件对象的增删改等操
 * User: Xudong
 * Date: 12-10-24
 * Time: 上午11:26
 */
@Repository
public final class ResourceRepository extends AbstractLocalFsRepository {


    private ResourcePrimaryRepository primaryRepository;
    private static final Logger log = LoggerFactory.getLogger(ResourceRepository.class);
    public static final String ResourceCollectionName = "ResourceComponent";
    private IResource root;
    private ResourceAttachFileRepository attachFileRepository;

    @Autowired
    public ResourceRepository(ResourcePrimaryRepository primaryRepository,
                              ResourceAttachFileRepository attachFileRepository,
                              GridFsOperations operations,
                              MongoTemplate template) {
        super(template, operations);
        this.primaryRepository = primaryRepository;
        this.attachFileRepository = attachFileRepository;
        root = new LemsunResource("Root", BaseCategory.ROOT.getCategory());
        if (log.isDebugEnabled()) log.debug("开始加载组件资源导");
        if (log.isDebugEnabled()) log.debug("组件导航加载完成");
    }

    /**
     * 获取一个通用的附件操作对象
     */
    public ResourceAttachFileRepository getAttachFileRepository() {
        return attachFileRepository;
    }

    /**
     * 创建一个资源对创建的会检查对象的类型. 已经产生一个组件ID
     *
     * @param resource
     * @throws Exception
     */
    public void create(IResource resource) {
        String category = resource.getCategory();

        if (StringUtils.isEmpty(category))
            throw new LemsunException("组件类型不能为空");

        if (StringUtils.isEmpty(resource.getPid()))
            resource.setPid(primaryRepository.generator(resource));

        if (StringUtils.isNotEmpty(resource.getPid()) && getBaseResource(resource.getPid()) != null)
            throw new LemsunException("组件编码" + resource.getPid() + "已经被占用");

        resource.setCreateTime(new Date());
        resource.setUpdateTime(new Date());

        resource.setState(ResourceState.RELEASE);

        getTemplate().insert(resource, ResourceCollectionName);

        if (log.isDebugEnabled())
            log.debug("数据库新增一个组件:" + resource.toString());
    }

    /**
     * 获取一个类型化的组件对
     */
    public <T> T get(String pid, Class<T> type) {
        return getTemplate().findOne(query(where("pid").is(pid)), type, ResourceCollectionName);
    }

    /**
     * 获取一个自动类型的对象
     *
     * @param pid 主键
     * @return 对象
     */
    public IResource get(String pid) {

        final IResource[] rc = {null};
        final MongoConverter converter = getTemplate().getConverter();

        getTemplate().executeQuery(query(where("pid").is(pid)), ResourceCollectionName, new DocumentCallbackHandler() {
            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                String type = (String) dbObject.get("_class");
                try {
                    Class<?> cls = Class.forName(type);
                    Object resource = converter.read(cls, dbObject);
                    if (resource instanceof IResource) {
                        rc[0] = (IResource) resource;
                    } else {
                        if (log.isErrorEnabled())
                            log.error("发现一个组件的类型没有实现 IResource 接口: {}", cls);
                    }

                } catch (ClassNotFoundException e) {
                    if (log.isErrorEnabled())
                        log.error("组件类型不能实例:{}, {}", type, e);
                }
            }
        });

        return rc[0];
    }


    /**
     * 获取基本的组件信
     *
     * @param pid 主键
     * @return 基本对象
     */
    public LemsunResource getBaseResource(String pid) {

        Query q = query(where("pid").is(pid));
        return getBaseResource(q);
    }

    /**
     * 使用查询对象获取一个基本的组件模型
     *
     * @param query 查询对象
     * @return 基本的组件模型
     */
    public LemsunResource getBaseResource(Query query) {
        query.fields()
                .include("_id")
                .include("pid")
                .include("name")
                .include("category")
                .include("createUser")
                .include("allowRoles")
                .include("parentPid")
                .include("permissionScript")
                .include("remark")
                .include("updateTime")
                .include("strParams")
                .include("businessCode");
        return getTemplate().findOne(query, LemsunResource.class, ResourceCollectionName);
    }


    /**
     * 根据业务编码获取组件信息
     *
     * @param code 业务编码
     * @return 基本的组件信息
     */
    public LemsunResource getResourceByCode(String code) {

        Query query = new Query();

        query.fields()
                .include("_id")
                .include("pid")
                .include("name")
                .include("category")
                .include("createUser")
                .include("allowRoles")
                .include("parentPid")
                .include("permissionScript")
                .include("remark")
                .include("updateTime")
                .include("businessCode");


        final LemsunResource[] rc = {null};
        final MongoConverter converter = getTemplate().getConverter();

        return rc[0];

    }

    /**
     * 根据类型返回组件
     *
     * @param category
     * @return
     */
    public List<LemsunResource> getResourceByCategory(String category) {
        Query q = query(where("category").is(category));
        q.fields()
                .include("pid")
                .include("name")
                .include("category")
                .include("createUser")
                .include("allowRoles")
                .include("parentPid")
                .include("permissionScript")
                .include("updateTime");
        return getTemplate().find(q, LemsunResource.class, ResourceCollectionName);
    }

    /**
     * 更新组件资源
     *
     * @param resource 组件
     * @throws Exception 更新异常
     */
    public void update(IResource resource) {
        if (resource.getId() == null)
            throw ResourceException.ResourcePidisNull;
        resource.setUpdateTime(new Date());
        getTemplate().save(resource, ResourceCollectionName);
    }


    /**
     * 从当前的组件为根节点开加载子节
     *
     * @param root
     */
    public void loadTreeResource(LemsunResource root) {
        List<LemsunResource> resources = getResourceByParentId(root.getPid());

        if (resources == null || resources.size() == 0)
            return;

        if (root instanceof ILoadChild) {
            ((ILoadChild<?>) root).loadChild(resources);
        }


        for (LemsunResource r : resources) {
            if (r instanceof ILoadChild) loadTreeResource(r);
        }

    }


    /**
     * 获取一个自动转换的类型. 对当前的组件进行类型自动转换
     *
     * @param parentid 父节
     * @return 自动实例化的组件
     */
    public List<IResource> getTypedResourceList(String parentid) {
        final List<IResource> resources = new ArrayList<>();
        final MongoConverter converter = getTemplate().getConverter();
        getTemplate().executeQuery(query(where("parentPid").is(parentid)), ResourceCollectionName, new DocumentCallbackHandler() {
            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                String type = (String) dbObject.get("_class");
                try {
                    Class<?> cls = Class.forName(type);
                    Object resource = converter.read(cls, dbObject);
                    if (resource instanceof IResource) {
                        resources.add((IResource) resource);
                    } else {
                        if (log.isErrorEnabled())
                            log.error("发现一个组件的类型没有实现 IResource 接口: {}", cls);
                    }
                } catch (ClassNotFoundException e) {
                    if (log.isErrorEnabled())
                        log.error("组件类型不能实例{}, {}", type, e);
                }
            }
        });

        return resources;
    }


    /**
     * 获取全部类型下的数据
     *
     * @param type 类型
     * @param <T>  类型
     * @return 组件列表
     */
    public <T extends IResource> List<T> getAll(Class<T> type) {
        return getTemplate().find(query(where("_class").is(type.getName())), type, ResourceCollectionName);
    }


    public <T extends IResource> List<T> getAll(Class<T> type, String parentPid) {
        return getTemplate().find(query(where("_class").is(type.getName()).and("parentPid").is(parentPid)), type, ResourceCollectionName);
    }


    public <T extends IResource> Page<T> getPagging(AbstractPageRequest query, Class<T> clazz) {
        Query dbQuery = query.createQuery();
        long total = getTemplate().count(dbQuery, ResourceCollectionName);


        List<T> data = getTemplate().find(dbQuery, clazz, ResourceCollectionName);

        return new PageImpl<>(data, query, total);
    }

    /**
     * 根据PID得到所有子集组
     *
     * @param parentId
     * @return
     */
    public List<LemsunResource> getResourceByParentId(String parentId) {
        return getTemplate().find(query(where("parentPid").is(parentId)), LemsunResource.class, ResourceCollectionName);
    }


    /**
     * 返回一个组件的根节当前的根节点维护了系统内的所有用于组件导航的树形结构. <br/>
     *
     * @return 资源根组
     */
    public IResource getRoot() {
        return root;
    }

    /**
     * 根据pid集合查询多个资源
     *
     * @param pids
     * @return
     */
    public List<LemsunResource> getResourceByPids(List<String> pids) {
        return getTemplate().find(query(where("pid").in(pids)), LemsunResource.class, ResourceCollectionName);
    }


    /**
     * 移除一个组件对
     * 1、移除附件
     * 2、移除组件
     *
     * @param pid 组件ID
     */
    public void remove(String pid) {
        Assert.notNull(pid);
        IResource resource = get(pid);
        remove(resource);
    }


    public void remove(IResource resource) {
        Assert.notNull(resource);

        ResourceEvent e = new ResourceEvent(this, resource, ResourceEvent.Action.BefreDelete);
        ApplicationContext app = Host.getInstance().getContext();
        try
        {
            app.publishEvent(e);
        }
        catch (Exception ex)
        {

        }

        String pid = resource.getPid();

        //移除关联的文件
        getAttachFileRepository().deleteAll(pid);

        if (log.isDebugEnabled())
            log.debug("成功从数据库删除组件" + pid + "的附件关联信息!");


        getTemplate().remove(query(where("pid").is(pid)), ResourceRepository.ResourceCollectionName);

        if (log.isDebugEnabled())
            log.debug("成功从数据库删除组件" + pid + "的基本信息!");


        app.publishEvent(new ResourceEvent(this, resource, ResourceEvent.Action.Delete));
    }

    /**
     * 通过通过组件类型查询它下分类函数
     *
     * @param category
     * @return
     */
    public List<String> distinctScriptTypebyCategory(String category) {
        Query query = Query.query(Criteria.where("category").is(category));
        List<String> distinctScriptTypes = getTemplate().getCollection(ResourceRepository.ResourceCollectionName).distinct("scriptType", query.getQueryObject());
        return distinctScriptTypes;
    }

    /**
     * 通过函数类型和组件类别查询组件
     *
     * @param category
     * @return
     */
    public List<LemsunResource> queryByCategoryAndScriptType(String category, String scriptType) {
        Query query = Query.query(Criteria.where("category").is(category).and("scriptType").is(scriptType));
        return getTemplate().find(query, LemsunResource.class, ResourceRepository.ResourceCollectionName);
    }

    public List<GridFSDBFile> getRegFils(String reg){
        List<GridFSDBFile> lsit= getGridFsOperations().find(query(whereFilename().regex(reg)));
        return lsit;
    }


}
