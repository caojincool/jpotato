package com.lemsun.auth.repository;

import com.lemsun.auth.PermissionNode;
import com.lemsun.auth.PermissionNodeExcetion;
import com.lemsun.core.repository.AbstractLocalRepository;
import com.lemsun.core.repository.ResourcePrimaryRepository;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 权限管理数据库操作类
 * User: dpyang
 * Date: 13-1-21
 * Time: 下午2:49
 */
@Repository
public class PermissionNodeRepository extends AbstractLocalRepository {



    private ResourcePrimaryRepository primaryRepository;

    @Autowired
    public PermissionNodeRepository(MongoTemplate template, ResourcePrimaryRepository primaryRepository) {
        super(template);
        this.primaryRepository = primaryRepository;
    }

    /**
     * 初始化根节点
     */
    private void initRoot() {
        PermissionNode permissionNode = new PermissionNode();
        permissionNode.setKey("Root");
        permissionNode.setName("权限根节点");
        permissionNode.setRemark("所有权限的根部节点");
        getTemplate().insert(permissionNode);
    }

    /**
     * 插入权限节点
     *
     * @param pnode 权限节点
     */
    public void insert(PermissionNode pnode) throws PermissionNodeExcetion {

        if (pnode.getId() != null)
            throw PermissionNodeExcetion.PIdExists;
        else if (StringUtils.isEmpty(pnode.getName()))
            throw PermissionNodeExcetion.PNameisNull;
        else if (StringUtils.isEmpty(pnode.getKey()))
            throw PermissionNodeExcetion.PKeyIsNull;
        else if (pnode.getParent() == null)
            throw PermissionNodeExcetion.PParentIsNull;

        getTemplate().insert(pnode);
    }

    /**
     * 保存(更新)一个权限点
     */
    public void save(PermissionNode pnode) throws PermissionNodeExcetion {

        if (pnode.getId() == null)
            throw PermissionNodeExcetion.PNameisNull;
        else if (StringUtils.isEmpty(pnode.getName()))
            throw PermissionNodeExcetion.PNameisNull;
        else if (StringUtils.isEmpty(pnode.getKey()))
            throw PermissionNodeExcetion.PKeyIsNull;
        else if (pnode.getParent() == null)
            throw PermissionNodeExcetion.PParentIsNull;

        getTemplate().save(pnode);
    }

    /**
     * 删除节点所有节点信息
     *
     * @param id 节点列表信息
     */
    private void deleteChild(ObjectId id) throws PermissionNodeExcetion {

        List<PermissionNode> child = getTemplate().find(query(where("parent.$id").is(id)), PermissionNode.class);

        getTemplate().remove(query(where("_id").is(id)), PermissionNode.class);

        for (PermissionNode p : child) {
            deleteChild(p.getId());
        }
    }

    /**
     * 删除权限节点信息
     *
     * @param pnode 权限节点
     */
    public void delete(PermissionNode pnode) throws PermissionNodeExcetion {

        if (pnode.getId() == null)
            throw PermissionNodeExcetion.PIdisNull;

        //判断节点是否是根节点
        if (getTemplate().findOne(query(where("key").is("Root")), PermissionNode.class).equals(pnode))
            throw PermissionNodeExcetion.PRootDel;

        //判断该节点是否有子节点移除所有节点
        if (getTemplate().find(query(where("parent.$id").is(pnode.getId())), PermissionNode.class).size() > 0)
            deleteChild(pnode.getId());

        //移除当前节点
        getTemplate().remove(query(where("_id").is(pnode.getId())), PermissionNode.class);
    }

    /**
     * 返回根节点
     *
     * @return 根节点
     */
    public PermissionNode getRoot() {
        PermissionNode p = getTemplate().findOne(new Query(Criteria.where("key").is("Root")), PermissionNode.class);

        if (p == null) {
            initRoot();
            p = getTemplate().findOne(new Query(Criteria.where("key").is("Root")), PermissionNode.class);
        }

        lodaChild(p);
        return p;
    }

    /**
     * 返回权限根节点下面的二级节点
     */
    public List<PermissionNode> getRootChildPermissions() {
        //获取根节点
        PermissionNode permissionNode = getTemplate().findOne(query(where("key").is("Root")), PermissionNode.class);

        //如果根节点为空,初始化根节点
        if (permissionNode == null)
            this.initRoot();

        return getChild(permissionNode.getId());
    }

    /**
     * 加载节点的二级子节点
     */
    private void lodaChild(PermissionNode p) {

        if (p == null)
            return;

        List<PermissionNode> child = getTemplate().find(query(where("parent.$id").is(p.getId())), PermissionNode.class);
        if (child.isEmpty() || child == null)
            return;
        p.setChild(child);

        //遍历子节点
        for (PermissionNode pnode : child)
            this.lodaChild(pnode);
    }

    /**
     * 返回某个节点的子节点
     */
    public List<PermissionNode> getChild(ObjectId id) {
        return getTemplate().find(query(where("parent.$id").is(id)), PermissionNode.class);
    }

    /**
     * 获取一个节点信息
     */
    public PermissionNode getPermissionNode(ObjectId id) {
        return getTemplate().findOne(query(where("_id").is(id)), PermissionNode.class);
    }

    /**
     * 根据权限节点获取该节点的详细信息
     */
    public PermissionNode getPermissionByKey(String key) throws PermissionNodeExcetion {
        if (StringUtils.isEmpty(key))
            throw PermissionNodeExcetion.PKeyIsNull;

        return getTemplate().findOne(query(where("key").is(key)), PermissionNode.class);
    }
}
