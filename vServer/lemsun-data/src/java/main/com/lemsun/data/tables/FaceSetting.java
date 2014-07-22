package com.lemsun.data.tables;

/**
 * 描述一个表格皮肤的设置的值
 * User: Xudong
 * Date: 12-11-16
 * Time: 下午3:22
 */
public class FaceSetting {

	private String range;
	private int name;
	private Object value;


	/**
	 *
	 * @return 获取设置当前区域
	 */
	public String getRange() {
		return range;
	}


	public void setRange(String range) {
		this.range = range;
	}

	/**
	 *
	 * @return 设置的名称
	 */
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	/**
	 *
	 * @return 设置的值
	 */
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
