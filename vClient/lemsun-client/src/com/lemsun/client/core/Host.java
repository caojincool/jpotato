/**
 * 5代网页端核心包
 * 主要包含视图解析
 * 脚本处理.
 * 公式解析
 */
package com.lemsun.client.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 主机环境信息
 * User: 宗旭东
 * Date: 13-2-22
 * Time: 下午6:39
 */
public class Host implements ApplicationContextAware {

    private static ApplicationContext context;
    private String code;
    private String key;
    private String serverHost;
    private String serverPort;
    private String serverPath;
    private IPlateformInstance plateform;
    private static Host host;


    private Host() {
    }

    /**
     * 获取环境的容器
     * 可通过该容器获取服务类
     * 例如:scope.host.getContext().getBean(com.lemsun.client.core.service.ISqlRunnerService);
     * 注意 getBean()方法的参数必须是服务接口的全路径
     * @return spring环境容器
     */
    public ApplicationContext getContext() {
        return context;
    }

    /**
     * 静态获取环境的容器
     * @return spring环境容器
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 获取当前主机实例
     * @return 当前主机实例
     */
    public static Host getInstance()
    {
        return host;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        this.host = applicationContext.getBean(Host.class);
    }

    //读取配置文件
    @Value("#{setting['plateform.pid']}")
    protected void setCode(String code) {
        this.code = code;
    }

    @Value("#{setting['plateform.key']}")
    protected void setKey(String key) {
        this.key = key;
    }

    @Value("#{setting['server.host']}")
    protected void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    @Value("#{setting['server.port']}")
    protected void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    @Value("#{setting['server.path']}")
    protected void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    /**
     * 获取当前服务主机地址
     * @return 当前服务主机地址
     */
    public String getServerHost() {
        return serverHost;
    }

    /**
     * 获取当前服务主机端口
     * @return 当前服务主机端口
     */
    public String getServerPort() {
        return serverPort;
    }

    /**
     * 获取当前服务主机路径
     * @return 当前服务主机端口
     */
    public String getServerPath() {
        return serverPath;
    }

    /**
     * 获取平台的代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取平台密码
     */
    public String getKey() {
        return key;
    }

    private String httpAddress;

    /**
     * 获取服务器的通信地址
     */
    public String getHttpAddress() {
        if(StringUtils.isEmpty(httpAddress))
            httpAddress = "http://" + serverHost + (StringUtils.isEmpty(serverPort) ? "" : (":" + serverPort)) + "/" + serverPath;
        return httpAddress;
    }

    /**
     * 获取平台的实例对象
     */
    public IPlateformInstance getPlateform() {
        return plateform;
    }

    /**
     * 设置平台的实例对象
     * @param plateform 平台对象
     */
    public void setPlateform(IPlateformInstance plateform) {
        this.plateform = plateform;
    }
}
