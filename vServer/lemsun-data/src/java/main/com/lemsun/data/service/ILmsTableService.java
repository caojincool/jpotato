package com.lemsun.data.service;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.util.Pid;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.lmstable.*;
import com.lemsun.data.tables.TableDataQuery;
import com.lemsun.ldbc.*;
import org.springframework.data.domain.Page;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * 五代的表格体现接口
 * User: 宗旭东
 * Date: 13-6-6
 * Time: 上午10:23
 */
public interface ILmsTableService {

    /**
     * 创建表组, 并创建里面的表信息. 根据表组的类型创建表组下得子表
     *
     * @param groupResource 表组信息
     */
    public void create(Table5GroupResource groupResource) throws Exception;

    /**
     * 使用PID获取一个表格组件对象
     *
     * @param pid Pid
     * @return 如果没有返回 NULL
     */
    @Pid
    public Table5Resource getResource(String pid);

    /**
     * 根据某个表格组件获取表格对象
     *
     * @param lmsr 表格组件
     * @return 表格对象
     */
    public Table5Resource getResource(LemsunResource lmsr);


    /**
     * 使用一个表组的ID, 获取表组下的当前表格
     *
     * @param pid
     * @return
     */
    @Pid
    public LemsunResource getCurrentResource(String pid);


    /**
     * 使用用户设定的日期, 返回一个给定日期下得表格组件
     * @param pid
     * @param date
     * @return
     */
    @Pid
    public LemsunResource getCurrentResource(String pid, Date date);


    /**
     * 获取表格组件的皮肤设置文件
     *
     * @param pid PID
     * @return 如果没有返回 NULL
     */
    @Deprecated
    public String getTableFace(String pid);


    /**
     * 获取表格的数据条数
     *
     * @param query
     * @return
     */
    public long getDataCount(TableDataQuery query);

    /**
     * <b>同步方法</b>
     *
     * 获取一个表的最后的单据号.
     *
     * 如果存储表中没有这样的表格号. 那么自动增加一条记录. 并初始值为 0;
     *
     * @param pid 表组编码
     * @param adate 操作日期
     * @return 返回最后的序列号
     */
    public long getLastFormNo(String pid, String adate);

    /**
     * <b>同步方法</b>
     *
     * 更新一个表格的最大单据序列号.
     * 当前的序列号由传入的 no 值决定. 自身不需要进行自增长. 自增长的过程由业务进行处理.
     * @param pid 表组编码
     * @param adate 操作日期
     * @param no 更新序列号
     */
    public void updateLastFormNo(String pid, String adate, long no);

    /**
     * 组件删除时删除单据号
     * @param pid
     */
    public void removeLastFormNo(String pid);

    /**
     * 查询前首先根据操作日期和表组PID（如：年表 PID_N%_表类型（不足俩位补全前边加0））
     * @param query
     * @return
     */
    boolean isExistTable(TableDataQuery query);

    /**
     * 查找最近表
     * @param query
     * @return
     */
    Table5Resource queryNearestTable(TableDataQuery query);

    /**
     * 创建表组下的表格组件
     *
     * @param table
     */
    public void createTable(Table5Resource table) throws Exception;

    /**
     * 获取查询数据
     *
     * @param query 查询对象
     * @return 数据集
     */
    public TableData query(TableDataQuery query) throws Exception;


    /**
     *
     * @param groupPid
     * @param adate
     * @param cate
     * @param isCopyData
     */
    void copyTable(String groupPid, String adate, int cate, boolean isCopyData);




    /**
     * 保存一个表格组件数据
     *
     * @param pid  表格ID
     * @param data 需要保存的数据
     * @throws Exception
     */
    public void saveData(String pid, TableData data) throws Exception;


    /**
     * 更新字段的字符内容
     * @param pid
     * @param column
     * @param content
     */
    public void uploadColumnString(String pid, String column, String rowid, String content) throws Exception;

    /**
     * 更新字段的字符内容
     * @param pid
     * @param column
     * @param content
     */
    public void uploadColumnString(String pid, String column, String rowid, InputStream content) throws Exception;

    /**
     * 在指定的字段中增加一张图片
     * @param pid
     * @param column
     * @param rowid
     * @param filename
     * @param reamrk
     * @param stream
     * @throws Exception
     */
    public String uploadColumnImage(String pid, String column, String rowid, String filename, String reamrk, InputStream stream) throws Exception;

    /**
     * 在指定的字段中增加一个文件
     * @param pid 表格资源
     * @param column 字段
     * @param rowid 行号
     * @param filename 文件名称
     * @param type 文件类型
     * @param reamrk
     * @param stream
     * @throws Exception
     */
    public String uploadColumnFile(String pid, String column, String rowid, String filename, String type, String reamrk, InputStream stream) throws Exception;


    /**
     * 获取表格字段的字符内容, 如果是二进制文件返回的是Base64编码的字符流
     * @param pid
     * @param column
     * @param rowid
     * @return
     */
    public String getColumnContext(String pid, String column, String rowid) throws Exception;


    /**
     * 获取一个字段的文件流, 写入到Stream 中去
     * @param pid 资源组件
     * @param column 列
     * @param fileid 文件ID
     */
    public void getColumnFile(String pid, String column, String fileid, OutputStream stream) throws Exception;

    /**
     * 获取字段中的文件信息
     * @param pid
     * @param column
     * @return
     * @throws Exception
     */
    public List<ColumnFileInfo> getColumnFileInfos(String pid, String column, String rowid) throws Exception;



    /**
     * 表组件更新包括表结构
     * @param table 更新的表格组件
     */
    void updateResource(Table5Resource table);

    /**
     * 将表格组件的设置信息更新
     * @param pid 组件资源的PID
     * @param face 组件的皮肤
     * @param dataModel 表格的数据模型
     * @throws Exception
     */
    @Deprecated
    public void updateResource(String pid, TableFace face, DataModel dataModel) throws Exception;

    /**
     * 根据5代表组获取子表
     * @param parentPid
     * @return
     * @throws Exception
     */
    public List<Table5Resource> getTable5Resources(String parentPid) throws Exception;

    /**
     * 根据数据库配置获取5代表
     * @param dbConfigResource
     * @return
     * @throws Exception
     */
    public List<Table5Resource> getTable5Resources(DbConfigResource dbConfigResource) throws Exception;

    /**
     * 根据数据库配置获取5代表组
     * @param dbConfigResource
     * @return
     * @throws Exception
     */
    public List<Table5GroupResource> getTable5GroupResource(IDbConnectionConfig dbConfigResource) throws Exception;
    /**
     * 获取表组
     * @param pid 表组Pid
     * @return
     */
    public Table5GroupResource getGroup(String pid)throws Exception;

    /**
     * 删除表组
     * @param pid
     */
    public void deleteTableGroup(String pid)throws Exception;

    /**
     * 删除表
     * @param pid
     */
    public void deleteTable(String pid) throws Exception;



    /**
     * 查询获取分页的组件模型
     */
    Page<Table5GroupResource> getPageing(AbstractPageRequest query);


    /**
     * 获取组件的全部列
     */
    public static final int GetResourceColumnAll = 1;
    /**
     * 获取用户操作日期之后的
     */
    public static final int GetResourceColumnDown = 2;


    /**
     * 获取用户操作日期的当前组件的列
     */
    public static final int GetResourceColumnCurrent = 3;


    /**
     * 给定一个Pid, 这个pid 可以是表组的, 可以是具体的表.
     * 如果是表组, 那么返回表组下所有表的列合并后的信息, 合并后需要去除同样的列. 在合并的时候. 如果发现列的类型是 不同的.
     * 那么将列的类型设置为 (0  ColumnCategory.Unkonw ) 未知类型.
     * @param pid 表组或者表的 PID
     * @param  cate 获取类型 类型定义在当前的常量  GetResourceColumnAll, GetResourceColumnDown, GetResourceColumnCurrent
     * @return 合并后的列集合
     */
    List<Column> getResourceColumns(String pid, int cate);


    /**
     * 将传入的列集合进行比对. 找出原始列的差异. 针对 新增, 删除, 修改类型
     * 批量修改的过程中. 如果遇到表格已经具备了新增的列. 那么就忽略.
     * 如果删除过程中. 表格没有当前的列. 也忽略
     *
     *
     * @param pid 表组或者表的 PID
     * @param cate 获取类型 类型定义在当前的常量  GetResourceColumnAll, GetResourceColumnDown, GetResourceColumnCurrent
     * @param columns 组件的列集合
     */
    void updateResourceColumns(String pid, int cate, List<Column> columns);

}
