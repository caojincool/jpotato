package com.lemsun.web.manager.controller.component;

import com.lemsun.auth.BaseAccount;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.*;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.service.ICategoryService;
import com.lemsun.core.service.INavigationService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.web.manager.controller.model.component.NavigationComponents;
import com.lemsun.web.manager.controller.model.component.NavigationTreeStore;
import com.lemsun.web.manager.controller.model.query.ComponentQuery;
import com.lemsun.web.manager.controller.model.query.NavComponentQuery;
import com.lemsun.web.model.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 导航管理
 * User: Lucklim
 * Date: 12-11-19
 * Time: 下午12:46
 */
@Controller
@RequestMapping("component/navigation")
public class NavigationController {

    @Autowired
    private INavigationService navigationService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private JsonObjectMapper mapper;
    @Autowired
    protected IAccountService accountService;
    private final Logger log = LoggerFactory.getLogger(NavigationController.class);
    @RequestMapping(value = {"", "/", "view"})
    public ModelAndView view(HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView("component/navigationView");
        request.getSession().setAttribute("head",1);
        request.getSession().setAttribute("left",10);
        return view;
    }

    @RequestMapping(value ="viewiframe")
    public ModelAndView viewiframe() throws IOException {

        Collection<ICategory> categorys = categoryService.getAll();

        Map<String, String> names = new HashMap<>();

        for (ICategory c : categorys) {
            names.put(c.getCategory(), c.getName());
        }

        String json = mapper.writeValueAsString(names);

        ModelAndView view = new ModelAndView("component/navigationBody");
        view.addObject("categorys", json);

        return view;
    }
    /**
     * 获取导航树形集合
     *
     * @return 导航树形集合
     */
    @ResponseBody
    @RequestMapping("list/get")
    public List<NavigationTreeStore> getData() {
        Navigation navigation = navigationService.getRoot();

        return (new NavigationTreeStore()).convertNavigation(navigation, true);
    }

    /**
     * 获取组件数据
     */
//    @RequestMapping("list/getfornavgation")
//    public ResponseEntity<Page<NavigationResourceChecked>> getPageListForNavgation(ComponentQuery query, String navigatpid) throws Exception {
//        Page<NavigationResourceChecked> data = navigationService.getPageingForNavgation(query, navigatpid);
//
//        return new ResponseEntity<>(data);
//    }

    /**
     * 分页获取某导航下的所有挂载组件集合
     *
     * @param query 分页条件
     * @return 此导航下的所有挂载组件集合
     */
    @ResponseBody
    @RequestMapping("/list/getncdata")
    public ResponseEntity<Page<NavigationComponent>> getNavigationComponentData(NavComponentQuery query) {

        Page<NavigationComponent> result = navigationService.getNComponentPagging(query);


        for(NavigationComponent webskin: result.getContent()){
            BaseAccount account=null;
            try{
                account=accountService.getAccountByAccount(webskin.getCreateUser());
            }catch (Exception e){
                log.info("异常数据",e.getMessage());
            }
            if(account==null){
                account=new BaseAccount();
            }
            webskin.setCreateUser(account.getShowName());

        }
        ResponseEntity<Page<NavigationComponent>> responseEntity = new ResponseEntity<>();

        responseEntity.setSuccess(true);
        responseEntity.setEntity(result);

        return responseEntity;
    }

    /**
     * 删除导航挂载组件信息
     *
     * @param request 要删除的导航挂载组件objid
     * @return 删除后的导航挂载组件集合
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("list/delete")
    public ResponseEntity<String> removeNavigationComponent(HttpServletRequest request){

        ResponseEntity<String> responseEntity=new ResponseEntity<>();

        String message = "ok";
        boolean success = true;

        try {
            NavigationComponent nc=mapper.readValue(request.getInputStream(),NavigationComponent.class);
            navigationService.removeComponent(nc);
        } catch (Exception e) {
            message = "由于" + e.getMessage() + "操作失败";
            success = false;
        }
        responseEntity.setSuccess(success);
        responseEntity.setMessage(message);

        return responseEntity;
    }

    /**
     * 添加导航
     *
     * @param filename   导航名称
     * @param fileremark 导航备注
     * @param parentpid  导航父objid
     * @return 导航集合
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "addwj", method = RequestMethod.POST)
    public ResponseEntity addWJ(String filename, String fileremark, String parentpid) throws Exception {

        ResponseEntity responseEntity = new ResponseEntity();
        Navigation navigation = parentpid.trim().equals("") ? navigationService.getRoot() : navigationService.get(parentpid);

        Navigation newNav = new Navigation(filename);
        //navigation 为 null 时，表示添加的是根节点
        newNav.setParent(navigation);
        newNav.setName(filename);
        newNav.setRemark(fileremark);
        navigationService.create(newNav);

        responseEntity.setSuccess(true);
        return responseEntity;
    }

    /**
     * 添加挂载组件
     *
     * @return 此导航的挂载组件集合
     * @throws Exception
     */
    @RequestMapping(value = "addzj", method = RequestMethod.POST)
    public ResponseEntity<String> addNavigation(HttpServletRequest request) {

        ResponseEntity<String> responseEntity=new ResponseEntity<>();

        String message = "挂载成功!";
        boolean success = true;

        NavigationComponents ncs= null;
        try {
            ncs = mapper.readValue(request.getInputStream(),NavigationComponents.class);

            //先删除原导航下的所有组件
            for (NavigationComponent nc:ncs.getComponents())
                navigationService.addComponent(nc);

        } catch (IOException e) {
            message = "由于" + e.getMessage() + "操作失败!";
            success = false;
        } catch (Exception e) {
            message = "由于" + e.getMessage() + "操作失败";
            success = false;
        }
        responseEntity.setSuccess(success);
        responseEntity.setMessage(message);

        return responseEntity;
    }

    /**
     * 删除导航信息
     *
     * @param pid 导航pid
     */
    @ResponseBody
    @RequestMapping(value = "delete")
    public void deleteNavigation(String pid) {
        navigationService.remove(pid);
    }

    /**
     * 过滤已经存在的导航组件
     * @param query
     * @return
     */
    @RequestMapping(value = "get/checkedNavResource")
    public ResponseEntity<Page<LemsunResource>> pageResponseEntity(ComponentQuery query) throws Exception {

        //获取当前节点中已经存在的resource
        List<String> list=navigationService.getAllResourcePidByNavId(query.getNavPid());
        query.setPids(list);
        IResourceService resourceService= Host.getInstance().getContext().getBean(IResourceService.class);

        Page<LemsunResource> lemsunResources=resourceService.getPageing(query,LemsunResource.class);

        return new ResponseEntity<>(lemsunResources);
    }
}
