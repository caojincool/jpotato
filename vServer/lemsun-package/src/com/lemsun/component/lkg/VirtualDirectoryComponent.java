package com.lemsun.component.lkg;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.lemsun.core.LemsunResource;
import com.lemsun.core.jackson.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.Date;

/**
 * 虚拟文件夹下挂载的组件信息
 * User: Administrator
 * Date: 13-1-7
 * Time: 下午1:36
 */
@Document(collection = "VirtualDirectoryComponent")
public class VirtualDirectoryComponent {

	/**
	 * 导航挂载组件id
	 */
    @Id
	private ObjectId id;

	/**
	 * 导航挂载组件所属导航
	 */
    @DBRef
    private VirtualDirectory navigation;
    /**
     * 组件PID
     */
    private String pid;
    /**
     * 所属组件包ID
     */
    private String lid;

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

	/**
	 * 获取导航挂载组件所属导航
	 * @return 导航挂载组件所属导航
	 */
	public VirtualDirectory getNavigation() {
        return navigation;
    }

	/**
	 * 设置导航挂载组件所属导航
	 * @param navigation 导航挂载组件所属导航
	 */
    public void setNavigation(VirtualDirectory navigation) {
        this.navigation = navigation;
    }

    /**
     * 组件Pid
     * @return
     */
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
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

    public Date getHitchTime() {
        return hitchTime;
    }

    public void setHitchTime(Date hitchTime) {
        this.hitchTime = hitchTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void encapstation(LemsunResource lr){
        this.setName(lr.getName());
        this.setCategory(lr.getCategory());
        this.setUpdateTime(lr.getUpdateTime());
        this.setCreateUser(lr.getCreateUser());
    }
}
