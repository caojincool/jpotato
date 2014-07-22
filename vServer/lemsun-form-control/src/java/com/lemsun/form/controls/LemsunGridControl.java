package com.lemsun.form.controls;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-12-5
 * Time: 下午2:53
 */
public class LemsunGridControl extends AbstractLemsunControl {

	public LemsunGridControl() {
		super(RootControl.lemsun, "LmsGridControl");
	}


	private String componentPid;


	/**
	 * 获取当前表格挂载的数据组件
	 * @return 数据组件PID
	 */
	public String getComponentPid() {
		return componentPid;
	}

	/**
	 * 设置当前组件挂载的表格
	 * @param componentPid 主键
	 */
	public void setComponentPid(String componentPid) {
		this.componentPid = componentPid;
	}

	@Override
	protected Element toElement() {
		Element el = super.toElement();

		if(StringUtils.isEmpty(componentPid))
			componentPid = getRoot().getCurrentResource().getPid();

		el.setAttribute("TableResourcePid", componentPid);

		return el;
	}
}
