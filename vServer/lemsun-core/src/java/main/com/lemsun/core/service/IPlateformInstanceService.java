package com.lemsun.core.service;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.PlateformException;
import com.lemsun.core.PlateformInstance;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 系统实例服务接口
 * User: gm
 * Date: 12-12-1
 * Time: 上午9:12
 */
public interface IPlateformInstanceService {

	/**
	 * 创建实例对象
	 * @param plateform 要创建的示例对象
	 */
	void create(PlateformInstance plateform);

	/**
	 * 更新系统实例对象数据
	 * @param resource 要更新的系统实例对象
	 */
	void update(PlateformInstance resource) throws Exception;

	/**
	 * 根据实例pid删除单条系统实例
	 * @param pid 系统实例pid
	 */
	void delete(String pid) throws Exception;

	/**
	 * 根据pid获取此系统实例对象
	 * @param pid  系统实例pid
	 * @return 满足条件的此系统实例对象
	 */
	PlateformInstance get(String pid);

    /**
     * 获取服务器系统实例
     * @return 系统实例
     */
    PlateformInstance getSystemInstance();

	/**
	 * 获取所有系统实例对象
	 * @return 所有实例对象集合
	 */
	List<PlateformInstance> getAllInstance();

	/**
	 * 系统实例分页
	 * @param request 页面信息
	 * @return 返回组件
	 */
	Page<PlateformInstance> getPagging(AbstractPageRequest request);

    /**
     * 获取系统实例启动的时候. 注册的票据信息
     * @param code 平台代码
     * @return 票据信息
     */
    String getPlateformToken(String code);

    /**
     * 获取实例对象.
     * @param token 登录票据
     * @return 实例对象
     */
    PlateformInstance getPlatefrmByToken(String token);


    /**
     * 系统平台登录, 平台登陆后将产生一个登录 票据提供给平台与服务器通信使用
     * @param code
     * @param password
     * @return
     */
    PlateformInstance plateformLogin(String code, String password) throws PlateformException;
}
