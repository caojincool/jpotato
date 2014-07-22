package com.lemsun.web.manager.controller.component;

import com.lemsun.auth.BaseAccount;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.*;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.service.ICategoryService;
import com.lemsun.core.service.INavigationService;
import com.lemsun.core.service.IPlateformService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.query.ComponentQuery;
import com.lemsun.web.model.*;
import com.lemsun.web.model.view.ResourceBase;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 13-1-9
 * Time: 下午2:03
 * 组件操作
 */
@Controller
@RequestMapping("component/main")
public class ComponentController {

    @Autowired
    private IResourceService service;
    @Autowired
    private IPlateformService plateformService;
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private INavigationService navigationService;

    @Autowired
    private JsonObjectMapper mapper;
    @Autowired
    protected IAccountService accountService;
    public static final String IMGROOT = "uploads"+File.separator;

    private final Logger log = LoggerFactory.getLogger(ComponentController.class);
    /**
     * 显示组件视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request,ComponentQuery query) throws IOException {
        ModelAndView view = new ModelAndView("component/main");

        //当取消创建组件或者创建完成组件返回到主页面的时候都应该移除session
        HttpSession session= request.getSession();
        session.removeAttribute("resource");
        session.removeAttribute("type");

        Collection<ICategory> categorys = categoryService.getAll();

        categorys.remove(BaseCategory.DBTABEL_4);
        categorys.remove(BaseCategory.DBTABEL_5);
        categorys.remove(BaseCategory.ROOT);
        categorys.remove(BaseCategory.DBTABEL_GROUP_4);

        Map<String, String> names = new HashMap<>();

        for (ICategory c : categorys) {
            names.put(c.getCategory(), c.getName());
        }

        if(query!=null&& StringUtils.isNotEmpty(query.getShowName())){
            List<BaseAccount> accountList=accountService.getAccountByShowName(query.getShowName());
            List<String> accounts=new ArrayList<>(accountList.size());
            for(BaseAccount account:accountList){
                accounts.add(account.getAccount());
            }
            query.setCreateAccount(accounts);
        }
        Page<LemsunResource> data = service.getPageing(query, LemsunResource.class);

        List<ResourceBase> webskinItemViewList=new ArrayList<>(data.getSize());
        for(IResource webskin: data.getContent()){
            BaseAccount account=null;
            try{
                account=accountService.getAccountByAccount(webskin.getCreateUser());
            }catch (Exception e){
                log.info("异常数据",e.getMessage());
            }
            if(account==null){
                account=new BaseAccount();
            }
            ResourceBase webskinItemView=new ResourceBase(webskin,account);
            webskinItemViewList.add(webskinItemView);

        }
        view.addObject("categorys2", categorys);
        view.addObject("categorys", names);
        view.addObject("data", new PageImpl<>(webskinItemViewList,query,data.getTotalElements()));
        view.addObject("query",query);
        session.setAttribute("head",1);
        session.setAttribute("left",0);

        return view;
    }

    /**
     * 获取组件数据
     */
    @RequestMapping("list/get")
    public ResponseEntity<Page<LemsunResource>> getPageList(ComponentQuery query) {
        Page<LemsunResource> data = service.getPageing(query, LemsunResource.class);
        return new ResponseEntity<>(data);
    }

    /**
     * 获取所有类
     */
    @RequestMapping("list/getcategory")
    public ResponseEntity<Collection<ICategory>> getAllCategory(){
        Collection<ICategory> categories = categoryService.getAll();
        return new ResponseEntity<>(categories);
    }

    /**
     * 创建组件视图
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create(String type, HttpServletRequest request) {

        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        ModelAndView view=null;
        LemsunResource resource= (LemsunResource) request.getSession().getAttribute("resource");
        request.getSession().setAttribute("type",type);
        if (resource==null){
            view = new ModelAndView("component/component_create");
            Collection<ICategory> categories = categoryService.getBaseCategoryIsCreate();
            view.addObject("categorys", categories);
        }else{
            view = new ModelAndView(
                    "redirect:"
                            + PrepareModelInteceptor.getRootPath()
                            + "component/"
                            + resource.getCategory().toLowerCase()
                            + "/add/");
        }
        return view;
    }

    /**
     * 执行创建组件
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView create(LemsunResource component, HttpServletRequest request) throws Exception {

        if (StringUtils.isEmpty(component.getCategory()))
            throw new Exception("未选择组件类型!");

        if(StringUtils.isEmpty(component.getName()))
            throw new Exception("未输入组件名称");

        IResource resource=ResourceHelder.createResource(component);
        service.create(resource);
        LemsunResource resource1=service.getBaseResource(resource.getPid());//为了获取到objectId
        request.getSession().setAttribute("resource", resource1);
        ModelAndView view = new ModelAndView(
                "redirect:"
                        + PrepareModelInteceptor.getRootPath()
                        + "component/"
                        + component.getCategory().toLowerCase()
                        + "/add/");
        return view;
    }

    /**
     * 设置组件权限页面
     */
    @RequestMapping(value = "{pid}/permission", method = RequestMethod.GET)
    public ModelAndView setPermission(HttpServletRequest request,@PathVariable String pid) {
        String url=null;
        ModelAndView view=null;
        if (request.getSession().getAttribute("resource")==null){
            url="redirect:"
                + PrepareModelInteceptor.getRootPath()
                + "component/main/create";
            view= new ModelAndView(url);
        }
        else{
            url="component/component_permission";
            view= new ModelAndView(url);
            IResource resource=service.get(pid);
            view.addObject("pid",pid);
            view.addObject("category",resource.getCategory());
        }
        return view;
    }

    /**
     * 保存权限
     */
    @RequestMapping(value = "{pid}/permission", method = RequestMethod.POST)
    public ResponseEntity<String> doPermission(HttpServletRequest request,@PathVariable String pid) throws Exception {
        LemsunResource lemsunResource=mapper.readValue(request.getInputStream(),LemsunResource.class);

        service.updatePermissions(pid,lemsunResource.getRpList());
        return new ResponseEntity<>("success");
    }

    /**
     * 编辑权限
     */
    @RequestMapping(value = "{pid}/permission/edit",method = RequestMethod.GET)
    public ModelAndView editPermission(HttpServletRequest request,@PathVariable String pid) throws IOException {
        ModelAndView view=new ModelAndView("component/component_permission_edit");
        view.addObject("type",request.getParameter("type"));
        view.addObject("pid",pid);
        return view;
    }

    /**
     * 获取某个组件的所有权�
     */
    @RequestMapping(value = "{pid}/permissions")
    public ResponseEntity<List<ResourcePermission>> listResponseEntity(@PathVariable String pid){
        List<ResourcePermission> list=service.listPermissions(pid);

        return new ResponseEntity<>(list);
    }

    /**
     * 显示组件设置导航页面
     */
    @RequestMapping(value = "{pid}/navigate", method = RequestMethod.GET)
    public ModelAndView setNavigate(@PathVariable String pid) {
        ModelAndView view = new ModelAndView("component/component_navigate");
        IResource resource=service.get(pid);
        view.addObject("pid",pid);
        view.addObject("category",resource.getCategory());
        return view;
    }

    /**
     * 执行导航挂载页面
     */
    @RequestMapping(value = "navigate/add", method = RequestMethod.POST)
    public ModelAndView addAccountInNavigation(HttpServletRequest request, String pid, String navid) throws Exception {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);
        IResource resource = service.get(pid);
        if (resource == null) throw new Exception("找不到要挂载的组件");
        NavigationComponent navigationComponent = new NavigationComponent();
        navigationComponent.setNavPid(navigationService.get(navid).getPid());
        navigationComponent.setResourcePid(pid);
        navigationService.addComponent(navigationComponent);
        String  url="redirect:"
                    + PrepareModelInteceptor.getRootPath()
                    + "component/main/"+resource.getPid()+"/create/finish";
        ModelAndView view= new ModelAndView(url);
        return view;

    }

    /**
     * 组件创建完成. 显示视图
     */
    @RequestMapping(value = "{pid}/create/finish", method = RequestMethod.GET)
    public ModelAndView createFinish(HttpServletRequest request,@PathVariable String pid) {
        HttpSession session=request.getSession();
        session.removeAttribute("resource");//清理sesion
        IResource resource = service.getBaseResource(pid);
        ModelAndView view= new ModelAndView("component/component_finish");
        view.addObject("resource",resource);
        return view;
    }
    /**
     * 进入创建组件预览图
     * @param request
     * @param pid
     * @return
     */
    @RequestMapping(value = "{pid}/imageAndDetails/add", method = RequestMethod.GET)
    public ModelAndView addImageAndDetails(HttpServletRequest request,@PathVariable String pid){
        ModelAndView view= new ModelAndView("component/add/imageAndDetails");
        IResource resource=service.get(pid);
        view.addObject("pid",pid);
        view.addObject("category",resource.getCategory());
        return view;
    }


    /**
     * 操作结果提示页面
     */
    @RequestMapping(value="operatingResults",method = RequestMethod.GET)
    public ModelAndView operatingResults(HttpServletRequest request) throws Exception {
        ModelAndView view = new ModelAndView("component/operatingResults");
        view.addObject("success",request.getParameter("success"));
        view.addObject("message",request.getParameter("message"));
        return view;
    }
    /**
     * 选择实例页面
     *
     * @return 系统实例集合界面
     */
    @RequestMapping("{pid}/instanceChoose")
    public ModelAndView instanceChoose
    (@PathVariable String pid) {
        ModelAndView view=new ModelAndView("component/instanceChoose");
        List<Plateform> result = plateformService.getAllCategory();
        view.addObject("categorylist",result);
        view.addObject("pid",pid);
        return view;
    }

    /**
     * 在默认客户端预览组件
     * @param pid
     * @return
     */
    @RequestMapping("defaultPreView/{pid}")
    public ModelAndView defaultPreView
            (@PathVariable String pid) {
        ModelAndView view=new ModelAndView("component/common/defaultPreView");
        view.addObject("clientUrl", Host.getInstance().getClientUrl()+"/"+pid);
        return view;
    }

     /**
     * 获取组件树
     */
    @ResponseBody
    @RequestMapping("categoryTree/get")
    public CompTreeNode getData() {
        ICategory baseCategory = categoryService.getRoot();
        CompTreeNode node= new CompTreeNode();
        loadCategory(baseCategory,node);
        return  node ;
    }
    /**
     * 查询函数组件下分类树组装
     * @param category
     * @param node
     * @return
     */
    private List<CompTreeNode> loadCategory(ICategory category, CompTreeNode node) {
        List<CompTreeNode> extTreeNodeList=new ArrayList<>();
      //  ExtTreeNode node= new ExtTreeNode();
        node.setId(category.getPid());
        node.setText(category.getName());
        node.setLeaf(false);
        node.setRemark(category.getCategory());
        List<CompTreeNode> childNodeList=new ArrayList<>();
        node.setChildren(childNodeList);

        extTreeNodeList.add(node);
        List<ICategory> child = category.getChild();
        if(child != null) {
            for(ICategory c : child) {
                CompTreeNode childNode= new CompTreeNode();
                loadCategory(c,childNode);
                childNodeList.add(childNode);
            }
        }
        if(!StringUtils.equalsIgnoreCase(category.getCategory(),"ROOT")){
            if(StringUtils.contains(category.getCategory(), BaseCategory.SCRIPT.getCategory())){
                List<String> scriptTypes=service.distinctScriptTypebyCategory(category.getCategory());
                int i=0;
                if(scriptTypes!=null){
                    for(String str:scriptTypes){
                        CompTreeNode childNode= new CompTreeNode();
                        childNode.setId(i+++category.getCategory());
                        childNode.setText(str);
                        childNode.setLeaf(false);
                        childNode.setRemark(category.getCategory());
                        childNodeList.add(childNode);
                        List<LemsunResource> resources= service.queryByCategoryAndScriptType(category.getCategory(),str);
                        List<ExtTreeNode> childNodeList2=new ArrayList<>();
                        if(resources != null) {
                            for(LemsunResource c : resources) {
                                CompTreeNode childNode2= new CompTreeNode();
                                childNode2.setId(c.getPid());
                                childNode2.setText(c.getName());
                                childNode2.setLeaf(true);
                                childNode2.setRemark(c.getCategory());
                                childNodeList2.add(childNode2);
                            }
                            childNode.setChildren(childNodeList2);
                        }
                    }
                }

            }else{
                List<LemsunResource> resources=service.getByCategory(category.getCategory());
                if(resources != null) {
                    for(LemsunResource c : resources) {
                        CompTreeNode childNode= new CompTreeNode();
                        childNode.setId(c.getPid());
                        childNode.setText(c.getName());
                        childNode.setLeaf(true);
                        childNode.setComp(true);
                        childNode.setRemark(c.getCategory());
                        childNodeList.add(childNode);
                    }

                }
            }

        }
        return extTreeNodeList;
    }



}
