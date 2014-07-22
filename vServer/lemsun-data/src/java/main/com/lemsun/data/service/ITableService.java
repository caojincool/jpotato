package com.lemsun.data.service;

import com.lemsun.core.IAccount;
import com.lemsun.core.LemsunResource;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.connection.TableSet;
import com.lemsun.data.tables.TableDataQuery;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.data.tables.TableResource;
import com.lemsun.data.viproject.FourAccount;
import com.lemsun.data.viproject.FourIndex;
import com.lemsun.data.viproject.FourTableGroup;
import com.lemsun.data.viproject.TableImportInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 表格挂载服务定义
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午4:15
 */
@Deprecated
public interface ITableService {

    /**
     * 在数据库初始化数据资源
     *
     * @param resource 数据资源
     */
    void initDbTable(TableResource resource, IAccount owner) throws Exception;

    /**
     * 保存创建一个数据表资源, 并对当前资源进行初始化
     *
     * @param resource 表格资源
     */
    void createTable(TableResource resource, IAccount owner) throws Exception;

    /**
     * 挂载4代的数据表
     *
     * @param parentPid 数据源
     * @param code      表格代码
     * @throws Exception 链接异常
     */
    void linkTable(String parentPid, String code) throws Exception;


    /**
     * 创建表格组, 在创建表格组. 会先调用初始化表格的方法. 进行数据校验检查加载等工作
     *
     * @param resource 表格对象
     * @throws Exception 创建异常
     */
    void createTableGroup(TableGroupResource resource) throws Exception;

    /**
     * 初始化表格. 填充发现有表格的数据, 或者子表缺少的内容.
     */
    void initTableGroup(TableGroupResource resource) throws Exception;

    /**
     * 获取挂载表的描述对象
     */
    TableResource get(String pid);

    /**
     * 根据组件编码获取表组信息
     *
     * @param pid 组件编码
     * @return 表组组件
     */
    TableGroupResource getTableGroupResource(String pid);

    /**
     * 获取一个表格组件的简单模型. 里面去除了皮肤, 等无关信息 <br/ >
     * <b>同时在获取表格组件. 如果当前给定的PID是一个表组, 那么就自动转换获取表组下的当前表格</b>
     */
    TableResource getSimple(String pid);

    /**
     * 获取表数据. 必须是分页形式
     */
    TableSet getTableData(TableDataQuery query) throws Exception;

    /**
     * 移除一个表格组
     *
     * @param pid
     */
    void deleteTableGroup(String pid) throws Exception;

    /**
     * 执行将制定编码的表组信息导入进5代的数据库
     *
     * @param codes
     * @param dbResource
     */
    Collection<TableImportInfo> importTableGroups(String[] codes, DbConfigResource dbResource, HttpServletRequest request) throws Exception;

    /**
     * 执行给定的指点数据库连接里面的全部4代数据表导入到5代中。
     *
     * @param dbResource
     */
    Collection<TableImportInfo> importTableGroups(DbConfigResource dbResource);

    /**
     * 根据CODE和数据库连接得到5代中导入的4代TableGroup
     *
     * @param dbConfigResource
     * @param code
     * @return
     */
    TableGroupResource getFourByCode(DbConfigResource dbConfigResource, String code);

    /**
     * 根据帐套获取4代目录
     *
     * @param dbConfigResource
     * @param accountCode
     * @param date
     * @return
     */
    FourIndex getFourIndexTree(DbConfigResource dbConfigResource, String accountCode, Date date);

    /**
     * 根据目录类型的帐套获取4代目录下的表组
     *
     * @param dbConfigResource
     * @param accountCode
     * @param date
     * @return
     */
    List<FourTableGroup> getFourTableGroupByIndex(DbConfigResource dbConfigResource, String accountCode, Date date);

    /**
     * 获取4代帐套导航信息
     */
    List<FourAccount> getFourAccount(DbConfigResource dbConfigResource, String accountname);

    /**
     * 获取一个表组下的当前表
     *
     * @param resource 表组资源
     * @return 当前表
     */
    LemsunResource getCurrentTable(TableGroupResource resource);

    /**
     * 获取5代表组下的当前表组件
     *
     * @param resource 5代表组组件
     * @return 5代表组件
     */
    TableResource getCurrentTableResource(TableGroupResource resource);

    /**
     * 获取一个表组下的当前表.
     *
     * @param pid 表组资源
     * @return 当前表
     */
    LemsunResource getCurrentTable(String pid);
}
