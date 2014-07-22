package com.lemsun.form.controls;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * User: Xudong
 * Date: 12-12-3
 * Time: 下午2:51
 * 基本的平台控件对象, 没有子对象的单个控件. 基本控件里面实现了控件的大小, 位置, 字体, 脚本等固定信息属性
 */
public abstract class AbstractLemsunControl {

    private static final Logger log = LoggerFactory.getLogger(AbstractLemsunControl.class);

	private Namespace namespace;
	private String elName;
	private String name;
	private int width = -1;
	private int height = -1;
	private Location location;
	private String font;
	private boolean visible = true;

	private AbstractLemsunControl parent;
	private RootControl root;
    private String script;


    /**
     * 构造一个控件
     * @param namespace 名称空间
     * @param elName 节点名称
     */
	public AbstractLemsunControl(Namespace namespace, String elName) {
		this.namespace = namespace;
		this.elName = elName;
	}


    /**
     * 使用默认的名称空间构造控件
     * @param elName 节点名称
     */
	public AbstractLemsunControl(String elName) {
		this(RootControl.lemsun, elName);
	}

    /**
     * 获取当前控件生成 xaml 中的名称空间
     * @return 名称空间
     */
	public Namespace getNamespace() {
		return namespace;
	}

    /**
     * 获取父节点的对象
     * @return 控件对象
     */
	public AbstractLemsunControl getParent() {
		return parent;
	}

    /**
     * 设置控件的父节点.
     * @param parent 父节点的控件
     */
	public void setParent(AbstractLemsunControl parent) {
		this.parent = parent;
	}

    /**
     * 获取控件的宽度
     * @return 宽度值
     */
	public int getWidth() {
		return width;
	}

    /**
     * 设置控件的宽度
     * @param width 宽度
     */
	public void setWidth(int width) {
		this.width = width;
	}

    /**
     * 获取控件的高度
     * @return 高度
     */
	public int getHeight() {
		return height;
	}

    /**
     * 设置控件的高度
     * @param height 高度
     */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 获取当前控件的名称. 默认为null. 表示没有设置名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置当前控件的名称
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * 获取当前控件的坐标
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * 设置当前控件的坐标
	 * @param location 坐标
	 */
	public void setLocation(String location) {
		this.location = new Location(location);
	}

    /**
     * 获取控件使用的字体
     * @return 如果没有返回null
     */
	public String getFont() {
		return font;
	}

    /**
     * 设置控件的字体
     * @param font 字体
     */
	public void setFont(String font) {
		this.font = font;
	}


    /**
     * 获取控件是否可视
     * @return 默认为true
     */
	public boolean isVisible() {
		return visible;
	}

    /**
     * 设置控件的可视
     * @param visible true为可视, false为隐藏
     */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

    /**
     * 获取控件执行的脚本
     * @return 脚本内容
     */
    public String getScript() {
        return script;
    }

    /**
     * 设置控件的执行脚本
     * @param script 脚本内容
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
	 * 从4代的xml代码中加载属性, 继承的子控件, 如果有自己的属性. 必须覆盖这个方法. 并在调用这个方法后. 加载特点的属性
	 * @param el 节点
	 */
	public void loadVIProperty(Element el) {

        if(log.isDebugEnabled())
        log.debug("开始一个控件的数据解析, {}", this.getClass().getName());

		name = el.getAttribute("name").getValue().trim();
		width = getPropertyInt(el, "Width", width);
		height = getPropertyInt(el, "Height", height);
		String l = getPropertyString(el, "Location", null);
		if(StringUtils.isNotEmpty(l)) setLocation(l);
		setFont(getPropertyString(el, "Font", null));
		setVisible(getPropertyBool(el, "Visible", visible));
	}


	/**
	 * 获取当前节点下属性的值
	 */
	protected static String getPropertyString(Element el, String name, String defaultValue) {
		try
        {
            Element nameEl = XPathFactory.instance().compile("Property[@name='" + name + "']", Filters.element()).evaluateFirst(el);
            return nameEl == null ? null : nameEl.getTextTrim();
        }
        catch (Exception ex) {
            if(log.isErrorEnabled())

                log.error("解析XML出错 : {}, \n {}", el.toString(), ex);
            return null;
        }
	}


    /**
     * 从xml节点中获取一个int值
     */
	protected static int getPropertyInt(Element el, String name, int defaultValue) {

        String v = getPropertyString(el, name, null);

		return StringUtils.isEmpty(v) ? defaultValue : Integer.parseInt(v);
	}

	protected static boolean getPropertyBool(Element el, String name, boolean defaultValue) {
		String v = getPropertyString(el, name, null);
		return StringUtils.isEmpty(v) ? defaultValue : Boolean.parseBoolean(v.toLowerCase());
	}

	/**
	 * 将当前的控件转化成一个xml 的节点. 如果子类有自己的属性需要转化在xml 中.
     * 就需要重写这个方法. 并在调用后得到节点对象. 加载自己的属性
	 */
	protected Element toElement() {
		Element el = new Element(elName, namespace);

		if(width != -1) {
			el.setAttribute("Width", Integer.toString(width));
		}

		if(height != -1) {
			el.setAttribute("Height", Integer.toString(height));
		}

		if(StringUtils.isNotEmpty(name))
			//el.setAttribute("Name", name);
            el.setAttribute("ScriptControl.Name", name, RootControl.lemsun);

		if(location != null)
			el.setAttribute("Margin", location.toString() + ",0,0");

		if(StringUtils.isNotEmpty(font))
			el.setAttribute("FontFamily", font);

//		if(!visible)
//			el.setAttribute("IsVisible", "False");


        if(StringUtils.isNotEmpty(script))
        {
            Element scriptEl = new Element("ScriptControl.Script", RootControl.lemsun);
            scriptEl.setText(script);
            el.addContent(scriptEl);
        }

		return el;
	}

    /**
     * 获取根节点的控件
     * @return 节点的控件
     */
	public RootControl getRoot() {
		return root;
	}

	public void setRoot(RootControl root) {
		this.root = root;
	}

    /**
     * 内部坐标对象
     */
	public class Location {
		private int x;
		private int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Location(String location) {
			String[] ls = location.split(",");
			x = Integer.parseInt(ls[0].trim());
			y = Integer.parseInt(ls[1].trim());
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public String toString() {
			return x + "," + y;
		}
	}

}
