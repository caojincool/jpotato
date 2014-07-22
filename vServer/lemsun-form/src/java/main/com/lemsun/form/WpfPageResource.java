package com.lemsun.form;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.LemsunResource;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-6
 * Time: 下午3:12
 */
public class WpfPageResource extends FormResource {


    public static String TYPE = "wpf";

    public WpfPageResource(){
        super(null,BaseCategory.WPF_SKIN.getCategory());
    }



    public WpfPageResource(String name) {
        super(name, BaseCategory.WPF_SKIN.getCategory());
    }

    public WpfPageResource(LemsunResource resource){
        super(resource.getName(),resource.getCategory());
        setId(resource.getId());
        setPid(resource.getPid());
        setRemark(resource.getRemark());
        setStrParams(resource.getStrParams());
        setCreateUser(resource.getCreateUser());
        setUpdateTime(resource.getUpdateTime());
        setSystem(resource.isSystem());
        setBusinessCode(resource.getBusinessCode());
    }


    private String beforeScript;
    private String endScript;
    private String formula;
    private Set<WpfPageParam> startParams;
    private boolean showToolbar;
    private boolean synchronismWindowSize;
    private int openMode;
    private int showLocation;

    /**
     * 获取当前组件在调用执行显示前执行的脚本
     */
    public String getBeforeScript() {
        return beforeScript;
    }

    /**
     * 设置组件在调用前执行的脚本
     */
    public void setBeforeScript(String beforeScript) {
        this.beforeScript = beforeScript;
    }

    /**
     * 获取组件结束完成后设置的脚本
     */
    public String getEndScript() {
        return endScript;
    }

    /**
     * 设置组件在结束后执行的脚本
     */
    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    /**
     * 获取执行公式脚本
     */
    public String getFormula() {
        return formula;
    }

    /**
     * 设置执行公式脚本
     */
    public void setFormula(String formula) {
        this.formula = formula;
    }

    /**
     * 组件开始参数
     *
     * @return 开始参数
     */
    public Set<WpfPageParam> getStartParams() {
        return startParams;
    }

    /**
     * 设置组件开始参数
     *
     * @param startParams 开始参数
     */
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
}
