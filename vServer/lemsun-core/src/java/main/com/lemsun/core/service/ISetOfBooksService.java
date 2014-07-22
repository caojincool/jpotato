package com.lemsun.core.service;

import com.lemsun.core.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 帐套服务类
 * User: dp
 * Date: 13-7-24
 * Time: 上午8:47
 */
public interface ISetOfBooksService {

    /**
     * 新增一个帐套
     *
     * @param setOfBooks
     * @throws Exception
     */
    void insert(SetOfBooks setOfBooks) throws Exception;

    /**
     * 保存一个帐套信息
     *
     * @param setofbooks 帐套信息
     */
    void save(SetOfBooks setofbooks) throws Exception;

    /**
     * 获取一个帐套信息
     *
     * @param id 帐套编码
     * @return
     */
    SetOfBooks getSetOfBooks(String id) throws Exception;

    /**
     * 返回所有的帐套信息
     *
     * @return
     */
    List<SetOfBooks> getSetOfBooks();

    /**
     * 根据帐套编码移除帐套
     * 同时移除与之相关的关联组件与账户
     * @param id 帐套编码
     */
    void remove(String id);

    /**
     * 增加一个帐套帐号关系
     * @param soba
     */
    void insertAccount(SetOfBooksAccount soba) throws Exception;

    /**
     * 增加帐套下的组件
     *
     * @param sobr
     */
    void insertResource(SetOfBooksResource sobr) throws Exception;

    /**
     * 根据帐套编码删除一个组件该帐套下的所有组件
     *
     * @param sid
     */
    void removeSobResourceBySid(String sid) throws Exception;

    /**
     * 移除帐套下的组件
     *
     * @param sobr
     */
    void removeSobResource(SetOfBooksResource sobr);

    /**
     * 移除某个组件对应帐套关系
     *
     * @param lmsResource
     */
    void removeSobResourceByResource(IResource lmsResource);

    /**
     * 从帐套账户表中移除某个账户
     *
     * @param soba
     */
    void removeSobAccount(SetOfBooksAccount soba);

    /**
     * 移除某个账户下对应的帐套信息
     * @param account
     */
    void removeSobAccountByAccount(IAccount account);

    /**
     * 返回某个账户下对应的帐套
     *
     * @param account
     * @return
     */
    List<SetOfBooks> getSetOfBooksByAccount(IAccount account);

    /**
     * 返回某个组件对应的帐套
     *
     * @param resource
     * @return
     */
    List<SetOfBooks> getSetOfBooksByResource(IResource resource) throws Exception;

    /**
     * 获取某个帐套下的所有组件编码
     *
     * @param sid 帐套编码
     * @return
     */
    public List<String> getSobResourcePids(String sid) throws Exception;


    /**
     * 获取一个帐套下的所有账户名称
     *
     * @param sid 帐套编码
     * @return
     */
    public List<String> getSobAccounts(String sid) throws Exception;

    /**
     * 分页返回某个帐套下的账户列表
     * @param request
     * @param sid 帐套id
     * @return
     * @throws Exception
     */
    public Page<SetOfBooksAccount> getSetOfBooksAccountBySid(IPageQuery request, String sid) throws Exception;

    /**
     * 分页返回某个帐套下的组件列表
     *
     * @param request
     * @return
     */
    public Page<SetOfBooksResource> getSetOfBooksResourceBySid(IPageQuery request, String sid) throws Exception;
}
