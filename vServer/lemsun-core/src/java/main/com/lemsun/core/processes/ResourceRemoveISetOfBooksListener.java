package com.lemsun.core.processes;

import com.lemsun.core.events.ResourceEvent;
import com.lemsun.core.service.ISetOfBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * 删除组件事件监听器
 * User: 刘晓宝
 * Date: 13-10-28
 * Time: 下午3:39
 */
@Service
public class ResourceRemoveISetOfBooksListener implements ApplicationListener<ResourceEvent> {


    private ISetOfBooksService setOfBooksService;

    @Autowired
    public ResourceRemoveISetOfBooksListener(
                                      ISetOfBooksService setOfBooksService) {


        this.setOfBooksService = setOfBooksService;
    }

    @Override
    public void onApplicationEvent(ResourceEvent resourceEvent) {
        if (resourceEvent.getResource()==null) throw new RuntimeException("删除的组件不能为空");

        if (resourceEvent.getAction()== ResourceEvent.Action.BefreDelete){
            setOfBooksService.removeSobResourceByResource(resourceEvent.getResource());
        }
    }
}
