package com.lemsun.core;

import com.lemsun.core.service.IAccountCoreService;
import com.lemsun.core.service.IAccountManager;
import org.springframework.context.ApplicationContext;

/**
 * 系统主机环境对象. 保存系统的基础信息. 以及获取常用的全局对象
 *
 * User: 宗旭东
 * Date: 13-9-23
 * Time: 上午11:22
 */
public abstract class Host {

    private LocalDbConfig dbConfig;
    private String key;
    private LocalFileUploadConfig fileUploadConfig;
    private LocalFileUploadConfig imageUploadConfig;
    private LocalFileUploadConfig reporterUploadConfig;
    private String clientUrl;
    private static Host instance;
    protected Host() {
    }


    private Register register;


    /**
     * 获取数据库配置信息
     */
    public LocalDbConfig getDbConfig() {
        return dbConfig;
    }

    /**
     * 修改数据库配置信息
     */
    public void setDbConfig(LocalDbConfig dbConfig) {
        this.dbConfig = dbConfig;
    }


    /**
     * 获取当前线程中得账号
     */
    public IAccount getCurrentAccount() {
        return getCurrentManager().getAccount();
    }



    public IAccountManager getCurrentManager() {
        return getContext().getBean(IAccountCoreService.class).getCurrentAccountManager();

    }


    /**
     * 获取全局秘钥
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置全局秘钥
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取当前的应用的注册信息
     * @return
     */
    public Register getRegister()
    {
        return register;
    }


    /**
     * 在对象容器中获取指定类型的对象模型
     * @param type 类型
     * @param <T> 类型对象
     * @return 对象
     */
    public  <T> T getBean(Class<T> type) {
        return SpringContextUtil.getBean(type);
    }

    /**
     * 重启当前的应用程序
     */
    public abstract void restart();

    /**
     * 停止当前的应用程序
     */
    public abstract void stop();

    /**
     * 获取主机中得对象容器
     */
    public ApplicationContext getContext() {
        return SpringContextUtil.getApplicationContext();
    }


    /**
     * 获取主机环境
     * @return
     */
    public static Host getInstance() {
        if(instance==null){
        instance=SpringContextUtil.getBean(Host.class);
        }
        return instance;
    }

    /**
     * 获取文件组件上传限制
     * @return 文件组件上传限制
     */
    public LocalFileUploadConfig getFileUploadConfig() {
        return fileUploadConfig;
    }
    /**
     * 设置上传文件类型
     * @return
     */
    public void setFileUploadConfig(LocalFileUploadConfig fileUploadConfig) {
        this.fileUploadConfig = fileUploadConfig;
    }

    /**
     * 服务器默认解析客户端地址
     * @return
     */
    public String getClientUrl() {
        return clientUrl;
    }

    /**
     * 服务器默认解析客户端地址
     * @param clientUrl
     */
    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }

    /**
     * 获取填报组件上传限制
     * @return 填报组件上传限制
     */
    public LocalFileUploadConfig getReporterUploadConfig() {
        return reporterUploadConfig;
    }

    /**
     * 获取图片组件上传限制
     * @return 图片组件上传限制
     */
    public LocalFileUploadConfig getImageUploadConfig(){return imageUploadConfig;}

    public void setImageUploadConfig(LocalFileUploadConfig imageUploadConfig) {
        this.imageUploadConfig = imageUploadConfig;
    }

    public void setReporterUploadConfig(LocalFileUploadConfig reporterUploadConfig) {
        this.reporterUploadConfig = reporterUploadConfig;
    }
}
