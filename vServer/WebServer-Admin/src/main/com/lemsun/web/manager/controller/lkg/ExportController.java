package com.lemsun.web.manager.controller.lkg;

import com.lemsun.auth.AccountManager;
import com.lemsun.component.lkg.BasePackage;
import com.lemsun.component.lkg.PackageException;
import com.lemsun.component.lkg.VirtualDirectory;
import com.lemsun.component.lkg.VirtualDirectoryComponent;
import com.lemsun.component.lkg.model.AddVdcForm;
import com.lemsun.component.lkg.service.IExportService;
import com.lemsun.component.lkg.service.IVirtualDirectoryService;
import com.lemsun.core.ICategory;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.service.ICategoryService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.packages.PackageCreate;
import com.lemsun.web.manager.controller.model.packages.VirtualDirectoryTreeStore;
import com.lemsun.web.manager.controller.model.query.BasePackageQuery;
import com.lemsun.web.manager.controller.model.query.ComponentQuery;
import com.lemsun.web.manager.controller.model.query.VirtualDirectoryComponentQuery;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-6-18
 * Time: 上午8:53
 */
@Controller
@RequestMapping(value = "package")
public class ExportController {
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IExportService service;
    @Autowired
    private IVirtualDirectoryService virtualDirectoryService;
    @Autowired
    private JsonObjectMapper mapper;

    @Autowired
    private ICategoryService categoryService;

    private final Logger log = LoggerFactory.getLogger(ExportController.class);

    /**
     * 组件包查看页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "main/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("lkg/main");

        Collection<ICategory> categorys = categoryService.getAll();

        Map<String, String> names = new HashMap<>();

        for (ICategory c : categorys) {
            names.put(c.getCategory(), c.getName());
        }

        String json = null;
        try {
            json = mapper.writeValueAsString(names);
        } catch (IOException e) {
            if(log.isErrorEnabled())

                log.error("组件包管理控制器bug:" + e.getMessage());
        }

        view.addObject("categorys", json);
        request.getSession().setAttribute("head",2);
        request.getSession().setAttribute("left",1);
        return view;
    }

    /**
     * 跳到添加组件包基本填写页面
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView addPackage() {
        ModelAndView view = new ModelAndView("lkg/create/createBase");

        return view;
    }

    /**
     * 执行添加组件包
     * @param packageCreate
     *
     * @return
     * @throws PackageException
     * @throws IOException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView doAddPackage(PackageCreate packageCreate)  throws PackageException{

        BasePackage bp= packageCreate.encapseBasePackage();
        bp.setCreateUser(AccountManager.getCurrentManager().getAccount().getAccount());
        bp.setCreateTime(new Date());
        service.add(bp);
        ModelAndView view = new ModelAndView("redirect:"+ PrepareModelInteceptor.getRootPath()+"package/addComponent");
        view.addObject("lid",bp.getLid());
        view.addObject("name",bp.getName());
         return view;
    }

    /**
     * 跳到组件包挂载组件页面
     * @return
     */
    @RequestMapping(value = "/addComponent")
    public ModelAndView addComponent(HttpServletRequest request) throws IOException, PackageException {
        Collection<ICategory> categorys = categoryService.getAll();
        Map<String, String> names = new HashMap<>();
        String lid= request.getParameter("lid");
        BasePackage basePackage=service.getBasePackageByLid(lid);
        String packageName= request.getParameter("packageName");
        for (ICategory c : categorys) {
            names.put(c.getCategory(), c.getName());
        }

        String json = mapper.writeValueAsString(names);
        ModelAndView view = new ModelAndView("lkg/create/VirtualDirectoryCreate");
        view.addObject("categorys", json);
        view.addObject("lid",lid);
        view.addObject("rootFid",basePackage.getId());
        view.addObject("packageName",packageName);
        return view;
    }
    /**
     * 调转到修改页面
     * @param
     * @param request
     * @return
     * @throws PackageException
     * @throws IOException
     */
    @RequestMapping(value = "/editPackage",method = RequestMethod.GET)
    public ModelAndView editPackage( HttpServletRequest request)  throws PackageException, IOException{
        String lid=request.getParameter("lid");
        BasePackage basePackage=service.getBasePackageByLid(lid);
        ModelAndView view = new ModelAndView("lkg/edit/editBase");
        view.addObject("basePackage", basePackage);
        return view;
    }

    /**
     * 调转到修改页面
     * @param
     * @return
     * @throws PackageException
     * @throws IOException
     */
    @RequestMapping(value = "/doEditPackage",method = RequestMethod.POST)
    public ModelAndView doEditPackage( PackageCreate packageCreate)  throws PackageException, IOException{
        packageCreate.setUpdateTime(new Date());
        packageCreate.setCreateUser(AccountManager.getCurrentManager().getAccount().getAccount());
        BasePackage basePackage=service.getBasePackageByLid(packageCreate.getLid());
        basePackage=packageCreate.encapseBasePackage(basePackage);
        service.update(basePackage);
        ModelAndView view = new ModelAndView("redirect:" + PrepareModelInteceptor.getRootPath() +"package/main/view");
        return view;
    }

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public ModelAndView view(String lid) throws PackageException {
        BasePackage basePackage=service.getBasePackageByLid(lid);
        ModelAndView view=new ModelAndView("lkg/view");
        view.addObject("basePackage", basePackage);
        return view;
    }

    @RequestMapping(value = "/delPackage",method = RequestMethod.GET)
    public ModelAndView remove(String lid) throws PackageException {
        BasePackage basePackage=service.getBasePackageByLid(lid);
        ModelAndView view=new ModelAndView("lkg/remove");
        view.addObject("basePackage", basePackage);
        return view;
    }

    /**
     * 根据包编码删除对应的包
     */
    @RequestMapping(value = "/delPackage", method = RequestMethod.POST)
    public ModelAndView delPackage(String  lid) throws PackageException{

        ModelAndView view=new ModelAndView("redirect:" + PrepareModelInteceptor.getRootPath() + "package/main/view");

        //ResponseEntity<String> responseEntity = new ResponseEntity<>();

        service.delPackage(lid);

        //responseEntity.setSuccess(true);
        return view;
    }

    /**
     * 获取组件数据
     */
    @RequestMapping("list/get")
    public ResponseEntity<Page<LemsunResource>> getPageList(ComponentQuery query,String lid) {
        List<VirtualDirectoryComponent> vdcs=virtualDirectoryService.getVirtualDirectoryComponentsByPackageId(lid);
        List<String> pids=new ArrayList<>();
        for(VirtualDirectoryComponent vdc:vdcs){
            pids.add(vdc.getPid());
        }
        query.setPids(pids);
        Page<LemsunResource> data = resourceService.getPageing(query,LemsunResource.class);
        return new ResponseEntity<>(data);
    }

    /**
     * 获取组件包虚拟文件夹树形结构
     *
     * @return 导航树形集合
     */
    @ResponseBody
    @RequestMapping("list/getAllVD")
    public List<VirtualDirectoryTreeStore> getData(String lid) {
        VirtualDirectory navigation = virtualDirectoryService.getRoot(lid);

        List<VirtualDirectoryTreeStore> stores = (new VirtualDirectoryTreeStore()).convertNavigation(navigation, true);
        return stores;
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
    public ResponseEntity addWJ(String filename, String fileremark, String parentpid,String lid) throws Exception {

        ResponseEntity responseEntity = new ResponseEntity();
        VirtualDirectory navigation = virtualDirectoryService.getVirtualDirectoryByPid(parentpid);
        VirtualDirectory newNav = new VirtualDirectory(filename);
        //navigation 为 null 时，表示添加的是根节点
        newNav.setParent(navigation);
        newNav.setName(filename);
        newNav.setRemark(fileremark);
        newNav.setCreateUser("system");
        newNav.setLid(lid);
        virtualDirectoryService.create(newNav);

        responseEntity.setSuccess(true);
        return responseEntity;
    }

    /**
     * 删除虚拟文件夹信息
     *
     * @param pid 虚拟文件夹pid
     */
    @ResponseBody
    @RequestMapping(value = "deleteVd")
    public void deleteVirtualDirectory(String pid) {
        virtualDirectoryService.deleteVirtualDirectory(pid);
    }

    /**
     * 分页获取某导航下的所有挂载组件集合
     *
     * @param query 分页条件
     * @return 此导航下的所有挂载组件集合
     */
    @ResponseBody
    @RequestMapping("/list/getncdata")
    public ResponseEntity<Page<VirtualDirectoryComponent>> getNavigationComponentData(VirtualDirectoryComponentQuery query) {
        Page<VirtualDirectoryComponent> result = virtualDirectoryService.getNComponentPagging(query);
        ResponseEntity<Page<VirtualDirectoryComponent>> responseEntity = new ResponseEntity<>();
        responseEntity.setSuccess(true);
        responseEntity.setEntity(result);

        return responseEntity;
    }

    /**
     * 给组件包一个虚拟文件下添加组件
     */
    @ResponseBody
    @RequestMapping(value = "addVdc")
    public ResponseEntity addVirtualDirectoryComponent(AddVdcForm addVdcForm) throws Exception {
        virtualDirectoryService.addVirtualDirectoryComponent(addVdcForm);
        ResponseEntity<String> entity = new ResponseEntity<>();
        entity.setSuccess(true);
        return entity;
    }

    /**
     * 删除组件包de一个虚拟文件下的组件
     */
    @ResponseBody
    @RequestMapping(value = "delVdc")
    public ResponseEntity delVirtualDirectoryComponent(String[] ids) {
        virtualDirectoryService.delVirtualDirectoryComponentByIds(ids);
        ResponseEntity<String> entity = new ResponseEntity<>();
        entity.setSuccess(true);
        return entity;
    }

    @RequestMapping(value = "/getBasePackage")
    public ResponseEntity<Page<BasePackage>> getBasePackage(BasePackageQuery query) {
        Page<BasePackage> result = service.getBasePackagePagging(query);
        ResponseEntity<Page<BasePackage>> responseEntity = new ResponseEntity<>();
        responseEntity.setSuccess(true);
        responseEntity.setEntity(result);
        responseEntity.setTotalCount(result.getTotalElements());
        return responseEntity;
    }

}
