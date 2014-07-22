package com.lemsun.core.service;

import com.lemsun.core.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 导航服务
 * User: Xudong
 * Date: 12-10-23
 * Time: 下午2:59
 */
public interface INavigationService{

	/**
	 * 返回pid为NAV00000000的导航信�
	 * @return 返回根节�
	 */
	Navigation getRoot();

	/**
	 * 创建保存一个导航节� 并把导航节点中的子节点跟组件一起保�
	 * @param navigation 节点
	 */
	void create(Navigation navigation) throws Exception;

	/**
	 * 根据pid获取Navigation
	 * @param pid 组件pid
	 * @return 组件
	 */
    Navigation get(String pid);

	/**
	 * 修改导航信息
	 * @param navigation 要修改的导航信息对象
	 */
    void edit(Navigation navigation);

	/**
	 * 删除导航信息，通过pid
	 * @param pid 导航信息pid
	 */
    void remove(String pid);

    /**
     * 获取某个节点的下一级子节点导航
     * @param oid 导航ObjectId
     * @return 导航节点列表
     */
    List<Navigation> getChild(String oid);

	/* -------------导航挂载组件相关--------------------------------------- */

    /**
     * 批量挂载组件
     * @param components 组件
     */
    void addComponents(List<NavigationComponent> components);

	/**
	 * 添加导航挂载组件
	 * @param component 组件信息
	 * @throws Exception
	 */
	void addComponent(NavigationComponent component) throws Exception;

    /**
     * 移除某个组件所在导航
     * @param lmsResource 组件
     */
    void removeComponent(IResource lmsResource);

    /**
     * 移除导航组件
     * @param component 导航挂载组件
     */
    void removeComponent(NavigationComponent component);

	/**
	 * 根据导航id，获取此导航的所有挂载组件信息
	 * @param navid 导航id
	 * @return 挂载组件信息
	 * @throws Exception id为空
	 */
	List<String> getAllResourcePidByNavId(String navid);

    /**
     * 根据导航id，获取此导航的所有挂载组件信息
     * @param navid
     * @return
     */
    List<NavigationComponent> getAllComponent(String navid);

    /**
     * 使用组件, 获取当前组件的在导航中得全部拥有的导航节点. 如果没有返回. 或者为空. 那么当前组件在导航中没有挂载
     * @param resource 组件
     * @return
     */
    List<Navigation> getNavigationByResource(IResource resource);

	/**
	 * 导航挂载组件分页
     * @param request 分页请求对象
     * @return 返回分页数据
	 */
	Page<NavigationComponent> getNComponentPagging(AbstractPageRequest request);
}
