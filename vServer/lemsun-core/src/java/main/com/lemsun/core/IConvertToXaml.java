package com.lemsun.core;

/**
 * 定义一个接口可以将当前对象转换成 xaml 组件格式
 * User: Xudong
 * Date: 12-12-7
 * Time: 下午4:46
 */
public interface IConvertToXaml {

	/**
	 * 开始转换成xaml
	 * @return xaml 格式
	 */
	String toXaml();

}
