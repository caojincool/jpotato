package com.dp.ui.model;

import java.util.List;

/**
 * Created by dpyang on 2014/11/18.
 */
public class FolderInfo {
    private String id;
    private String text;
    private String iconCls;
    private boolean checked;
    private boolean state;

    private List<FolderInfo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<FolderInfo> getChildren() {
        return children;
    }

    public void setChildren(List<FolderInfo> children) {
        this.children = children;
    }
}
