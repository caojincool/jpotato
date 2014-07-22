package com.lemsun.client.core.lmstable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemsun.client.core.model.LemsunResource;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 表格组件模型
 * User: 宗旭东
 * Date: 13-10-25
 * Time: 下午4:19
 */
public class Table5Resource extends LemsunResource {


    public static final String TYPE = "table5";

    private String code;
    private int cate;
    private Date dateTime;
    private boolean enable;
    private Date enableTime;
    private List<Column> columns;
    private Date createTime;
    private String dbtable;


    private DataModel dataModel;

    private TableFace face;

    private Column keyColumn;


    private Column codeColumn;


    private HashMap<String, String> formulas;
    private Date actionDate;


    /**
     * 获取当前表格的操作日期
     */
    public Date getActionDate() {
        return actionDate;
    }

    /**
     * 设置当前表格的操作日期
     */
    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    /**
     * 获取表格编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置表格编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取表格类型
     */
    public int getCate() {
        return cate;
    }

    /**
     * 设置表格类型
     */
    public void setCate(int cate) {
        this.cate = cate;
    }



    /**
     * 获取表格时间
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * 设置表格时间
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 获取表格是否启用
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * 设置表格是否启用
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * 获取启用时间
     */
    public Date getEnableTime() {
        return enableTime;
    }

    /**
     * 设置启用时间
     */
    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    /**
     * 获取表格的列信息
     */
    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    /**
     * 表格皮肤信息
     */
    public TableFace getFace() {
        return face;
    }


    public void setFace(TableFace face) {
        this.face = face;
    }


    /**
     * 表格创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取物理表格名称
     *
     * @return 物理表格名称
     */
    public String getDbtable() {
        return dbtable;
    }

    /**
     * 设置物理表格名称, 这个是在表格创建的时候尤Rpository 设置. 只能设置一� 用户不能修改这个属�
     *
     * @param dbtable 设置物理表格名称
     */
    public void setDbtable(String dbtable) {
        this.dbtable = dbtable;
    }

    /**
     * 获取数据模型
     */
    @JsonIgnore
    public DataModel getDataModel() {
        return dataModel;
    }

    /**
     * 设置数据模型
     */
    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    /**
     * 获取当前表格所携带的触发公式, 建作为触发键, 值作为触发内容
     */
    public HashMap<String, String> getFormulas() {
        return formulas;
    }

    /**
     * 设置表格携带的触发公式
     */
    public void setFormulas(HashMap<String, String> formulas) {
        this.formulas = formulas;
    }

    /**
     * 获取当前的表格Key字段
     */
    @JsonIgnore
    public Column getKeyColumn() {

        if (keyColumn == null && getColumns() != null)
            for (Column c : getColumns()) {
                if (c.getCategory() == ColumnCategory.KEY) {
                    keyColumn = c;
                    break;
                }

            }

        return keyColumn;
    }


    /**
     * 获取表格列中的编码字�
     */
    @JsonIgnore
    public Column getCodeColumn() {
        if (codeColumn == null && getColumns() != null)
            for (Column c : getColumns()) {
                if (c.getCategory() == ColumnCategory.CODE) {
                    codeColumn = c;
                    break;
                }

            }

        return codeColumn;
    }

    /**
     * 获取一个字段类�
     * @param name
     * @return
     */
    public Column getColumn(String name)
    {
        for(Column c : getColumns())
        {
            if(StringUtils.equalsIgnoreCase(c.getCol(), name))
            {
                return c;
            }
        }

        return null;
    }

    /**
     * 使用用户的操作日期, 返回对数据进行操作的日期条件
     * @param adate 操作日期
     * @return 数据的条件
     */
    public String getAdateSelect(Date adate)
    {
        return TableCategory.getAdateSelect(getCate(), adate);
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        Object t = super.clone();
        return t;
    }

}
