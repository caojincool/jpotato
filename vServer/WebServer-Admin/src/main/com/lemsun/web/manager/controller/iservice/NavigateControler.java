package com.lemsun.web.manager.controller.iservice;

import com.lemsun.core.Navigation;
import com.lemsun.core.NavigationComponent;
import com.lemsun.core.service.INavigationService;
import com.lemsun.web.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 导航信息对外访问接口
 * User: 宗旭东
 * Date: 13-5-17
 * Time: 下午12:07
 */
@Controller("interfaceNavigateControler")
@RequestMapping("/interface/{plateform}/navigate")
public class NavigateControler {

    @Autowired
    private INavigationService navigationService;


    /**
     * 获取导航树的根节点.
     * @return
     */
    @RequestMapping("root/get")
    public ResponseEntity<Navigation> getNavTree() {
        return new ResponseEntity<>(navigationService.getRoot());
    }

    /**
     * 获取导航节点下的组件
     * @param pid
     * @return
     * @throws Exception
     */
    @RequestMapping("components/get")
    public ResponseEntity<List<NavigationComponent>> getNavComponents(String pid) throws Exception {
       List<NavigationComponent> data = navigationService.getAllComponent(pid);
        return new ResponseEntity<>(data);
    }
}
