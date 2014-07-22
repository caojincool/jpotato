package com.lemsun.form.controls;

/**
 * 表示一个未知的控件对象
 * User: Xudong
 * Date: 12-12-5
 * Time: 下午2:22
 */
public class UnknownControl extends AbstractLemsunControl {

	public UnknownControl(String type) {
		super("UnknownControl");
	}

	private String type;


	public String getType() {
		return type;
	}
}
