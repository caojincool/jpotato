package com.lemsun.component.lkg;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemsun.core.AbstractLemsunResource;
import com.lemsun.core.ITreeNode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
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
@Document(collection = "VirtualDirectory")
public class VirtualDirectory extends AbstractLemsunResource implements ITreeNode<VirtualDirectory> {
    /**
     * 所属组件包
     */
    private String lid;
	/**
	 * 导航父节点
	 */
	@DBRef
	private VirtualDirectory parent;

	/**
	 * 导航说明
	 */
	private String remark;

	@Transient
	private List<VirtualDirectory> child;

    @Transient
    private List<VirtualDirectoryComponent> components;

    /**
     * 这里默认的构造方法是为了转换json对象
     */
    public VirtualDirectory(){

    }
    public VirtualDirectory(ObjectId id){
        super.setId(id);
    }
	@PersistenceConstructor
	public VirtualDirectory(String name)
	{
		this(name, "NAV");
	}

	public VirtualDirectory(String name, String category) {
		super(name, category);
	}

	/**
	 * 获取导航父节点
	 * @return 导航父节点
	 */
    @JsonIgnore
	public VirtualDirectory getParent() {
		return parent;
	}

	/**
	 * 设置导航父节点
	 * @param parent 导航父节点
	 */
	public void setParent(VirtualDirectory parent) {
		this.parent = parent;
	}

	/**
	 * 获取子导航
	 * @return 子导航
	 */
	public List<VirtualDirectory> getChild() {
		return child;
	}

	/**
	 * 设置 子导航
	 * @param child 集合
	 */
	public void setChild(List<VirtualDirectory> child) {
		if(child != null)
			for(VirtualDirectory n : child) n.setParent(this);

		this.child = child;
	}

	@Override
	public void loadChild(Collection  collection) {
		Assert.notNull(collection);

		List<VirtualDirectory> navs = new ArrayList<>(collection.size());

		for(Object c : collection)
			if(c instanceof VirtualDirectory)
				navs.add((VirtualDirectory)c);

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
    public List<VirtualDirectoryComponent> getComponents() {
        return components;
    }

	/**
	 * 设置航下的所有挂载组件信息
	 * @param components 航下的所有挂载组件信息集合
	 */
    public void setComponents(List<VirtualDirectoryComponent> components) {

       if(components != null)
           for(VirtualDirectoryComponent c : components) {
               c.setNavigation(this);
           }

        this.components = components;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }
}
