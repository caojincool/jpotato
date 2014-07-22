package com.lemsun.form.web;

import com.lemsun.form.WebPageResource;
import org.springframework.web.servlet.View;

/**
 * 网页视图显示引擎
 * User: Xudong
 * Date: 12-10-29
 * Time: 上午11:25
 */
public interface IViewEngine {

	/**
	 * 创建一个可以渲染的视图
	 * @return 返回视图
	 */
	View createView(WebPageResource resource);

	/**
	 * 获取一个视图. 获取的方法. 可以支持缓存的快速的创建视图
	 * @return 视图对象
	 */
	View getView(WebPageResource resource);
}
