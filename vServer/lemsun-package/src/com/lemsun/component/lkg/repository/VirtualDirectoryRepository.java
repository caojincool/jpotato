package com.lemsun.component.lkg.repository;

import com.lemsun.component.lkg.BasePackage;
import com.lemsun.component.lkg.VirtualDirectory;
import com.lemsun.component.lkg.VirtualDirectoryComponent;
import com.lemsun.component.lkg.model.AddVdcForm;
import com.lemsun.core.*;
import com.lemsun.core.member.NavigationReturnData;
import com.lemsun.core.repository.AbstractLocalRepository;
import com.lemsun.core.repository.ResourcePrimaryRepository;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.core.service.IAccountCoreService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
public class VirtualDirectoryRepository extends AbstractLocalRepository {

    private ResourcePrimaryRepository primaryRepository;
    private static final String LID = "LID";
    private static final String VirtualDirectoryComponent = "VirtualDirectoryComponent";


    @Autowired
    public VirtualDirectoryRepository(ResourcePrimaryRepository primaryRepository,

                                MongoTemplate template) {
        super(template);
        this.primaryRepository = primaryRepository;
    }

    /**
     * @return 返回根节点
     */
    public VirtualDirectory getRoot(String lip) {

        VirtualDirectory root = getTemplate().findOne(query(where("pid").is(LID+lip)), VirtualDirectory.class);
        if (root == null) {
            root = new VirtualDirectory("导航", "NAV");
            root.setPid(LID+lip);
            root.setUpdateTime(new Date());
            getTemplate().save(root);
        }
        loadChild(root);
        return root;
    }

    /**
     * 根据pid获取Navigation
     *
     * @param pid 组件pid
     * @return 组件
     */
    public VirtualDirectory getNavigationByPid(String pid) {
        return getTemplate().findOne(query(where("pid").is(pid)), VirtualDirectory.class);
    }

    public VirtualDirectory get(String pid) {
        return getNavigationByPid(pid);
    }

    /**
     * 创建导航信息
     *
     * @param navigation 导航信息
     */
    public void create(VirtualDirectory navigation) {

        //自动生成pid
        navigation.setPid(primaryRepository.generator(navigation));
        navigation.setUpdateTime(new Date());
        getTemplate().save(navigation);
    }



    /**
     * 删除导航信息，通过pid
     *
     * @param pid 导航信息pid
     */
    public void deleteVirtualDirectory(String pid) {
        if (pid.equals(LID)) return;
        VirtualDirectory navigation = getNavigationByPid(pid);
        if (navigation != null) {
            //删除此导航信息的挂载组件信息
            removeNavigationComponentByNavId(navigation.getId());
            deletNavigationChild(navigation.getId());
            getTemplate().remove(query(where("pid").is(pid)), VirtualDirectory.class);
        }
    }

    /**
     * 删除导航信息
     *
     * @param parentId 导航信息id
     */
    private void deletNavigationChild(ObjectId parentId) {
        List<VirtualDirectory> child = getTemplate().find(query(where("parent.$id").is(parentId)), VirtualDirectory.class);

        getTemplate().remove(query(where("parent.$id").is(parentId)), VirtualDirectory.class);
        for (VirtualDirectory c : child) {
            //删除此导航信息的挂载组件信息
            removeNavigationComponentByNavId(c.getId());

            deletNavigationChild(c.getId());
        }
    }

    /**
     * 加载根下所有子节点
     *
     * @param navigation 导航点
     */
    public void loadChild(VirtualDirectory navigation) {
        if (navigation == null) return;

        List<VirtualDirectory> child = getTemplate().find(query(where("parent.$id").is(navigation.getId())), VirtualDirectory.class);

        if (child == null || child.isEmpty()) return;

        navigation.setChild(child);

        for (VirtualDirectory n : child)
            loadChild(n);
    }

    /**
     * 根据导航id，获取此导航的所有挂载组件信息
     *
     * @param id 导航id
     * @return 挂载组件信息
     */
    public List<VirtualDirectoryComponent> getNavigationComponentById(ObjectId id) {

        return getTemplate().find(query(where("navigation.$id").is(id)), VirtualDirectoryComponent.class);
    }
    /**
     * 删除导航组件挂载信息
     *
     * @param id 导航组件挂载信息parent.$id
     */
    public void removeNavigationComponentByNavId(ObjectId id) {

        getTemplate().remove(query(where("navigation.$id").is(id)), VirtualDirectoryComponent.class);
    }
    /**
     * 文件夹下组件资源挂载信息分页
     *
     * @param request 分页条件
     * @return 分页数据
     */
    public Page<VirtualDirectoryComponent> getVDComponentPaggingByVirtualDirectoryId(AbstractPageRequest request) {
        Query query = request.createQuery();

        long total = getTemplate().count(query, VirtualDirectoryComponent);

        List<VirtualDirectoryComponent> data = getTemplate().find(query, VirtualDirectoryComponent.class, VirtualDirectoryComponent);

        return new PageImpl<>(data, request, total);
    }
    /**
     * 删除导航组件挂载信息，通过主键
     * @param ids 需要删除id集合
     */
    public void delVirtualDirectoryComponentByIds(String[] ids){
        Query query = new Query();
        Query id = query.addCriteria(Criteria.where("id").in((Object)ids));
        getTemplate().remove(id, VirtualDirectoryComponent.class);
    }
    /**
     * 添加导航挂载组件
     *
     * @throws Exception
     */
    public  void addVirtualDirectoryComponent(AddVdcForm addVdcForm) throws Exception{
       List<VirtualDirectoryComponent> list= new ArrayList<>();
        VirtualDirectoryComponent vd;
        VirtualDirectory vd1= new VirtualDirectory(new ObjectId(addVdcForm.getFid()));
        for(String pid :addVdcForm.getPids()){
            vd=new VirtualDirectoryComponent();
            vd.setHitchTime(new Date());
            vd.setOperator(Host.getInstance().getCurrentAccount().getAccount());
            vd.setNavigation(vd1);
            vd.setLid(addVdcForm.getLid());
            vd.setPid(pid);
            list.add(vd);
        }
        getTemplate().insert(list,VirtualDirectoryComponent);
    }
    /**
     * 通过组件包ID获取它下所有组件
     * @param packageId
     * @return
     */
    public List<VirtualDirectoryComponent> getVirtualDirectoryComponentsByPackageId(String packageId) {
        Query dbQuery = new Query();
        dbQuery.addCriteria(Criteria.where("lid").is(packageId));
        dbQuery.fields().include("pid");
        List<VirtualDirectoryComponent> data =  getTemplate().find(dbQuery, VirtualDirectoryComponent.class, VirtualDirectoryComponent);

        return data;
    }
    public List<BasePackage> getBasePackageByPid(String pid) {
        List<VirtualDirectoryComponent>  vds=getTemplate().find(query(where("pid").is(pid)), VirtualDirectoryComponent.class);
        List<String> lids=new ArrayList<>(vds.size());
        for(VirtualDirectoryComponent vd:vds){
            lids.add(vd.getLid());
        }
        List<BasePackage> bps=getTemplate().find(query(where("lid").in(lids)), BasePackage.class);
        return bps;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 根据组件pid移除 虚拟文件夹下挂载的组件信息
     * @param pid 组件ID
     */
    public void removeComponent(String pid){
        getTemplate().remove(query(where("pid").is(pid)), VirtualDirectoryComponent.class);
    }
}
