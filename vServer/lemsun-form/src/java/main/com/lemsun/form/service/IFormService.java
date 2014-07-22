package com.lemsun.form.service;

import com.lemsun.core.formula.IFunctionStatement;
import com.lemsun.core.service.IResourceOperaterService;
import com.lemsun.form.FormResource;
import com.lemsun.form.InnerFunctionDefines;
import com.mongodb.gridfs.GridFSDBFile;

import java.io.InputStream;
import java.util.List;

/**
 * 单据服务接口
 * User: Xudong
 * Date: 12-10-17
 * Time: 下午3:17
 */
public interface IFormService<T extends FormResource> extends IResourceOperaterService<T> {

    /**
     * 获取单据组件的内容
     * @param resource 单据组件
     * @return 单据组件的内容
     */
    String getContext(T resource);

    /**
     * 更新组件的内容数据
     * @param resource 单据组件
     * @param context 组件的内容信息
     */
    void update(T resource, String context);


    /**
     * 保存函数对象
     * @param funtion
     */
    void insertStatement(IFunctionStatement funtion);

    /**
     * 更新函数对象
     * @param funtion
     */
    void updateStatement(IFunctionStatement funtion);

    /**
     * 删除
     * @param name
     */
    void dateteStatement(String parentpid,String name);
    /**
     * 移除组件下函数
     * @param parentpid
     */
    void removeStatement(String parentpid);

    /**
     * 获取组件下所有函数
     * @param parentpid
     * @return
     */
    List<InnerFunctionDefines> getStatementsByParentpid(String parentpid);

    /**
     * 获取函数
     * @param name 函数名
     * @return
     */
    InnerFunctionDefines getStatement(String parentpid,String  name);

    /**
     * 返回组件下所有格式化后函数
     * @param parentpid
     * @return
     */
    String formatFunciton(String  parentpid);


    /**
     * 获取显示组件的附件
     * @param pid
     * @param filename
     * @param filetype
     * @return
     */
    GridFSDBFile getAttach(String pid, String filename, String filetype);

    /**
     * 更新显示组件的附件
     * @param pid
     * @param filename
     * @param filetype
     */
    void updateAttach(String pid, String filename, String filetype, InputStream stream);

    /**
     * 移除组件的附件
     * @param pid
     * @param filename
     * @param filetype
     */
    void removeAttach(String pid, String filename, String filetype);
}
