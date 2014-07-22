package com.lemsun.form.controls;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-12-3
 * Time: 下午4:46
 */
public class BaseButtonControl extends AbstractLemsunControl {

	public BaseButtonControl() {
		super("LmsButton");
	}


    private String text;
    private String lmsCommand;

    /**
     * 获取按钮的显示文字
     * @return 文字
     */
    public String getText() {
        return text;
    }

    /**
     * 设置按钮的显示文字
     * @param text 显示文字
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 获取执行命令的内容
     */
    public String getLmsCommand() {
        return lmsCommand;
    }

    /**
     * 设置执行命令的内容
     */
    public void setLmsCommand(String lmsCommand) {
        this.lmsCommand = lmsCommand;
    }

    @Override
    protected Element toElement() {
        Element el = super.toElement();

        if(StringUtils.isNotEmpty(getText()))
            el.setAttribute("Content", getText());

        if(StringUtils.isNotEmpty(getLmsCommand()))
            el.setAttribute("LmsCommand", getLmsCommand());

        return el;
    }

    @Override
    public void loadVIProperty(Element el) {
        super.loadVIProperty(el);

        setText(getPropertyString(el, "Text", null));
        setLmsCommand(getPropertyString(el, "ButtonEventType", null));

//        if(StringUtils.equals(actionType, "保存")) {
//            setScript("按钮点击(" + getName() + ", '表格.保存(C2)')");
//        }

    }
}
