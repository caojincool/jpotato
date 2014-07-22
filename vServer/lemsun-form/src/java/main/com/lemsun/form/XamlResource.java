package com.lemsun.form;

/**
 * 使用Xaml描述的单据资源. 一般来说xaml单据资源会使用在wpf平台, 或者是Silverlight中. <br/>
 * 尽量避免使用各平台特定的东西
 * User: Xudong
 * Date: 12-12-4
 * Time: 下午9:05
 */
public class XamlResource extends FormResource {
	public XamlResource(String name, String category) {
		super(name, category);
	}
}
