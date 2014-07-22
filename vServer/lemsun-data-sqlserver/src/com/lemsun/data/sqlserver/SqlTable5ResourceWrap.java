package com.lemsun.data.sqlserver;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemsun.data.lmstable.Column;
import com.lemsun.data.lmstable.ColumnCategory;
import com.lemsun.data.lmstable.Table5Resource;
import com.lemsun.data.lmstable.TableCategory;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 包装一个表格资源, 提供模板使用
 * User: 宗旭东
 * Date: 13-7-23
 * Time: 下午5:59
 */
public class SqlTable5ResourceWrap {

    private Table5Resource resource;
    private List<Column> syncColumn;
    private List<Column> syncBigColumn;
    private boolean sycn = false;
    private boolean sycnDown = false;
    private List<Column> nonKeyColumns;
    public SqlTable5ResourceWrap(Table5Resource resource) {
        this.resource = resource;
        getSyncColumns();
    }

    /**
     * 获取表格组件对象
     */
    public Table5Resource getResource()
    {
        return this.resource;
    }

    /**
     * 获取物理表格
     */
    public String getDbTable()
    {
        return resource.getDbtable();
    }


    /**
     * 获取列的信息
     */
    public List<Column> getColumns()
    {
        return resource.getColumns();
    }

    /**
     * 获取单据序号列
     * @return
     */
    public Column getFormSec(){
        for( Column col:resource.getColumns()){
            if(col.getCategory()== ColumnCategory.FormSec){
                return col;
            }
        }
        return null;
    }
    /**
     * 获取需要同步的列集合
     */
    public synchronized List<Column> getSyncColumns()
    {
        if(syncColumn == null)
        {
            List<Column> cols = resource.getColumns();

            syncColumn = new ArrayList<>();

            for(Column c : cols) {
                if(c.getSync() != Column.SyncNo) syncColumn.add(c);

                if(c.getSync() == Column.SyncYes) sycn = true;
                if(c.getSync() == Column.SyncDown) sycnDown = true;

            }

        }

        return syncColumn;
    }

    /**
     * 获取需要同步的大字段列集合
     */
    public synchronized List<Column> getSyncBigColumn()
    {
        if(syncBigColumn == null)
        {
            List<Column> cols = resource.getColumns();
            syncBigColumn = new ArrayList<>();
            for(Column c : cols) {
                if(c.isExtCol()) syncBigColumn.add(c);
            }
        }

        return syncBigColumn;
    }

    /**
     *是否有相同code列存在
     * @return
     */
    public boolean isSameCodeRecord(){
        int cate=resource.getCate();
        return TableCategory.isSameCodeRecord(cate);
    }


    /**
     * 返回是否含有同步字段
     */
    public boolean isSync()
    {
        getSyncColumns();
        return sycn;
    }


    public boolean isSycnDown()
    {
        getSyncColumns();
        return sycnDown;
    }

    /**
     * 判断一个表格是否是一张日期表格, 日期表格
     * @return
     */
    public boolean isAdate()
    {
        return TableCategory.isAdateTable(getResource());
    }

    public boolean isForm() {
        return TableCategory.isFormTable(getResource().getCate());
    }
    /**
     * 是否code是否唯一 标准表和参数表
     *
     * @return
     */
    public boolean isUniqueCode(){
        return TableCategory.isUniqueCode(getResource().getCate());
    }

    /**
     * 返回当前表格的全部操作时间节点, 比如年表格返回12个月, 月表格返回
     */
    public String[] getAllAdate()
    {
        int cate = getResource().getCate();
        Date date = getResource().getDateTime();

        if(cate == TableCategory.YEAR)
        {
            Calendar cl = Calendar.getInstance();
            cl.setTime(date);

            String[] adate = new String[12];


            cl.set(Calendar.DAY_OF_MONTH, 1);
            for(int i=0; i<12; i++)
            {
                adate[i] = DateFormatUtils.format(cl, "yyyyMMdd");
                cl.add(Calendar.MONTH, 1);
            }
            return adate;

        }
        else if(cate == TableCategory.DAY ) {

            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            cl.set(Calendar.DAY_OF_MONTH, 1);

            int days = cl.getActualMaximum(Calendar.DAY_OF_MONTH);
            String[] adates = new String[days];

            for(int i=0; i<days; i++) {
                adates[i] = DateFormatUtils.format(cl, "yyyyMMdd");
                cl.add(Calendar.DAY_OF_MONTH, 1);
            }

            return adates;

        } else if(cate == TableCategory.YEAR365 ) {

            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            int days = cl.getActualMaximum(Calendar.DAY_OF_YEAR);
            cl.set(Calendar.DAY_OF_YEAR, 1);
            String[] adates = new String[days];

            for(int i=0; i<days; i++) {

                adates[i] = DateFormatUtils.format(cl, "yyyyMMdd");
                cl.add(Calendar.DAY_OF_YEAR, 1);
            }

            return adates;

        }

        return null;

    }
    private List<Column> addColumn;
    private List<Column> delColumn;
    /**
     * 获取新增列 要求新增列 不能和以前存在列名称相同
     * @param oldCols
     * @return
     */
    @JsonIgnore
    public  synchronized List<Column>  getAddColumn(List<Column> oldCols){
        if(addColumn==null){
             addColumn=new ArrayList<>();
            for(Column col:this.getColumns()){
                boolean flag=false;
                for(Column oldCol:oldCols){
                    if(StringUtils.equalsIgnoreCase(oldCol.getCol(), col.getCol())){
                        flag=true;
                        break;
                    }
                }
                if(!flag){
                    addColumn.add(col);
                }
            }
        }
        return addColumn;
    }

    /**
     * 获取删除列
     * @param oldCols 老的表格列
     * @return
     */
    @JsonIgnore
    public synchronized List<Column> getDelColumn( List<Column> oldCols){
        if(addColumn==null){
            delColumn=new ArrayList<>();
            for(Column oldCol:oldCols){
                boolean flag=false;
                for(Column col:this.getColumns()){
                    if(StringUtils.equalsIgnoreCase(oldCol.getCol(),col.getCol())){
                        flag=true;
                        break;
                    }
                }
                if(!flag){
                    delColumn.add(oldCol);
                }
            }
        }
        return delColumn;
    }
    /**
     *获取修改列最新列
     * @param oldCols 老的表格列
     * @return
     */
    @JsonIgnore
    public List<Column> getUpdateAddColumn( List<Column> oldCols){
        List<Column> cols=new ArrayList<>();
        for(Column oldCol:oldCols){
            for(Column col:this.getColumns()){
                if(StringUtils.equalsIgnoreCase(oldCol.getCol(),col.getCol())){
                     if(!col.compare(oldCol)){
                         cols.add(col);
                     }
                    break;
                }
            }
        }
        return cols;
    }
    /**
     * 先获取修改前列
     * @param oldCols 老的表格列
     * @return
     */
    @JsonIgnore
    public List<Column> getUpdateDelColumn( List<Column> oldCols){
        List<Column> cols=new ArrayList<>();
        for(Column oldCol:oldCols){
            for(Column col:this.getColumns()){
                if(StringUtils.equalsIgnoreCase(oldCol.getCol(),col.getCol())){
                    if(!col.compare(oldCol)){
                        cols.add(oldCol);
                    }
                    break;
                }
            }
        }
        return cols;
    }
}
