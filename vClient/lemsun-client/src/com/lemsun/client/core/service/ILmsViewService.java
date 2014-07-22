/**
 * 5代网页端服务包
 * 提供视图解析服务
 * 账户服务
 * 与服务端的远程通信服务
 * 组件服务
 * 表组服务
 * sql 查询与执行服务
 * 脚本组件服务
 */
package com.lemsun.client.core.service;

import com.lemsun.client.core.model.ImageResource;
import com.lemsun.client.core.model.WebPageResource;
import org.springframework.web.servlet.View;

/**
 * 视图服务
 * User: xudong
 * Date: 13-12-12
 * Time: 下午4:37
 */
public interface ILmsViewService {

    /**
     * 使用一个组件PID, 获取这个组件的视图. 提供给Spring MVC显示
     * @param pid 组件PID
     * @return 视图
     */
    public View getView(String pid);


    /**
     * 使用页面组件获取一个页面视图
     * @param resource 网页组件
     * @return 页面视图
     */
    public View getView(WebPageResource resource);

    /**
     * 使用图形组件获取图形视图
     * @param resource
     * @return 图形视图
     */
    public View getView(ImageResource resource);


    /**
     * 获取一个组件的附加文件的视图
     *
     * @param pid 组件
     * @param fileName 附加文件
     * @return 视图
     */
    public View getViewAttach(String pid, String fileName);

}
