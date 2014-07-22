package com.lemsun.reporter.service;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IResource;
import com.lemsun.reporter.ReporterScriptResource;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 填报脚本组件服务类
 * Created by dpyang on 2014/5/21.
 */
public interface IReporterScriptResourceService {


    /**
     * 更新填报脚本组件
     * @param reporterScriptResource 脚本组件
     */
    void update(ReporterScriptResource reporterScriptResource);

    /**
     * 更新脚本组件内容
     * @param reporterScriptResource 脚本组件
     * @param context 内容
     */
    void updateContent(ReporterScriptResource reporterScriptResource,String context);

    /**
     * 更新脚本组件内容
     * @param pid 脚本组件pid
     * @param context 内容
     */
    void updateContent(String pid,String context) throws Exception;

    /**
     * 获取脚本组件内容
     * @param pid 脚本组件pid
     * @return 内容
     */
    String getContent(String pid);

    /**
     * 获取填报脚本组件内容
     * @param reporterScriptResource 脚本组件
     * @return 脚本内容
     */
    String getContent(ReporterScriptResource reporterScriptResource);

    /**
     * 获取填报脚本组件
     * @param pid 填报组件PID
     * @return 填报脚本组件
     */
   ReporterScriptResource get(String pid);

    /**
     * 分页获取脚本组件内容
     * @param query 查询条件
     * @param clazz
     * @param <T>
     * @return
     */
    <T extends IResource>Page<T> getPageing(AbstractPageRequest query,Class<T> clazz);

    /**
     * 移除填报脚本组件
     * @param pid 填报脚本组件内容
     */
    void remove(String pid) throws Exception;

    /**
     * 获取所有的填报脚本
     * @return
     */
    List<ReporterScriptResource> getAll();
}

