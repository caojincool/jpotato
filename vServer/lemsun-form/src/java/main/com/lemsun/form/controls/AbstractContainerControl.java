package com.lemsun.form.controls;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * User: Xudong
 * Date: 12-12-3
 * Time: 下午3:30
 * 一种描述容器的组件, 如果一个控件继承了容器. 那么在转换的过程总. 子控件也一同被转换.
 */
public abstract class AbstractContainerControl extends AbstractLemsunControl {

	public AbstractContainerControl(Namespace namespace, String elName) {
		super(namespace, elName);
	}

	public AbstractContainerControl(String elName) {
		super(elName);
	}


	private List<AbstractLemsunControl> items;


	/**
	 * 获取容器内的组件
	 */
	public List<AbstractLemsunControl> getItems() {
		return items;
	}

	/**
	 * 设置容器内的组件
	 */
	public void setItems(List<AbstractLemsunControl> items) {
		this.items = items;
	}


	public void addItem(AbstractLemsunControl child) {
		Assert.notNull(child);
		if(items == null) items = new ArrayList<>();
		items.add(child);
	}

	@Override
	protected Element toElement() {
		Element el = super.toElement();
		if(!CollectionUtils.isEmpty(items)) {
			for(AbstractLemsunControl c : items) {
				el.addContent(c.toElement());
			}
		}
		return el;
	}


	/**
	 * 加载必要的属性信息. 并寻找节点下的子节点信息
	 * @param el 节点
	 */
	@Override
	public void loadVIProperty(Element el) {
		super.loadVIProperty(el);
		List<AbstractLemsunControl> child = getChildControls(el);
		if(!CollectionUtils.isEmpty(child)) {
			setItems(child);
		}
	}


	/**
	 * 获取子节点下的对象集合
	 * @param el 节点
	 * @return 节点集合
	 */
	protected static List<Element> getChildObject(Element el) {
		return XPathFactory.instance().compile("Object", Filters.element()).evaluate(el);
	}

	protected List<AbstractLemsunControl> getChildControls(Element el) {
		List<Element> objs = getChildObject(el);
		if(CollectionUtils.isEmpty(objs))
			return null;

		List<AbstractLemsunControl> controls = new ArrayList<>(objs.size());
		for(Element e : objs) {
			AbstractLemsunControl c = ControlFactory.createVIControl(e);
			c.setParent(this);
			c.setRoot(this.getRoot());
			c.loadVIProperty(e);
			controls.add(c);
		}

		return controls;
	}
}
