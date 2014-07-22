package com.lemsun.client.core.model;

/**
 * 与服务端对应的组件基本类型
 * User: dp
 * Date: 13-6-13
 * Time: 下午7:21
 */
public class BaseCategory {

    private String name;
    private String category;

    public BaseCategory(String name, String category) {
        this.category=category;
        this.name=name;
    }
    /**
     * 全局脚本
     */
    public final static BaseCategory SCRIPT = new BaseCategory("全局脚本", "SCRIPT");

    /**
     * WEB脚本
     */
    public final static BaseCategory WEB_SCRIPT = new BaseCategory("WEB脚本", "WEBSCRIPT");

    /**
     * WPF脚本
     */
    public final static BaseCategory WPF_SCRIPT = new BaseCategory("WPF脚本", "WPFSCRIPT");

    /**
     * 关系数据�
     */
    public final static BaseCategory DB = new BaseCategory("关系数据", "DB");

    /**
     * 4代数据表
     */
    public final static BaseCategory DBTABEL_GROUP_4 = new BaseCategory("4代数据表", "TABELGP4");


    /**
     * 数据�
     */
    public final static BaseCategory DBTABEL = new BaseCategory("数据", "DBTABEL");

    /**
     * 5代数据表
     */
    public final static BaseCategory DBTABEL_GROUP_5 = new BaseCategory("5代数据表", "TABELGP5");

    /**
     * WPF界面
     */
    public final static BaseCategory WPF_SKIN = new BaseCategory("WPF界面", "WPFSKIN");

    /**
     * WEB界面
     */
    public final static BaseCategory WEB_SKIN = new BaseCategory("WEB界面", "WEBSKIN");

    /**
     * 资源
     */
    public final static BaseCategory RESOURCE = new BaseCategory("资源", "RESOURCE");

    /**
     * 图片
     */
    public final static BaseCategory IMAGE = new BaseCategory("图片", "IMAGE");


    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

}
