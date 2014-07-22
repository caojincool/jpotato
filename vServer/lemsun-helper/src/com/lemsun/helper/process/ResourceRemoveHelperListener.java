package com.lemsun.helper.process;

import com.lemsun.core.events.ResourceEvent;
import com.lemsun.helper.service.IHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * 组件删除对应帮助服务也要接听组件删除事件做一些事件
 * User: 刘晓宝
 * Date: 13-12-9
 * Time: 下午1:34
 */
@Service
public class ResourceRemoveHelperListener  implements ApplicationListener<ResourceEvent> {

    private IHelperService helperService;
    @Autowired
    public ResourceRemoveHelperListener(IHelperService helperService) {
        this.helperService = helperService;
    }

    @Override
    public void onApplicationEvent(ResourceEvent resourceEvent) {
        if (resourceEvent.getResource()==null) throw new RuntimeException("删除的组件不能为空");

        if (resourceEvent.getAction()== ResourceEvent.Action.BefreDelete){
            helperService.deleteHelpContext(resourceEvent.getResource().getPid());
        }
    }
}
