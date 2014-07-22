package com.lemsun.web.manager.model.component;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 12-12-13
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
public class CreateResource {

    //组件名称
    private String compontentname;
    //组件类型
    private String category;
    //父级PID
    private String parentid;
    public String getCompontentname() {
        return compontentname;
    }

    public void setCompontentname(String compontentname) {
        this.compontentname = compontentname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}
