package com.lemsun.web.manager.controller.model.query;


import com.lemsun.auth.BaseAccount;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.Host;
import com.lemsun.web.model.ExtPageRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;
import java.util.List;

/**
 * 组件分页模型，组件及导航挂载组件有用到
 * User: gm
 * Date: 13-1-14
 * Time: 上午11:49
 */
public class ComponentQuery extends ExtPageRequest {

    private String category;
    private String pid;
    protected String name;
    private Collection<String> pids;
    private int state;
    private String navPid;
    private String sortProperty;
    protected String showName;//页面使用
    private List<String> createAccount;//添加条件

    public ComponentQuery(){
        if(getLimit()==0)
            setPageSize(25);
        if (getPage()==0)
            setPageNumber(1);
    }

    /**
     * 获取查询的所属类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置查询的所属类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取查询名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置查询组件的名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 过滤组件编码集合
     * @return 组件编码集合
     */
    public Collection<String> getPids() {
        return pids;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public List<String> getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(List<String> createAccount) {
        this.createAccount = createAccount;
    }

    /**
     * 排序字段
     * 默认降序
     * @param sortProperty
     */
    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    /**
     * 设置过滤组件编码集合
     * @param pids 组件编码集合
     */
    public void setPids(Collection<String> pids) {
        this.pids = pids;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     * 导航组件编码
     * 用于导航过滤
     */
    public String getNavPid() {
        return navPid;
    }

    public void setNavPid(String navPid) {
        this.navPid = navPid;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Override
    public Query createQuery() {
        Query query = super.createQuery();
        query.fields().include("_id")
                .include("pid")
                .include("name")
                .include("state")
                .include("category")
                .include("categoryName")
                .include("createUser")
                .include("allowRoles")
                .include("parentPid")
                .include("permissionScript")
                .include("system")
                .include("remark")
                .include("createTime")
                .include("updateTime");
        if(StringUtils.isNotEmpty(getCategory())) {
            //表示类型不为ROOT根节点，才加上这个类型查询条件
            if(!getCategory().equals("ROOT")){
                query.addCriteria(Criteria.where("category").is(getCategory()));
            }
        }
        if(StringUtils.isNotEmpty(showName)){
            query.addCriteria(Criteria.where("createUser").in(createAccount));
        }
        if(StringUtils.isNotEmpty(getName()))
            query.addCriteria(Criteria.where("name").regex(".*" + getName() + ".*"));

        if(getPids()!=null && getPids().size()>0)
            query.addCriteria(Criteria.where("pid").nin(getPids()));

        if(StringUtils.isNotEmpty(getPid()))
            query.addCriteria(Criteria.where("pid").regex(".*"+getPid()+".*"));

        if(getState()>0)
            query.addCriteria(Criteria.where("state").is(getState()));

        query.sort().on("updateTime", Order.DESCENDING);

        if(StringUtils.isNotEmpty(sortProperty)){
            query.sort().on(sortProperty, Order.DESCENDING);
        }
        return query;
    }


}
