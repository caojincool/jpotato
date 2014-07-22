/**
 * 5代网页端数据数据包
 * 储存5代表格信息
 */
package com.lemsun.client.core.data;

import com.lemsun.client.core.LemsunException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 二维的数组数据
 * User: 宗旭东
 * Date: 13-4-23
 * Time: 下午7:33
 */
public class ArrayData {

    protected ArrayList<TableRow> data;
    protected ArrayList<String> cols;

    public static final int ASC = 1;
    public static final int DESC = 2;

    private String adate;
    private int colCount;
    private int rowCount;
    private int[] colTypes;
    private ArrayDataComparator comparator;

    public ArrayData() {

    }


    public ArrayData(Object value) {
        if(value == null) return;

        data = new ArrayList<>(1);
        cols=new ArrayList<>(1);

        TableRow tableRow=new TableRow();
        tableRow.add(0,value);
        data.add(tableRow);

        cols.add("value");
    }


    public ArrayDataComparator getComparator() {
        return comparator;
    }

    /**
     * 获取当前数据的操作日期
     */
    public String getAdate() {
        return adate;
    }

    /**
     * 设置当前数据的操作日期
     */
    public void setAdate(String adate) {
        this.adate = adate;
    }

    /**
     * 设置列数
     */
    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    /**
     * 设置当前数据的行数
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * 获取当前数据列的类型
     */
    public int[] getColTypes() {
        return colTypes;
    }

    /**
     * 设置当前数据列的类型
     */
    public void setColTypes(int[] colTypes) {
        this.colTypes = colTypes;
    }

    /**
     *  返回数据
     */
    public List<TableRow> getData() {
        return data;
    }

    /**
     *  返回列集合
     */
    public List<String> getCols() {
        return cols;
    }


    /**
     * 返回列数
     * @return 列数
     */
    public int getColCount() {
        return colCount;
    }
    /**
     *
     * @return 行数
     */
    public int getRowCount() {
        return rowCount;
    }


    public void setData(ArrayList<TableRow> data) {

        if(data != null)
        for(TableRow r : data) {
            r.setTable(this);
        }

        this.data = data;
    }

    public void setCols(ArrayList<String> cols) {
        this.cols = cols;
    }

    /**
     * 获取0行0列的信息
     */
    public Object getValue() {

        if(data != null && data.size() > 0) {
            return data.get(0);
        }
        return null;
    }

    public Object getValue(String col) {
        if(null != getValue()){
            return data.get(0).get(col);
        }
        return null;
    }

    /**
     * 根据行号和列名获取对应的数据
     * @param row 行号
     * @param col 列名
     * @return 行号和列名获取对应的数据
     */
    public Object getValue(int row,String col){
        if (null != data && null != data.get(row)){
            return data.get(row).get(getIndex(col));
        }
        return null;
    }

    private int getIndex(String col){
        for (int i=0;i<cols.size();i++){
            if(cols.get(i).equalsIgnoreCase(col)){
                return i;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayData)) return false;

        ArrayData arrayData = (ArrayData) o;

        ArrayList<String> oCols = arrayData.cols;

        if(cols.size() != oCols.size())
            return false;


        for(int i=0; i<cols.size(); i++) {
            if(!StringUtils.equals(cols.get(i), oCols.get(i)))
                return false;
        }


        if (!data.equals(arrayData.data)) return false;

        return true;
    }


    public void orderBy(String col, int order) {

        int colIndex = getCols().indexOf(col);

        if(colIndex == -1) throw new LemsunException("只能对查询列进行排序!");

        this.comparator  = new ArrayDataComparator(this, col, order);

        Collections.sort(getData(), this.getComparator());

    }


    @Override
    public int hashCode() {
        int result = data.hashCode();
        result = 31 * result + cols.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ArrayData{" +
                "cols=" + cols + ", " +
                "data=" + data + "" +
                '}';
    }



    public class ArrayDataComparator implements Comparator {

        private ArrayData table;
        private String col;
        private int order;
        private int colIndex;


        public ArrayDataComparator(ArrayData table, String col, int order) {
            this.table = table;
            this.col = col;
            this.order = order;
            colIndex = table.getCols().indexOf(col);
        }


        public ArrayData getTable() {
            return table;
        }

        public String getCol() {
            return col;
        }

        public int getOrder() {
            return order;
        }

        public int getColIndex() {
            return colIndex;
        }

        @Override
        public int compare(Object o1, Object o2) {

            if(!(o1 instanceof  TableRow && o2 instanceof TableRow)) {
                return getOrder() == ASC ? -1 : 1;
            }

            TableRow o1Row = (TableRow) o1;
            TableRow o2Row = (TableRow) o2;


            if(o1Row != null) {

                int c = o1Row.compareTo(o2Row);

                return getOrder() == ASC  ? c :-1;

            }
            else {
                return -1;
            }

        }
    }

}
