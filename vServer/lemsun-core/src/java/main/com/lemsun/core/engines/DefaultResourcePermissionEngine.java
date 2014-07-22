package com.lemsun.core.engines;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResource;
import com.lemsun.core.Permission;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-20
 * Time: 下午4:01
 */
public class DefaultResourcePermissionEngine implements IResourcePermissionEngine {

	private IScriptEngine engine;

	public DefaultResourcePermissionEngine(IScriptEngine engine)
	{
		this.engine = engine;
	}

	private void init() {

	}

	@Override
	public Permission CheckByScript(IAccount account, String script, IResource resource) {

		if(account != null && !StringUtils.isEmpty(script) && resource != null)
		{

		}

		return Permission.Unkonw;
	}
}
