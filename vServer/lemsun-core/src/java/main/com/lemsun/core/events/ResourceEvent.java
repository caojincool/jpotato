package com.lemsun.core.events;

import com.lemsun.core.IResource;
import org.springframework.context.ApplicationEvent;

/**
 * 组件操作事件对象, 如果监听这个事件后可以抛出异常来进行回滚
 * User: 宗旭东
 * Date: 13-9-29
 * Time: 下午4:23
 */
public class ResourceEvent extends ApplicationEvent {

    private IResource resource;
    private Action action;
    private IResource oldResource;

    public ResourceEvent(Object source, IResource resource, Action action) {
        super(source);
        this.resource = resource;
        this.action = action;
    }


    /**
     * 创建组件操作类型事件对象
     * @param source
     * @param resource
     * @param action
     * @param oldResource
     */
    public ResourceEvent(Object source, IResource resource, Action action, IResource oldResource) {
        super(source);
        this.resource = resource;
        this.action = action;
        this.oldResource = oldResource;
    }

    /**
     * 获取组件对象
     */
    public IResource getResource() {
        return resource;
    }

    /**
     * 获取组件操作类型
     */
    public Action getAction() {
        return action;
    }

    /**
     * 获取组件操作之前的类型
     */
    public IResource getOldResource() {
        return oldResource;
    }


    /**
     * 获取组件操作类型
     */
    public enum Action {
        Delete,
        New,
        Edit,
        /**
         * 开始正式删除组件时触发. 尽量将失败的可能性比较大得放在之前执行
         */
        BefreDelete,
    }

}
