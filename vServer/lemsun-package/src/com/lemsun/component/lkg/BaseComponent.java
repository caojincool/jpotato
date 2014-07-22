package com.lemsun.component.lkg;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-6-18
 * Time: 下午5:36
 */
public class BaseComponent {
    private String pid;
    private String name;
    private boolean system;
    private String category;


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
