package com.lemsun.core.processes;

import com.lemsun.core.events.ResourceEvent;
import com.lemsun.core.service.INavigationService;
import com.lemsun.core.service.ISetOfBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * User: 刘晓宝
 * Date: 13-10-28
 * Time: 下午3:37
 */
@Service
public class ResourceRemoveNavigationListener implements ApplicationListener<ResourceEvent> {
    private INavigationService navigationService;


    @Autowired
    public ResourceRemoveNavigationListener(INavigationService navigationService) {

        this.navigationService = navigationService;


    }

    @Override
    public void onApplicationEvent(ResourceEvent resourceEvent) {
        if (resourceEvent.getResource()==null) throw new RuntimeException("删除的组件不能为空");

        if (resourceEvent.getAction()== ResourceEvent.Action.BefreDelete){

            navigationService.removeComponent(resourceEvent.getResource());

        }
    }
}
