package com.lemsun.data.tables;

import com.lemsun.data.lmstable.*;
import com.lemsun.ldbc.ITableResource;
import com.lemsun.ldbc.TableQuery;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 12-12-22
 * Time: 上午10:29
 * 表格数据请求模型
 */
public class TableDataQuery extends TableQuery {

    private String pid;
    private Date date;

    public TableDataQuery()
    {
        super(null);
    }


    /**
     * 获取表格模型的主键
     * @return 主键
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置查询表格模型的主键
     * @param pid 主键
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取查询时间
     * @return 操作时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置操作时间
     * @param date 时间
     */
    public void setDate(Date date) {
        this.date = date;
    }


    public void setTableResource(ITableResource tableResource) {
        super.setTableResource(tableResource);
    }


    @Override
    public boolean isAdateSearch() {
        Table5Resource resource = null;
        if(getTableResource() instanceof Table5Resource)
        {
            resource = (Table5Resource)getTableResource();
        }

        if(resource == null) return false;

        return com.lemsun.data.lmstable.TableCategory.isAdateTable(resource)
                && super.isAdateSearch();

    }
}
