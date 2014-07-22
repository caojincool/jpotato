package com.lemsun.client.core.data;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 12-12-25
 * Time: 上午9:49
 * 数据行
 */
public class TableRow extends ArrayList<Object> implements Comparable<TableRow> {

    private ArrayData table;
    private String[] cols;


    public ArrayData getTable() {
        return table;
    }

     void setTable(ArrayData table) {
        this.table = table;
    }

    public String getAsString(int index) {
        Object value = get(index);

        if(value instanceof String) {
            return (String) value;
        }

        return value.toString();
    }

    public Timestamp getAsDate(int index) {
        Object value = get(index);

        if(value instanceof java.util.Date) {
            return new Timestamp(((java.util.Date) value).getTime());
        }

        try {
            java.util.Date temp = DateUtils.parseDate(value.toString(), "yyyy-MM-dd'T'HH:mm:ss");
            return new Timestamp(temp.getTime());
        } catch (ParseException e) {
            return Timestamp.valueOf(value.toString());
        }

    }

    public int getAsInt(int index) {
        Object value = get(index);

        if(value instanceof Number) {
            return ((Number) value).intValue();
        }

        return Integer.parseInt(value.toString());
    }

    public Object get(String col){

        int index = getTable().getCols().indexOf(col);


        if(index != -1) {
            return  get(index);
        }

        return null;
    }

    @Override
    public int compareTo(TableRow o) {

        ArrayData.ArrayDataComparator comparator = table.getComparator();

        if(comparator == null) return 0;


        int index = comparator.getColIndex();
        if(index == -1) {
            return 0;
        }

        Object o1 = get(index);
        Object o2 = o.get(index);

        if(o1 instanceof Comparable && o2 instanceof Comparable) {
            return ObjectUtils.compare((Comparable)o1,(Comparable) o2);
        }
        return 0;

    }
}
