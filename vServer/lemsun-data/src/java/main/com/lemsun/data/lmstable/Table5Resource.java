package com.lemsun.data.lmstable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemsun.core.BaseCategory;
import com.lemsun.data.connection.AbstractDbResource;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.ldbc.ITableResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;

import java.util.*;

/**
 * 五代表格
 * User: 宗旭�
 * Date: 13-6-5
 * Time: 上午10:34
 */
public class Table5Resource extends AbstractDbResource implements ITableResource, Cloneable {


    public static final String TYPE = "table5";

    private String code;
    private int cate;
    private String reamrk;
    private Date dateTime = new Date();
    private boolean enable;
    private Date enableTime = new Date();
    private List<Column> columns;
    private Date createTime = new Date();
    private String dbtable;
    private String codeFormat;

    @Transient
    private DataModel dataModel;

    @Transient
    private TableFace face;

    @Transient
    private Table5GroupResource parent;

    @Transient
    private Column keyColumn;

    @Transient
    private Column codeColumn;


    private HashMap<String, String> formulas;


    public Table5Resource(String name, DbConfigResource dbConfig) {
        super(name, BaseCategory.DBTABEL_5.getCategory(), dbConfig);
    }

    /**
     * 获取父组�
     */
    public Table5GroupResource getParent() {
        return parent;
    }

    /**
     * 设置父组�
     */
    public void setParent(Table5GroupResource parent) {
        this.parent = parent;
        if (parent != null) {
            setParentPid(parent.getPid());
        } else {
            setParentPid(null);
        }
    }

    /**
     * 获取物理表格编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置物理表格编码
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
     * 获取表格备注
     */
    public String getReamrk() {
        return reamrk;
    }

    /**
     * 设置表格备注
     */
    public void setReamrk(String reamrk) {
        this.reamrk = reamrk;
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
     * 获取表格的命名规则
     */
    public String getCodeFormat() {
        int cate = getCate();

        if(cate == TableCategory.YEAR||cate == TableCategory.YEAR365) {
            return "%N";
        }
        else if(cate == TableCategory.MONTH) {
            return "%N%Y";
        }
        else if(cate == TableCategory.DAY) {
            return "%N%Y";
        }
        else if(cate == TableCategory.DAYRECORD) {
            return "%N%Y%R";
        } else if(cate == TableCategory.VTABLE) {
            return "XX";
        }else if(cate == TableCategory.UNIT && StringUtils.isEmpty(codeFormat)) {
            return "XX";
        }
        return codeFormat;

    }

    public void setCodeFormat(String codeFormat) {
        this.codeFormat = codeFormat;
    }

    /**
     * 获取数据模型
     */
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
                if (c.getCategory() == ColumnCategory.CODE
                        || c.getCategory() == ColumnCategory.FormNO) {
                    codeColumn = c;
                    break;
                }

            }

        return codeColumn;
    }


    /**
     * 获取当前表格同步列集合
     */
    @JsonIgnore
    public List<Column> getSyncColumns() {
        ArrayList<Column> syncColumns = new ArrayList<>();

        for(Column c : getColumns()) {
            if(c.getSync() == Column.SyncYes
                    || c.getSync() == Column.SyncDown
                    || c.getCategory() == ColumnCategory.FormNO) {
                syncColumns.add(c);
            }
        }

        return syncColumns;
    }

    /**
     * 获取当前表格不同步的字段集合
     * */
    @JsonIgnore
    public List<Column> getExcludeSyncColumn() {
        ArrayList<Column> syncColumns = new ArrayList<>();

        for(Column c : getColumns()) {
            if(c.getSync() == Column.SyncNo
                    || c.getCategory() == ColumnCategory.FormNO) {
                syncColumns.add(c);
            }
        }

        return syncColumns;
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
