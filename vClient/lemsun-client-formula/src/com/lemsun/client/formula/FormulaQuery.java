package com.lemsun.client.formula;

/**
 * 公式查询对象
 * Created by dpyang on 2014/6/4.
 */
public class FormulaQuery {

    private String adate;

    private int dataCount = -1;
    private int start = -1;
    private int limit = -1;

    /**
     * 插入日期
     * @return
     */
    public String getAdate() {
        return adate;
    }

    /**
     * 插入日期
     * @param adate
     */
    public void setAdate(String adate) {
        this.adate = adate;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * 返回是否要返回数据的总数
     */
    public boolean isReturnDataCount()
    {
        return dataCount == 1;
    }

    /**
     * 获取当前是否是分页查询
     */
    public boolean isPageRequest() {
        return start != -1 && limit != -1;
    }
}
