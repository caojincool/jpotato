package com.lemsun.component.lkg.processes;

import com.lemsun.component.lkg.service.IVirtualDirectoryService;
import com.lemsun.core.IResource;
import com.lemsun.core.events.ResourceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * 组件包接听组件删除事件
 * User: 宗旭东
 * Date: 13-10-8
 * Time: 下午3:50
 */
@Service
public class ResourceRemovePackageListener implements ApplicationListener<ResourceEvent> {


    private IVirtualDirectoryService virtualDirectoryService;

    @Autowired
    public ResourceRemovePackageListener(IVirtualDirectoryService virtualDirectoryService) {
        this.virtualDirectoryService = virtualDirectoryService;
    }

    @Override
    public void onApplicationEvent(ResourceEvent resourceEvent) {

        if(resourceEvent.getAction() == ResourceEvent.Action.BefreDelete) {

            IResource resource = resourceEvent.getResource();

            if(resource == null) {

                    throw new RuntimeException("删除的组件不能为空");
            }
            virtualDirectoryService.removeComponent(resource.getPid());
        }


    }
}
