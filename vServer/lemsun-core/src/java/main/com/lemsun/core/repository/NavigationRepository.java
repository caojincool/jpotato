package com.lemsun.core.repository;

import com.lemsun.core.*;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 导航及导航挂载组件操作层
 * User: Xudong
 * Date: 12-10-23
 * Time: 下午2:50
 */
@Repository
public class NavigationRepository extends AbstractLocalRepository {

    private ResourcePrimaryRepository primaryRepository;

    private static final String NAV = "NAV00000000";

    private static final String NavigationComponentName = "NavigationComponent";

    private ResourceRepository resourceRepository;

    @Autowired
    public NavigationRepository(ResourceRepository resourceRepository,
                                ResourcePrimaryRepository primaryRepository,
                                MongoTemplate template) {
        super(template);
        this.primaryRepository = primaryRepository;
        this.resourceRepository = resourceRepository;
    }

    /**
     * @return 返回根节点
     */
    public Navigation getRoot() {

        Navigation root = getTemplate().findOne(query(where("pid").is(NAV)), Navigation.class);
        if (root == null) {
            root = new Navigation("导航", "NAV");
            root.setPid(NAV);
            root.setUpdateTime(new Date());
            getTemplate().save(root);
        }
        loadChild(root);
        return root;
    }

    public int getCountByNavPid(String navPid){
        return getAllResourcePidByNavid(navPid).size();
    }

    /**
     * 根据pid获取Navigation
     *
     * @param pid 组件pid
     * @return 组件
     */
    public Navigation get(String pid) {
        Navigation nav=getTemplate().findOne(query(where("pid").is(pid)), Navigation.class);
        loadChild(nav);
        return nav;
    }

    /**
     * 创建导航信息
     *
     * @param navigation 导航信息
     */
    public void create(Navigation navigation) throws Exception {
        //自动生成pid


        navigation.setPid(primaryRepository.generatorNavigate(navigation));

        navigation.setUpdateTime(new Date());
        List<Navigation> navigations=navigation.getParent().getChild();
        if (navigations != null){
            for (Navigation nav:navigations){
                if (nav.getName().equals(navigation.getName()))
                    throw new Exception("该目录中已经存在"+nav.getName());
            }
        }

        getTemplate().save(navigation);

        if (navigation.getComponents() != null)
            for (NavigationComponent c : navigation.getComponents()) {
                getTemplate().save(c);
            }

        if (navigation.getChild() != null)
            for (Navigation n : navigation.getChild())
                create(n);
    }

    /**
     * 修改导航信息
     *
     * @param navigation 要修改的导航信息对象
     */
    public void edit(Navigation navigation) {
        Navigation nav = get(navigation.getPid());

        if (nav != null) {
            navigation.setUpdateTime(new Date());
            getTemplate().save(navigation);
        }
    }

    /**
     * 删除导航信息，通过pid
     *
     * @param pid 导航信息pid
     */
    public void remove(String pid) {
        if (pid.equals(NAV))
            return;

        Navigation navigation = get(pid);
        if (navigation != null) {
            List<Navigation> children=navigation.getChild();
            if (children!=null)
                for (Navigation nav:children){
                    remove(nav.getPid());
                }
            getTemplate().remove(query(where("pid").is(pid)), Navigation.class);
            //移除对应的导航组件
            getTemplate().findAndRemove(query(where("navigation.$id").is(navigation.getId())),NavigationComponent.class);
        }
    }

    /**
     * 加载根下所有子节点
     *
     * @param navigation 导航点
     */
    public void loadChild(Navigation navigation) {
        if (navigation == null)
            return;

        List<Navigation> child = getTemplate().find(query(where("parent.$id").is(navigation.getId())), Navigation.class);

        if (child == null || child.isEmpty()) return;

        navigation.setChild(child);

        for (Navigation n : child)
            loadChild(n);
    }

    /**
     * 根据导航编码对应下一级的子节点
     *
     * @param parentId 导航节点的ObjectID
     * @return 导航集合
     */
    public List<Navigation> getChild(ObjectId parentId) {
        return getTemplate().find(query(where("parent.$id").is(parentId)), Navigation.class);
    }

	/* -------------导航挂载组件相关--------------------------------------- */

    /**
     * 批量挂载导航组件
     * @param navigationComponents 导航组件集合
     */
    public void addComponents(List<NavigationComponent> navigationComponents){
        getTemplate().insert(navigationComponents,NavigationComponent.class);
    }

    /**
     * 保存导航组件信息
     *
     * @param navcpt 导航组件信息
     */
    public void addComponent(NavigationComponent navcpt) throws Exception {
        if(getTemplate().findOne(query(where("resourcePid")
                .is(navcpt.getResourcePid()).and("navPid").is(navcpt.getNavPid())),NavigationComponent.class)!=null)
            throw new Exception("一个组件不能重复挂载在同一导航下!");

        getTemplate().insert(navcpt);
    }

    /**
     * 根据导航id，获取此导航的所有组件编码集合
     *
     * @param navId 导航id
     * @return 挂载组件信息
     */
    public List<String> getAllResourcePidByNavid(String navId) {
        Query query=new Query();
        query.addCriteria(Criteria.where("navPid").is(navId));
        query.fields().include("resourcePid");

        final ArrayList<String> data = new ArrayList<>();
        //获取一个导航对应的组件编码
        getTemplate().executeQuery(query, NavigationComponentName, new DocumentCallbackHandler() {
            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                data.add((String)dbObject.get("resourcePid"));
            }
        });

        return data;
    }

    public List<NavigationComponent> getNavigationByResourcePid(IResource lmsResource){
        return getTemplate().find(query(where("resourcePid").is(lmsResource.getPid())),NavigationComponent.class);
    }

    public boolean checkResourceInComponent(String pid) {

        return getTemplate().findOne(query(where("pid").is(pid)), NavigationComponent.class, NavigationComponentName) != null;

    }

    /**
     * 获取一个导航节点下挂载的组件信息
     */
    public List<NavigationComponent> getAllComponents(String pid) {


        List<NavigationComponent> list = getTemplate()
                .find(query(where("navPid").is(pid)), NavigationComponent.class, NavigationComponentName);

        if(list != null)
            for(NavigationComponent n : list) {

                LemsunResource r = resourceRepository.getBaseResource(n.getResourcePid());



                n.setLemsunResource(r);

            }


        return list;

    }

    /**
     * 根据组件编码获取导航组件信息
     * @param pid 组件编码
     * @return
     */
    public List<Navigation> getNavigationByResourcePid(String pid){

        Query query=new Query(Criteria.where("pid").is(pid));
        query.fields().include("navPid");
        final List<String> navids=new ArrayList<>();


        getTemplate().executeQuery(query,NavigationComponentName,
                new DocumentCallbackHandler() {
            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                navids.add((String) dbObject.get("navPid"));
            }
        });

        return getTemplate().find(query(where("pid").in(navids)),Navigation.class);
    }

    /**
     * 删除导航组件
     *
     * @param nc 导航组件
     */
    public void removeComponent(NavigationComponent nc) {

        getTemplate().remove(query(where("navPid").is(nc.getNavPid())
                .and("resourcePid").is(nc.getResourcePid())), NavigationComponent.class);
    }

    /**
     * 导航组件资源挂载信息分页
     *
     * @param request 分页条件
     * @return 分页数据
     */
    public Page<NavigationComponent> getNComponentPagging(AbstractPageRequest request) {
        Query query = request.createQuery();

        long total = getTemplate().count(query, NavigationComponentName);

        List<NavigationComponent> data = getTemplate().find(query, NavigationComponent.class, NavigationComponentName);

        return new PageImpl<>(data, request, total);
    }
}
