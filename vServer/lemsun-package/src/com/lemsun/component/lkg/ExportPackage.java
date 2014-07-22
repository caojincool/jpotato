package com.lemsun.component.lkg;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-6-17
 * Time: 下午3:58
 */
public class ExportPackage extends BasePackage {

    private String target;
    private String licensing;

    /**
     * 导出目标
     */
    public String getTarget() {
        return target;
    }

    /**
     * 导出目标
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * 导出许可
     */
    public String getLicensing() {
        return licensing;
    }

    /**
     * 导出许可
     */
    public void setLicensing(String licensing) {
        this.licensing = licensing;
    }
}
