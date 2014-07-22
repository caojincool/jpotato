package com.lemsun.data.lmstable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * 表格的列信息
 * User: 宗旭东
 * Date: 13-6-4
 * Time: 下午5:26
 */
public class Column {

    /**
     * 当前列不同步
     */
    public static final int SyncNo = 0;
    /**
     * 当前是同步列
     */
    public static final int SyncYes = 1;

    /**
     * 当前是向下同步列
     */
    public static final int SyncDown = 2;

    private String col;
    private String name;
    private String name1;
    private String name2;
    private boolean unique = false; //唯一
    private boolean readOnly = false; //只读
    private boolean empty = true;
    private int sync = 0; //同步
    private String check; //检查
    private String formatter;
    private int category; //类型
    private boolean visible;
    private int decimal; //小数位数
    private String defaultValue; //默认值
    private int width;
    private String filter;
    private String table;
    private int length; //字段长度
    private String formula;

    private String id;
    private ColumnHelper helper;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取列的物理名称
     */
    public String getCol() {
        return col;
    }

    /**
     * 设置列的物理名称
     */
    public void setCol(String col) {
        this.col = col;
    }

    /**
     * 获取列的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置列的名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取列的扩展名称
     */
    public String getName1() {
        return name1;
    }

    /**
     * 设置列的扩展名称
     */
    public void setName1(String name1) {
        this.name1 = name1;
    }

    /**
     * 获取列的扩展名称
     */
    public String getName2() {
        return name2;
    }

    /**
     * 设置列的扩展名称
     */
    public void setName2(String name2) {
        this.name2 = name2;
    }

    /**
     * 获取当前列的数据是否唯一
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * 设置当前列的数据是否唯一
     *
     * @param unique
     */
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    /**
     * 获取当前列的数据是否只读
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * 设置当前列的数据是否只读
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * 内容是否可以为空 null
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * 设置内容是否可以为空
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    /**
     * 获取检查内容验证内容
     */
    public String getCheck() {
        return check;
    }

    /**
     *
     * @param check
     */
    public void setCheck(String check) {
        this.check = check;
    }

    /**
     * 获取内容格式显示
     */
    public String getFormatter() {
        return formatter;
    }

    /**
     * 设置内容格式显示
     */
    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }


    /**
     * 获取单元类型
     */
    public int getCategory() {
        return category;
    }

    /**
     * 设置单元类型
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * 获取是否显示
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * 设置是否显示
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * 获取小数位数
     */
    public int getDecimal() {
        return decimal;
    }

    /**
     * 设置小数位数
     */
    public void setDecimal(int decimal) {
        this.decimal = decimal;
    }

    /**
     * 获取默认值
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 设置默认值
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 获取显示宽度
     */
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * 获取显示过滤
     */
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * 获取列所在的物理表格
     */
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /**
     * 获取设置的公式
     */
    public String getFormula() {
        return formula;
    }

    /**
     * 设置数据公式
     */
    public void setFormula(String formula) {
        this.formula = formula;
    }

    /**
     * 获取帮助信息
     */
    public ColumnHelper getHelper() {
        return helper;
    }

    /**
     * 设置帮助信息
     */
    public void setHelper(ColumnHelper helper) {
        this.helper = helper;
    }

    /**
     * 判断是否是编码字段
     */
    @JsonIgnore
    public boolean isCodeCol() {
        return StringUtils.equals("Code", col)
                || StringUtils.equals("A1", col);
    }


    /**
     * 返回当前是否是扩展字段
     */
    @JsonIgnore
    public boolean isExtCol() {
        return category == ColumnCategory.Files
                || category == ColumnCategory.HTML;
    }

    /**
     * 获取当前是否是Key 字段
     */
    @JsonIgnore
    public boolean isKeyCol() {
        return category == ColumnCategory.KEY;
    }

    /**
     * 使用当前类型获取对应的数据库类型
     * @return
     */
    @JsonIgnore
    public int getSQLType()
    {
        return ColumnCategory.getSQLType(getCategory());
    }


    /**
     * 获取当前列的同步方式 0 不同步, 1 同步, 2 先前不同步
     *
     * <br/>
     * 在数据同步的时候. 如果内部是有单据编码栏目. 就需要按照单据编码进行同步.
     *
     */
    public int getSync() {

        int c = getCategory();
        if(c == ColumnCategory.KEY
        || c == ColumnCategory.Update
        || c == ColumnCategory.Files
        || c == ColumnCategory.CODE
        || c == ColumnCategory.ADATE
        || c == ColumnCategory.FormSec
        || c == ColumnCategory.FormNO) return SyncNo;

    return sync;
}

    /**
     * 设置当前列的同步方式
     * @param sync 0 不同步, 1 同步, 2 先前不同步
     */
    public void setSync(int sync) {
        this.sync = sync;
    }

    /**
     * 比较是否相同
     * @param col
     * @return
     */
    public boolean  compare(Column col){
        if(this.getCategory()!=col.getCategory()){
            return false;
        }else if(this.getSync()!=col.getSync()){
            return false;
        }else if (this.getCategory()==col.getCategory()&&
                (this.getDecimal()!=col.getDecimal()||this.getLength()!=col.getLength())){
            return false;
        }
        return true;
    }
    /**
     * 将数据转换成符合当前列的数据
     * @param value
     * @return
     */
    public Object convert(Object value) {

        if(value == null) return null;

        int c = getCategory();

        if(c == ColumnCategory.ADATE)
        {
            if(value instanceof Date) return DateFormatUtils.format((Date)value, "yyyyMMdd");
        }
        else if(c == ColumnCategory.INT)
        {
            if(value instanceof String) return Integer.parseInt((String)value);
        }
        else if(c == ColumnCategory.TEXT)
        {
            if(!(value instanceof String)) return value.toString();
        }
        return value;
    }

}
