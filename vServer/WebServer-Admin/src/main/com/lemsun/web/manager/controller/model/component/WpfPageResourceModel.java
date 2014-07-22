package com.lemsun.web.manager.controller.model.component;

import com.lemsun.form.WpfPageParam;
import com.lemsun.form.WpfPageResource;

import java.util.Set;

/**
 * wpf页面组件模型
 * User: dp
 * Date: 13-5-9
 * Time: 下午3:13
 */
public class WpfPageResourceModel extends LemsunResourceModel {
    private String beforeScript;
    private String endScript;
    private String formula;
    private String context;
    private Set<WpfPageParam> startParams;

    private boolean showToolbar;
    private boolean synchronismWindowSize;
    private int openMode;
    private int showLocation;

    public String getBeforeScript() {
        return beforeScript;
    }

    public void setBeforeScript(String beforeScript) {
        this.beforeScript = beforeScript;
    }

    public String getEndScript() {
        return endScript;
    }

    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Set<WpfPageParam> getStartParams() {
        return startParams;
    }

    public void setStartParams(Set<WpfPageParam> startParams) {
        this.startParams = startParams;
    }

    /**
     * 是否显示工具条
     *
     * @return
     */
    public boolean isShowToolbar() {
        return showToolbar;
    }

    /**
     * * 是否显示工具条
     *
     * @param showToolbar
     */
    public void setShowToolbar(boolean showToolbar) {
        this.showToolbar = showToolbar;
    }

    /**
     * 是否同步窗体大小
     *
     * @return
     */
    public boolean isSynchronismWindowSize() {
        return synchronismWindowSize;
    }

    /**
     * 是否同步窗体大小
     *
     * @param synchronismWindowSize
     */
    public void setSynchronismWindowSize(boolean synchronismWindowSize) {
        this.synchronismWindowSize = synchronismWindowSize;
    }

    /**
     * 打开方式
     * 弹出窗口=1
     * 合并窗口=2
     * 模式窗口=3
     *
     * @return
     */
    public int getOpenMode() {
        return openMode;
    }

    /**
     * 打开方式
     * 弹出窗口=1
     * 合并窗口=2
     * 模式窗口=3
     */
    public void setOpenMode(int openMode) {
        this.openMode = openMode;
    }

    /**
     * 显示位置
     * 窗口居中 = 1
     * 屏幕居中 = 2
     * 随意 = 3
     *
     * @return
     */
    public int getShowLocation() {
        return showLocation;
    }

    /**
     * 显示位置
     * 窗口居中 = 1
     * 屏幕居中 = 2
     * 随意 = 3
     *
     * @param showLocation
     */
    public void setShowLocation(int showLocation) {
        this.showLocation = showLocation;
    }

    /**
     * 从前台页面的wfp组件模型封装到后台的wpf组件模型
     *
     * @return
     */
    public WpfPageResource encapseWpfResource() {
        WpfPageResource wppr = new WpfPageResource(getName());
        return encapseWpfResource(wppr);
    }

    /**
     * 封装一个已经存在的wpf组件
     */
    public WpfPageResource encapseWpfResource(WpfPageResource wpfPageResource) {

        wpfPageResource.setParentPid(getParentPid());
        wpfPageResource.setName(getName());
        wpfPageResource.setBeforeScript(getBeforeScript());
        wpfPageResource.setEndScript(getEndScript());
        wpfPageResource.setFormula(getFormula());
        wpfPageResource.setStartParams(getStartParams());
        wpfPageResource.setOpenMode(getOpenMode());
        wpfPageResource.setShowToolbar(isShowToolbar());
        wpfPageResource.setSynchronismWindowSize(isSynchronismWindowSize());
        wpfPageResource.setShowLocation(getShowLocation());
        return wpfPageResource;
    }
}
