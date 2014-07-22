package com.lemsun.auth;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 权限信息表
 * User: dpyang
 * Date: 13-1-21
 * Time: 下午2:25
 */
@Document(collection = "Permissions")
public class PermissionNode implements com.lemsun.auth.ITreeNode {

    //忽略保存到数据库
    @DBRef
    private PermissionNode parent;

    //忽略保存到数据库
    @Transient
    private List<PermissionNode> child;

    @Id
    private ObjectId id;
    private String key;
    private String name;
    private String remark;

    /**
     * 获取权限父节点
     *
     * @return 权限父节点
     */
    public PermissionNode getParent() {
        return parent;
    }

    /**
     * 设置权限父节点
     *
     * @param parent 父节点
     */
    public void setParent(PermissionNode parent) {
        this.parent = parent;
    }

    /**
     * 加载一个集合类型. 对类型进行判断加载
     *
     * @param collection 集合对象
     */
    @Override
    public void loadChild(Collection collection) {
        Assert.notNull(collection);

        //节点容器
        List<PermissionNode> permissionNodes = new ArrayList<>(collection.size());

        //把权限表节点添加到容器
        for (Object c : collection)
            if (c instanceof PermissionNode)
                permissionNodes.add((PermissionNode) c);
        //设置子节点
        setChild(permissionNodes);
    }

    /**
     * 获取权限子节点
     *
     * @return 权限子节点
     */
    public List<PermissionNode> getChild() {
        return child;
    }

    /**
     * 设置权限子节点
     */
    @Override
    public void setChild(List child) {
        this.child = child;
    }

    /**
     * 获取权限数据库ID
     *
     * @return 权限数据库ID
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * 设置数据库权限ID
     *
     * @param id 权限的数据库ID
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * 获取权限Key
     *
     * @return 权限Key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置权限key
     *
     * @param key 权限Key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取权限名称
     *
     * @return 权限名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置权限名称
     *
     * @param name 权限名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取权限说明
     *
     * @return 权限说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置权限说明
     *
     * @param remark 权限说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取从根节点至当前节点的Key
     *
     * @return 所有的key
     */
    public String getFullKey() {
        if (getParent() != null)
            return getParent().getFullKey() + "." + getKey();

        return getKey();
    }
}