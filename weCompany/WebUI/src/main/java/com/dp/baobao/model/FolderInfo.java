package com.dp.baobao.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dpyang on 2014/11/18.
 */
public class FolderInfo {
    private String id;
    private String text;
    private String iconCls;
    private boolean checked;
    private String state;
    private Map<String,String> attributes;
    private List<FolderInfo> children;

    public FolderInfo(){}

    public FolderInfo(String id,String text,String state){
        this.id=id;
        this.text=text;
        this.state=state;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<FolderInfo> getChildren() {
        return children;
    }

    public void setChildren(List<FolderInfo> children) {
        this.children = children;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
