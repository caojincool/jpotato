package com.lemsun.form.controls;

import com.lemsun.core.SpringContextUtil;
import org.jdom2.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * User: Xudong
 * Date: 12-12-5
 * Time: 下午2:14
 * 控件创建工厂. 在通过对四代的控件名称寻找控件容器中注册的控件名称.
 */
public class ControlFactory implements ApplicationContextAware {


    private static ApplicationContext context;

    private String[] resources;

    /**
     * 获取控件拓展的名称
     */
    public String[] getResources() {
        return resources;
    }

    /**
     * 设置控件拓展的资源名称
     * @param resources 资源名称
     */
    public void setResources(String[] resources) {
        this.resources = resources;
    }

	/**
	 * 根据名称创建一个控件对象
	 * @param name  控件的名称
	 * @return 控件对象
	 */
	public static AbstractLemsunControl createControl(String name) {
//        ControlFactory factory = SpringContextUtil.getBean(ControlFactory.class);
//        ApplicationContext context = factory.context;
		if(!context.containsBean(name)) {
			return new UnknownControl(name);
		}

		return (AbstractLemsunControl) context.getBean(name);
	}


	public static AbstractLemsunControl createVIControl(String name) {
		return createControl(name.substring(0, name.indexOf(",")));
	}


	public static AbstractLemsunControl createVIControl(Element el) {
		return createVIControl(el.getAttribute("type").getValue());
	}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        String[] rs;

        if(resources == null) {
            rs = new String[1];
        }
        else
        {
            rs = new String[resources.length + 1];
            System.arraycopy(resources, 0, rs, 1, resources.length);
        }

        rs[0] = "applicationContext-controls.xml";

        GenericXmlApplicationContext temp = new GenericXmlApplicationContext(rs);
        temp.setParent(applicationContext);

        context = temp;
    }
}
