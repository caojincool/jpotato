package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.component.lkg.BasePackage;
import com.lemsun.component.lkg.service.IVirtualDirectoryService;
import com.lemsun.core.*;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.service.*;
import com.lemsun.core.util.ResourceUtil;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.ResourceTreeStore;
import com.lemsun.web.manager.controller.model.query.ComponentQuery;
import com.lemsun.web.manager.controller.util.WebConstant;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.view.ResourceBase;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * 组件控制层基类
 * User: 刘晓宝
 * Date: 13-9-12
 * Time: 下午3:09
 */
public abstract class BaseController {

    @Autowired
    protected IResourceService resourceService;

    @Autowired
    protected JsonObjectMapper mapper;

    @Autowired
    protected INavigationService navigationService;

    @Autowired
    protected com.lemsun.web.manager.controller.iservice.ResourceController resourceController;

    @Autowired
    protected ISetOfBooksService setOfBooksService;

    @Autowired
    private IVirtualDirectoryService virtualDirectoryService;

    @Autowired
    private IPlateformInstanceService plateformInstanceService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    protected IAccountService accountService;
    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 组件关联查询
     *
     * @param view
     * @param resource
     * @return
     * @throws Exception
     */
    protected ModelAndView details(ModelAndView view, IResource resource) throws Exception {
        view.addObject("resource", resource);
        List<BasePackage> bps = virtualDirectoryService.getBasePackageByPid(resource.getPid());
        List<Navigation> ncs = navigationService.getNavigationByResource(resource);
        List<SetOfBooks> sbrs = setOfBooksService.getSetOfBooksByResource(resource);

        Collection<ICategory> categorys = categoryService.getAll();
        Map<String, String> names = new HashMap<>();

        for (ICategory c : categorys) {
            names.put(c.getCategory(), c.getName());
        }
        String json = mapper.writeValueAsString(names);
        List<LemsunResource> childs = resourceService.getChildResourceByParentId(resource.getPid());
        view.addObject("childsIsEmpty", childs.isEmpty());
        view.addObject("categorys", json);
        view.addObject("bps", bps);
        view.addObject("ncs", ncs);
        view.addObject("sbrs", sbrs);
        return view;
    }

    /**
     * 获取根节点下的信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "{pid}/tree")
    public List<ResourceTreeStore> getRoot(@PathVariable String pid) {
        LemsunResource LemsunResource = resourceService.loadTreeResourceByPid(pid);
        List<ResourceTreeStore> list = (new ResourceTreeStore()).convertResourceTreeStore(LemsunResource, true);
        return list;
    }

    protected IResource getIResource(String pid) throws Exception {
        IResource resource = resourceService.get(pid);
        return resource;
    }

    @RequestMapping(value = "{pid}/details", method = RequestMethod.GET)
    public ModelAndView details(String type, HttpServletRequest request, @PathVariable String pid) throws Exception {
        if (!ResourceUtil.isRecourcePid(pid))
            throw ResourceException.ResourcePidisNull;
        RequestMapping rm = this.getClass().getAnnotation(RequestMapping.class);

        ModelAndView view = new ModelAndView(rm.value()[0] + "/details");
        try {
            IResource webPageResource = getIResource(pid);
            List<PlateformInstance> plateformInstances = plateformInstanceService.getAllInstance();

            details(view, webPageResource);
            view.addObject("type", type);
            view.addObject("plateformInstances", plateformInstances);

        } catch (Exception ex) {
            if(logger.isDebugEnabled())
                logger.debug("获取组件异常:"+ex.getMessage());
        }

        return view;
    }

    @RequestMapping(value = "{pid}/delete", method = RequestMethod.GET)
    public ModelAndView delete(String type, HttpServletRequest request, @PathVariable String pid) throws Exception {
        if (!ResourceUtil.isRecourcePid(pid))
            throw ResourceException.ResourcePidisNull;
        IResource webPageResource = getIResource(pid);
        RequestMapping rm = this.getClass().getAnnotation(RequestMapping.class);
        ModelAndView view = new ModelAndView(rm.value()[0] + "/delete");
        details(view, webPageResource);

        view.addObject("type", type);
        return view;
    }

    protected void removeResourceFormSession(HttpSession session) {
        IResource resource = (IResource) session.getAttribute("resource");
        if (resource != null) {
            session.removeAttribute("resource");
        }
    }

    protected HttpSession getHttpSession(HttpServletRequest request) {
        return request.getSession();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseEntity delete(HttpServletRequest request) throws Exception {
        String pid = request.getParameter("pid");
        HttpSession session = getHttpSession(request);
        removeResourceFormSession(session);
        if (ResourceUtil.isRecourcePid(pid)) {

            deleteResource(pid);

        }
        return new ResponseEntity<String>("操作成功!");
    }

    abstract void deleteResource(String pid) throws Exception;

    protected ModelAndView getModelAndView(String pid, HttpServletRequest request) {
        String flag = request.getParameter("flag");
        if (StringUtils.equalsIgnoreCase(WebConstant.ISNEXT, flag)) {
            return new ModelAndView("redirect:"
                    + PrepareModelInteceptor.getRootPath()
                    + "component/main/" + pid + "/imageAndDetails/add");
        } else {
            String url = "redirect:"
                    + PrepareModelInteceptor.getRootPath()
                    + "component/main/" + pid + "/create/finish";
            return new ModelAndView(url);
        }
    }

    public ModelAndView view(ComponentQuery query) throws IOException {

        ComponentQueryHandler handler = createComponentQueryHandler();
        handler.setQuery(query);
        ModelAndView view = handler.initModelAndView();
        handler.initMap(view);
        handler.covertConditions();
        Page<IResource> data = handler.executeQueryComponent();
        handler.setData(data);
        Page<ResourceBase> resourceBases = handler.afterRelatedQueryHandler();
        view.addObject("data", resourceBases);
        view.addObject("query", handler.getQuery());
        return view;
    }

    protected abstract ComponentQueryHandler createComponentQueryHandler() throws IOException;

    protected abstract class ComponentQueryHandler {
        protected Page<IResource> data;
        protected ComponentQuery query;

        protected ComponentQueryHandler() throws IOException {
        }

        public void setData(Page<IResource> data) {
            this.data = data;
        }

        public ComponentQuery getQuery() {
            return query;
        }

        public void setQuery(ComponentQuery query) {
            this.query = query;
        }


        /**
         * 初始化目标地址
         *
         * @return
         */
        protected ModelAndView initModelAndView() {
            RequestMapping rm = BaseController.this.getClass().getAnnotation(RequestMapping.class);
            return new ModelAndView(rm.value()[0] + "/view");
        }

        /**
         * 设置页面菜单定位信息
         *
         * @return
         */
        protected void initMap(ModelAndView view) {
            Map<String, Integer> map = createMap();
            addAttribute(view, map);
        }

        protected abstract Map<String, Integer> createMap();

        /**
         * 条件转化
         *
         * @param
         */
        protected void covertConditions() {
            userNameConvertAccount();
        }

        /**
         * 查询组件
         *
         * @param
         * @return
         */
        protected abstract <T extends IResource> Page<T> executeQueryComponent();

        /**
         * 查找组件相关属性进行填充
         *
         * @return
         */
        protected abstract <E extends ResourceBase> Page<E> afterRelatedQueryHandler();

        /**
         * 添加属性
         *
         * @param view
         */
        protected void addAttribute(ModelAndView view, Map<String, Integer> map) {
            Set<String> keys = map.keySet();
            for (Iterator it = keys.iterator(); it.hasNext(); ) {
                String key = (String) it.next();
                view.addObject(key, map.get(key));
            }

        }

        /**
         * 条件转化
         * 用户名转为登录账户
         *
         * @param
         */
        protected void userNameConvertAccount() {
            if (query != null && StringUtils.isNotEmpty(query.getShowName())) {
                List<BaseAccount> accountList = accountService.getAccountByShowName(query.getShowName());
                List<String> accounts = new ArrayList<>(accountList.size());
                for (BaseAccount account : accountList) {
                    accounts.add(account.getAccount());
                }
                query.setCreateAccount(accounts);
            }
        }
    }

    @RequestMapping(value = "{pid}/getContent",method = RequestMethod.GET)
    public void getContent(HttpServletResponse response,@PathVariable String pid) {
        String content=resourceService.getContent(pid);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");

            response.getWriter().write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
