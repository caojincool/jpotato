package com.lemsun.ldbc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
public class TableRow extends ArrayList<Object> {

    private TableData table;

    @JsonIgnore
    public TableData getTable() {
        return table;
    }

    @JsonIgnore
    void setTable(TableData table) {
        this.table = table;
    }

    /**
     * 获取当前行的信息
     */
    public int getStatus(int statusIndex) {
        Object value = get(statusIndex);

        if(value == null)
            return RowStatus.Unchanged;

        if(value instanceof Number)
            return ((Number) value).intValue();

        return NumberUtils.toInt(value.toString());
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


    @JsonIgnore
    public String getCode() {

        int codeIndex = getTable().getCodeIndex();

        return codeIndex == -1 ? null : getAsString(getTable().getCodeIndex());

    }


    @JsonIgnore
    public boolean  isNewCode() {
        String code = getCode();
        return StringUtils.startsWith(code, "新");
    }

    @JsonIgnore
    public void setCode(String code) {
        set(getTable().getCodeIndex(), code);
    }
    @JsonIgnore
    public void setCode(int code) {
        set(getTable().getCodeIndex(), code);
    }

}
