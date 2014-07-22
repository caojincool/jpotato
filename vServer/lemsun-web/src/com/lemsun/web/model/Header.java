package com.lemsun.web.model;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 13-1-6
 * Time: 下午2:04
 * 头部导航节点
 */
public class Header {

    private String name;
    private boolean current;
    private HeaderTitle title;
    private String icon;
    private String href;

    public Header(HeaderTitle title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {

        for (Header h : getTitle().getHeaders()) {
            if(h != this) {
                h.setCurrent(false);
            }
        }

        this.current = current;
    }

    public HeaderTitle getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
