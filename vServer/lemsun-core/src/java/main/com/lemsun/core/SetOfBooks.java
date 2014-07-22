package com.lemsun.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lemsun.core.jackson.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * 帐套属性
 * User: dp
 * Date: 13-7-24
 * Time: 上午8:23
 */
@Document(collection = "SetOfBooks")
public class SetOfBooks {

    private String sid;
    private String name;
    private Date createTime;
    private String remark;

    @Id
    private ObjectId _id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId getId() {
        return this._id;
    }

    @JsonSerialize(using = ObjectIdSerializer.class)
    public void setId(ObjectId id) {
        this._id = id;
    }

    @Transient
    private List<String> accounts;

    @Transient
    private List<String> resources;

    /**
     * 帐套编码
     *
     * @return
     */
    public String getSid() {
        return sid;
    }

    /**
     * 帐套编码
     *
     * @param sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 帐套名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 帐套名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 说明
     *
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 说明
     *
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 创建时间
     *
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
