package com.lemsun.data.tables;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lemsun.core.BaseCategory;
import com.lemsun.core.IConvertToXaml;
import com.lemsun.core.IResource;
import com.lemsun.core.jackson.CustomJsonDateSerializer;
import com.lemsun.data.connection.AbstractDbResource;
import com.lemsun.data.connection.DbConfigResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

/**
 * 数据表资源挂载
 * User: Xudong
 * Date: 12-11-14
 * Time: 上午10:20
 */

public class TableResource extends AbstractDbResource {


	public static final String TYPE = "table";

	@PersistenceConstructor
	protected TableResource(String name, String parentPid) {
		super(name, BaseCategory.DBTABEL_4.getCategory());
		setParentPid(parentPid);
	}


	public TableResource(String name, String parentPid, DbConfigResource resource)
	{
		super(name, BaseCategory.DBTABEL_4.getCategory(), resource);
		setParentPid(parentPid);
		face = new TableFace();
	}

    protected TableResource()
    {
        super(null, BaseCategory.DBTABEL_4.getCategory(), null);

    }

	private String code;
	private int cate = TableCategory.YEAR;
	private int useBy = TableUseBy.DEFAULT;
	private List<TableColumn> columns;
	private Date enableTime = new Date();
	private boolean enable = true;
	private int version = 5;
	private TableFace face;
	private String dbtable;
    private String currentTimeFormat;

	@Transient
	private IConvertToXaml control;


	@Transient
	private IResource parent;

	/**
	 * 返回表格代码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置表格代码
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * 表格类型. 如年表, 月表, 日表
	 * @return 返回表格类型
	 */
	public int getCate() {
		return cate;
	}

	/**
	 * 设置表格类型
	 * @param cate 表格类型
	 */
	public void setCate(int cate) {
		this.cate = cate;
	}

	/**
	 * 表格使用类型, 如账簿, 单据等
	 * @return 返回表格使用类型
	 */
	public int getUseBy() {
		return useBy;
	}

	/**
	 *
	 * @param useBy 设置表格使用类型
	 */
	public void setUseBy(int useBy) {
		this.useBy = useBy;
	}

	/**
	 *
	 * @return 返回表格当前的列
	 */
	public List<TableColumn> getColumns() {
		return columns;
	}

	/**
	 *
	 * @param columns 表格列
	 */
	public void setColumns(List<TableColumn> columns) {
		this.columns = columns;
	}

	/**
	 * 开启时间
	 */
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public Date getEnableTime() {
		return enableTime;
	}

	/**
	 *
	 * @param enableTime 开启时间
	 */
	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}

	/**
	 *
	 * @return 是否开启
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 *
	 * @param enable 是否开启
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 *
	 * @return 对应的版本信息
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * 设置版权信息. 目前只支持 4 - 四代, 5 - 五代格式
	 * @param version 版本信息
	 */
	public void setVersion(int version) {
		this.version = version;
	}


    /**
     * 获取当前表格设置的界面信息, 比如表头合并, 表格文字. 等
     */
	public TableFace getFace() {
		return face;
	}

    /**
     * 设置表格的界面信息
     * @param face 界面信息
     */
	public void setFace(TableFace face) {
		this.face = face;
	}

	/**
	 * 获取表格对象在数据库中的物理表名
	 * @return 获取真实的数据表名称
	 */
	public String getDbtable() {
		return dbtable;
	}

	/**
	 * 设置表格对象在数据库中的表名
	 * @param dbtable 真实的数据表名称
	 */
	public void setDbtable(String dbtable) {



		this.dbtable = dbtable;
	}

    /**
     * 获取表格所指定的时间显示的字符形式, yyyyMMdd
     * @return 字符格式
     */
    public String getCurrentTimeFormat() {
        return currentTimeFormat;
    }

    /**
     * 设置表格当前时间的字符格式, yyyyMMdd
     * @param currentTimeFormat yyyyMMdd
     */
    public void setCurrentTimeFormat(String currentTimeFormat) {
        this.currentTimeFormat = currentTimeFormat;
    }

    /**
	 *
	 * 获取当前组件的父节点
	 */
	public IResource getParent() {
		return parent;
	}

	/**
	 * 设置当前组件的父节点
	 * @param parent 当前组件的父节点
	 */
	public void setParent(IResource parent) {
		this.parent = parent;
	}

    /**
     * 获取一个可以进行转换成 xaml 的对象. 这个对象基本是表格显示的界面
     * @return 转换对象
     */
	public IConvertToXaml getControl() {
		return control;
	}

    /**
     * 设置表格的转换 xaml 对象
     * @param control 控件对象
     */
	public void setControl(IConvertToXaml control) {
		this.control = control;
	}

    /**
     * 获取表格指定的名称列
     * @param col 名称
     * @return 列对象
     */
    public TableColumn getColumnByName(String col) {
        for (TableColumn c : columns) {
            if(StringUtils.equals(c.getCol(), col))
                return c;
        }
        return null;
    }
}
