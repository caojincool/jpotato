package com.lemsun.core.member;

import com.lemsun.core.Navigation;

import java.util.List;

/**
 * 导航查询条件
 * User: lucklm
 * Date: 13-1-6
 * Time: 上午11:53
 */
public class NavigationReturnData {
	public List<Navigation> navigationList;
	public long count;

	public List<Navigation> getNavigationList() {
		return navigationList;
	}

	public void setNavigationList(List<Navigation> navigationList) {
		this.navigationList = navigationList;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
