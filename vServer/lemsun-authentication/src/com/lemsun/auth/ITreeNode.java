package com.lemsun.auth;

import java.util.Collection;
import java.util.List;

/**
 * 定义权限树形接口
 * 因为权限接口没有组件资源所以单独定义接口
 * User: dpyang
 * Date: 13-1-23
 * Time: 上午9:15
 */
public interface ITreeNode<T> {

    /**
     * 获取父节点
     * @return 父节点
     */
    T getParent();

    /**
     * 返回所有子节点
     * @return 所有子节点
     */
    List<T> getChild();

    /**
     * 设置所有子节点
     * @param child 子节点
     */
    void setChild(List<T> child);

    /**
     * 加载一个集合类型. 对类型进行判断加载
     * @param collection 集合对象
     */
    void loadChild(Collection collection);
}
