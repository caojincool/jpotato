package com.lemsun.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 平台中的导航. 在这个导航中. 可以存储下一个导航. 可以摆放资源
 * User: Xudong
 * Date: 12-10-23
 * Time: 下午2:36
 */
@Document(collection = "Navigation")
public class Navigation extends AbstractLemsunResource implements ITreeNode<Navigation> {
	/**
	 * 导航父节点
	 */
	@DBRef
	private Navigation parent;

	/**
	 * 导航说明
	 */
	private String remark;

	@Transient
	private List<Navigation> child;

    @Transient
    private List<NavigationComponent> components;

    @Transient
    private int navResourceTotal;

    /**
     * 这里默认的构造方法是为了转换json对象
     */
    public Navigation(){

    }

	@PersistenceConstructor
	public Navigation(String name)
	{
		this(name, "NAV");
	}

	public Navigation(String name, String category) {
		super(name, category);
	}

	/**
	 * 获取导航父节点
	 * @return 导航父节点
	 */
    @JsonIgnore
	public Navigation getParent() {
		return parent;
	}

	/**
	 * 设置导航父节点
	 * @param parent 导航父节点
	 */
	public void setParent(Navigation parent) {
		this.parent = parent;
	}

	/**
	 * 获取子导航
	 * @return 子导航
	 */
    @Override
	public List<Navigation> getChild() {
		return child;
	}

	/**
	 * 设置 子导航
	 * @param child 集合
	 */
    @Override
	public void setChild(List<Navigation> child) {
		if(child != null)
			for(Navigation n : child) n.setParent(this);

		this.child = child;
	}

	@Override
	public void loadChild(Collection  collection) {
		Assert.notNull(collection);

		List<Navigation> navs = new ArrayList<>(collection.size());

		for(Object c : collection)
			if(c instanceof Navigation)
				navs.add((Navigation)c);

		setChild(navs);
	}

	/**
	 * 获取导航说明
	 * @return 导航说明
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置导航说明
	 * @param remark 导航说明
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取导航下的所有挂载组件信息
	 * @return 导航下的所有挂载组件信息
	 */
    @JsonIgnore
    public List<NavigationComponent> getComponents() {
        return components;
    }


    @Override
    public String toString() {
        return "当前节点名称:"+getName();
    }

    /**
     * 所属导航组件合计数
     */
    public int getNavResourceTotal() {

        int t = navResourceTotal;

        if(getChild() != null) {

            for(Navigation n : getChild()) {
                t += n.getNavResourceTotal();
            }

        }


        return t;
    }

    public void setNavResourceTotal(int navResourceTotal) {
        this.navResourceTotal = navResourceTotal;
    }
}
