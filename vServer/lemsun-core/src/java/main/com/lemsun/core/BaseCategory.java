package com.lemsun.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-20
 * Time: 下午3:36
 */
@Document(collection = "BaseCategory")
public class BaseCategory implements ICategory {

	/**
	 * 类型根节点
	 */
	public final static BaseCategory ROOT = new BaseCategory("类型", "ROOT");

	/**
	 * 全局脚本
	 */
	public final static BaseCategory SCRIPT = new BaseCategory("全局脚本", "SCRIPT");

	/**
	 * WEB脚本
	 */
	public final static BaseCategory WEB_SCRIPT = new BaseCategory("网页脚本", "WEBSCRIPT");

	/**
	 * WPF脚本
	 */
	public final static BaseCategory WPF_SCRIPT = new BaseCategory("程序脚本", "WPFSCRIPT");

    /**
     * 任务脚本
     */
    public final static BaseCategory TASK_SCRIPT = new BaseCategory("任务脚本", "TASKSCRIPT");

    /**
     * 填报脚本
     */
    public final static BaseCategory REPORTER_SCRIPT = new BaseCategory("填报脚本", "RTSCRIPT");

    /**
	 * 关系数据库
	 */
	public final static BaseCategory DB = new BaseCategory("关系数据", "DB");

	/**
	 * 4代数据表
	 */
	public final static BaseCategory DBTABEL_GROUP_4 = new BaseCategory("4代表组", "TABELGP4");

	/**
	 * 数据
	 */
	public final static BaseCategory DBTABEL_4 = new BaseCategory("4代数据表", "DBTABEL4");

    /**
     *
     */
    public final static BaseCategory DBTABEL_5 = new BaseCategory("5代数据表", "DBTABEL5");

	/**
	 * 5代数据表
	 */
	public final static BaseCategory DBTABEL_GROUP_5 = new BaseCategory("5代表组", "TABELGP5");

	/**
	 * WPF界面
	 */
	public final static BaseCategory WPF_SKIN = new BaseCategory("程序界面", "WPFSKIN");

	/**
	 * WEB界面
	 */
	public final static BaseCategory WEB_SKIN = new BaseCategory("网页界面", "WEBSKIN");
    /**
     * 填报界面
     */
    public final static BaseCategory REPORTER = new BaseCategory("填报", "REPORTER");

	/**
	 * 资源
	 */
	public final static BaseCategory RESOURCE = new BaseCategory("资源", "RESOURCE");

	/**
	 * 图片
	 */
	public final static BaseCategory IMAGE = new BaseCategory("图片", "IMAGE");

    /**
     * 计划任务
     */
    public final static BaseCategory TASK = new BaseCategory("计划任务", "TASK");




	static {
		DBTABEL_GROUP_4.addChild(DBTABEL_4);
        DBTABEL_GROUP_5.addChild(DBTABEL_5);
		DB.addChild(DBTABEL_GROUP_4);
		DB.addChild(DBTABEL_GROUP_5);
		ROOT.addChild(DB);
		ROOT.addChild(SCRIPT);
		ROOT.addChild(WEB_SCRIPT);
		ROOT.addChild(WPF_SCRIPT);
        ROOT.addChild(REPORTER);
        ROOT.addChild(REPORTER_SCRIPT);
		ROOT.addChild(WEB_SKIN);
		ROOT.addChild(WPF_SKIN);
		ROOT.addChild(RESOURCE);
		ROOT.addChild(IMAGE);
        ROOT.addChild(TASK);
        ROOT.addChild(TASK_SCRIPT);
	}

    @Id
    private ObjectId _id;
    private String pid;
    private String name;
    private String category;
	private String remark;
    private Date updateTime;

	@DBRef
	private ICategory parent;
	@Transient
	private List<ICategory> child;
	private boolean create;

	@PersistenceConstructor
	public BaseCategory(String name, String category) {
		this(name, category, null);
	}

	/**
	 * @param name     类型名称
	 * @param category 类型种类
	 * @param parent   父类
	 */
	public BaseCategory(String name, String category, BaseCategory parent) {
	    this.name=name;
        this.category=category;
		this.parent = parent;
	}


	@JsonIgnore
	public ICategory getParent() {
		return parent;
	}

	public void setParent(ICategory category) {
		parent = category;
	}


	@Override
	public List<ICategory> getChild() {
		return child;
	}

	@Override
	public void setChild(List<ICategory> child) {
		if (child != null)
			for (ICategory c : child) {
				addChild(c);
			}
		this.child = child;
	}

	@Override
	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	@Override
	public void loadChild(Collection collection) {
		if (collection != null)
			for (Object c : collection) {
				if (c instanceof ICategory) addChild((ICategory) c);
			}
	}

	public void addChild(ICategory item) {
		if (child == null) child = new ArrayList<>();

		if (item instanceof BaseCategory) ((BaseCategory) item).parent = this;

		child.add(item);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    /**
     * 获取组件类别内置编码
     * @return 组件类别内置编码
     */
    public ObjectId getId() {
        return _id;
    }

    /**
     * 设置组件类别内置编码
     * @param id 组件类别内置编码
     */
    public void setId(ObjectId id) {
        this._id = id;
    }

    /**
     * 获取组件类别名称
     * @return 组件类别名称
     */
    public void setName(String name) {
        this.name=name;
    }

    /**
     * 设置组件类别名称
     * @return 组件类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取组件类型
     * @return 获取组件类型
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置组件类型
     * @return 设置组件类型
     */
    public void setCategory(String category) {
        this.category=category;
    }

    /**
     * 获取组件类型的编码
     * @return 组件类型编码
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置组件类型编码
     * @param pid 组件类型编码
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取组件类别更新时间
     * @return 组件类型的更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置组件更新类别时间
     * @param updateTime 组件类型更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
