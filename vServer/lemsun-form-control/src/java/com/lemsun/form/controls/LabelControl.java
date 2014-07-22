package com.lemsun.form.controls;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;

/**
 * 文字标签控件
 * User: Xudong
 * Date: 13-1-22
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */
public class LabelControl extends AbstractLemsunControl {

    public LabelControl() {
        super("LmsLabel");
    }


    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    protected Element toElement() {
        Element el = super.toElement();

        if(StringUtils.isNotEmpty(getText()))
            el.setAttribute("Text", getText());

        return el;
    }

    @Override
    public void loadVIProperty(Element el) {

        super.loadVIProperty(el);

        setText(getPropertyString(el, "Text", null));
    }
}
