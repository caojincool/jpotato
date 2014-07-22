package com.lemsun.data.tables;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * 表格列的描述
 * User: Xudong
 * Date: 12-11-14
 * Time: 上午10:56
 */
public class TableColumn {

    private String col;
    private String name;
    private String name1;
    private String name2;
    private boolean unique = false;
    private boolean readOnly = false;
    private boolean isNull = true;
    private String check = "";
    private String formatter;
    private int category;
    private boolean visible = true;
    private int align;
    private int decimal = 0;
    private String defaultvalue;
    private int width = -1;
    private String filter;


    private int length = 0;
    private String flag;
    private int sectionLine;
    private int precision;
    private int negativeRed;

    public static final int AlignLeft = 1;
    public static final int AlignRight = 2;
    public static final int AlignCenter = 3;

    /**
     * @param col      列的物理名称, 唯一的
     * @param category 列的数据类型
     */
    @PersistenceConstructor
    public TableColumn(String col, int category) {
        this.col = col;
        this.category = category;
    }

    /**
     * @return 返回列名
     */
    public String getCol() {
        return col;
    }

    /**
     * @param col 列名
     */
    public void setCol(String col) {
        this.col = col;
    }

    /**
     * @return 返回列的别名
     */
    public String getName() {
        return name == null ? getCol() : name;
    }

    /**
     * @param name 列别名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 名称第一层
     */
    public String getName1() {
        return name1 == null ? getName() : name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    /**
     * @return 名称第二层
     */
    public String getName2() {
        return name2 == null ? getName1() : name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    /**
     * @return 返回列的使用类型
     */
    public int getCategory() {
        return category;
    }

    /**
     * @param category 设置列的类型
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * @return 返回列显示出的长度
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width 类的显示长度
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return 内容的长度
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length 内容的长度
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return 当前列是否唯一
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * @param unique 当前列是否唯一
     */
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    /**
     * @return 内容检查
     */
    public String getCheck() {
        return check;
    }

    /**
     * @param check 内容检查
     */
    public void setCheck(String check) {
        this.check = check;
    }

    /**
     * 获取表头是否可见
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * 设置表头是否可见
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * 获取当前列是否可以编辑
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * 设置当前列是否可以编辑
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * 获取对齐方式
     */
    public int getAlign() {
        return align;
    }

    /**
     * 设置对齐方式
     */
    public void setAlign(int align) {
        this.align = align;
    }

    /**
     * 获取显示小数点的位数
     */
    public int getDecimal() {
        return decimal;
    }

    /**
     * 设置小数点的位数
     */
    public void setDecimal(int decimal) {
        this.decimal = decimal;
    }

    /**
     * 获取过滤条件
     */
    public String getFilter() {
        return filter;
    }

    /**
     * 设置过滤条件
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * 获取帮助表示
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置帮助标识
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }


    public int getSectionLine() {
        return sectionLine;
    }

    public void setSectionLine(int sectionLine) {
        this.sectionLine = sectionLine;
    }

    /**
     * 获取小数位数
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * 设置小数位数
     */
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getNegativeRed() {
        return negativeRed;
    }

    public void setNegativeRed(int negativeRed) {
        this.negativeRed = negativeRed;
    }


    /**
     * 获取当前列是否是系统列
     */
    public boolean isSystem() {
        String col = getCol();
        return col.startsWith("_") && !isCodeCol();
    }

    public boolean isCodeCol() {
        return StringUtils.equals("_code", col) || StringUtils.equals("A1", col);
    }

    /**
     * 获取表头行数显示的文字. row 是行
     */
    public String getText(int row) {
        if (row == 0) {
            return col;
        }
        if (row == 1) return getName();
        if (row == 2) return getName1();
        if (row == 3) return getName2();
        return null;
    }

    private static char[] ens = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'};

    /**
     * 获取给定列数的列名. 这个列名是按照字母排列
     *
     * @param index 列的值
     * @return 列的名称
     */
    public static String getColumnCode(int index) {
        StringBuilder col = new StringBuilder();

        while (index >= 0) {

            if (col.length() > 0) {
                index--;
                if (index < 0) break;
            }

            col.append(ens[index % 26]);

            index = index / 26;
        }
        return col.reverse().toString();
    }

    /**
     * 字段是否允许为空
     */
    public boolean isNull() {
        return isNull;
    }

    /**
     * 字段是否允许为空
     */
    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    /**
     * 字段默认值
     */
    public String getDefaultvalue() {
        return defaultvalue;
    }

    /**
     * 字段默认值
     */
    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    /**
     * 格式化字符串
     */
    public String getFormatter() {
        return formatter;
    }

    /**
     * 格式化字符串
     */
    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}