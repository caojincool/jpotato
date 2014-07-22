package com.lemsun.core.engines;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResource;
import com.lemsun.core.Permission;

/**
 * 组件用户权限判断
 * User: Xudong
 * Date: 12-10-20
 * Time: 下午3:59
 */
public interface IResourcePermissionEngine {

	/**
	 * 使用脚本检查用户对组件的权限
	 * @param account 账号对象
	 * @param script 脚本对象
	 * @param resource 资源对象
	 * @return 判断结果
	 */
	Permission CheckByScript(IAccount account, String script, IResource resource);

}
