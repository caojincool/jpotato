package com.lemsun.form.controls;

import com.lemsun.core.IConvertToXaml;
import com.lemsun.core.IResource;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.StringWriter;

/**
 * 单据展示的根节点
 * User: Xudong
 * Date: 12-12-3
 * Time: 下午3:28
 */
public class RootControl extends AbstractContainerControl implements IConvertToXaml {

	private IResource currentResource;

	public RootControl(IResource currentResource) {
		super("LmsRoot");
		this.currentResource = currentResource;
	}


	public static final Namespace lemsun = Namespace.getNamespace("lemsun", "clr-namespace:Lemsun.Controls;assembly=Lemsun.Controls");
	public static final Namespace base = Namespace.getNamespace("http://schemas.microsoft.com/winfx/2006/xaml/presentation");
	public static final Namespace xaml = Namespace.getNamespace("x", "http://schemas.microsoft.com/winfx/2006/xaml");

	/**
	 * 获取当前的组件节点显示的XML文档. 每次将产生一个新的xml文档对象.
	 * @return xml 文档对象
	 */
	public Document getDoc() {

		Document doc = new Document();
		Element el = toElement();
		el.setNamespace(lemsun);
		el.addNamespaceDeclaration(base);
		el.addNamespaceDeclaration(xaml);
		el.addNamespaceDeclaration(lemsun);
		doc.setRootElement(el);

		return doc;
	}


	/**
	 * 获取当前对象的 xaml 描述语言信息
	 */
	public String toXaml() {

		try {
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			StringWriter writer = new StringWriter();
			outputter.output(getDoc(), writer);
			writer.flush();
			return writer.toString();
		} catch (IOException e) {

		}

		return null;

	}

	public IResource getCurrentResource() {
		return currentResource;
	}

	public void setCurrentResource(IResource currentResource) {
		this.currentResource = currentResource;
	}


	@Override
	public RootControl getRoot() {
		return this;
	}

	@Override
	public void setRoot(RootControl root) {

	}
}
