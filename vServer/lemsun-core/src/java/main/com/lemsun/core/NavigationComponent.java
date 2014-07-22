package com.lemsun.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lemsun.core.jackson.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.Date;

/**
 * 导航文件夹下挂载的组件信息
 * User: Administrator
 * Date: 13-1-7
 * Time: 下午1:36
 */
@Document(collection = "NavigationComponent")
public class NavigationComponent {

	/**
	 * 导航挂载组件id
	 */
    @Id
	private ObjectId id;

    private String resourcePid;

    private String navPid;

    @Transient
    private String name;

    @Transient
    private String category;

    @Transient
    private String createUser;

    @Transient
    private Date updateTime;

    private Date hitchTime;

    private String operator;

    /**
     * 获取导航挂载组件id
     */
    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId getId() {
        return id;
    }

	/**
	 * 设置导航挂载组件id
	 * @param id 导航挂载组件id
	 */
    protected void setId(ObjectId id) {
        this.id = id;
    }


    public String getNavPid() {
        return navPid;
    }


    public void setNavPid(String navPid) {
        this.navPid = navPid;
    }

    /**
     * 挂载的导航组件编码
     * @return
     */
    public String getResourcePid() {
        return resourcePid;
    }

    /**
     * 挂载的导航组件编码
     * @param resourcePid
     */
    public void setResourcePid(String resourcePid) {
        this.resourcePid = resourcePid;
    }

    /**
     * 挂载时间
     * @return
     */
    public Date getHitchTime() {
        return hitchTime;
    }

    /**
     * 挂载时间
     * @param hitchTime
     */
    public void setHitchTime(Date hitchTime) {
        this.hitchTime = hitchTime;
    }

    /**
     * 操作者
     * @return
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 操作者
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 设置当前的导航节点中挂载的组件, 将组件的属性赋值到当前的导航节点上
     * @param resource
     */
    public void setLemsunResource(LemsunResource resource) {

        if(resource == null) {
            setName(null);
            setCreateUser(null);
            setCategory(null);
        } else {
            setName(resource.getName());
            setCategory(resource.getCategory());
            setCreateUser(resource.getCreateUser());
        }
    }

}
