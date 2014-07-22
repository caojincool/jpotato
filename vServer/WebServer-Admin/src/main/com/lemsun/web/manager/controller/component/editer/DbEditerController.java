package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.ResourceState;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.util.ResourceUtil;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.service.IDbService;
import com.lemsun.ldbc.DbCategory;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.DbComponentCreate;
import com.lemsun.web.manager.controller.model.query.DbConfigQuery;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.view.DbDbConfigItemView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 13-1-10
 * Time: 下午2:45
 * 数据库链接资源管理
 */
@Controller
@RequestMapping("component/db")
public class DbEditerController extends BaseController{

    @Autowired
    private IDbService dbService;
    @Autowired
    IResourceService resourceService;
    private Logger logger = LoggerFactory.getLogger(DbEditerController.class);
    /**
     * 获取创建DB视图
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView viewadd(HttpServletRequest request, String pid) {

        LemsunResource resource = ((LemsunResource) request.getSession().
                getAttribute("resource"));

        ModelAndView view = new ModelAndView();

        if (resource == null){
            view.setViewName("redirect:" + PrepareModelInteceptor.getRootPath() + "component/main/create");
        }else{
            view.setViewName("component/add/DB");
            view.addObject("resource", resource);
        }
        return view;
    }

    /**
     * 执行数据库编辑请求
     */
    @RequestMapping(value = "doAdd", method = RequestMethod.POST)
    public ModelAndView doadd(HttpServletRequest request, DbComponentCreate create) throws Exception {

        LemsunResource lemsunResource = (LemsunResource) request.getSession().getAttribute("resource");
        //lemsunResource=resourceService.getBaseResource(lemsunResource.getPid());
        if (lemsunResource == null)
            throw new Exception("没有进行正确的创建组件向导操作.");

        DbConfigResource resource = new DbConfigResource(lemsunResource);

        encapsulation(create, resource);

        dbService.updateConfig(resource);
        return getModelAndView(resource.getPid(),  request);
    }

    private void encapsulation(DbComponentCreate create, DbConfigResource resource) {
        resource.setName(create.getName());
        resource.setServer(create.getServer());
        resource.setMaxActive(create.getMaxActive());
        resource.setMaxWait(create.getMaxWait());
        resource.setMaxIdea(create.getMaxIdea());
        resource.setDbCategory(DbCategory.getDbcategory(create.getDbtype()));
        resource.setDbName(create.getDbName());
        resource.setPassword(create.getPassword());
        resource.setUsername(create.getUsername());
        resource.setState(ResourceState.RELEASE);
    }

    /**
     * 显示数据库链接编辑视图
     */
    @RequestMapping(value = "{pid}/edit", method = RequestMethod.GET)
    public ModelAndView view(String type,HttpServletRequest request, @PathVariable String pid) {

        ModelAndView view;

        if (ResourceUtil.isRecourcePid(pid)) {
            DbConfigResource resource = dbService.getDbconfigResource(pid);

            if (resource != null) {
                view = new ModelAndView("component/editer/DB");
                view.addObject("resource", resource);
                return view;
            }

        }
        view = new ModelAndView("component/add/DB");
        view.addObject("type",type);
        return view;
    }



    /**
     * 执行数据库编辑请求
     */
    @RequestMapping(value = "{pid}/edit", method = RequestMethod.POST)
    public ModelAndView doEdit(String type,HttpServletRequest request, @PathVariable String pid, DbComponentCreate create) {
        ModelMap mmap = new ModelMap();

      try{
        DbConfigResource resource = dbService.getDbconfigResource(pid);
        encapsulation(create, resource);
        dbService.updateConfig(resource);
        mmap.put("success",true);
      }catch (Exception e){
          mmap.put("message",e.getMessage());
          mmap.put("success",false);
      }
        ModelAndView view=new ModelAndView("redirect:"
            + PrepareModelInteceptor.getRootPath()+"component/main/operatingResults",mmap);
      return view;
    }


    /**
     * 获取数据库名称集合
     *
     * @return 返回数据库名称集合
     */
    @RequestMapping(value = "names/get", method = RequestMethod.POST)
    public ResponseEntity<String[]> getDbNames(DbComponentCreate create) {
        String[] names = null;
        try {
            DbConfigResource resource = new DbConfigResource("test");
            resource.setServer(create.getServer());
            resource.setUsername(create.getUsername());
            resource.setPassword(create.getPassword());
            resource.setDbCategory(DbCategory.getDbcategory(create.getDbtype()));

            names = dbService.getDbNames(resource);
        } catch (Exception ignored) {

        }
        return new ResponseEntity<>(names);
    }
    /**
     * 显示组件视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request, DbConfigQuery query) throws IOException {
        return super.view(query);
    }

    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler= new ComponentQueryHandler(){

            @Override
            protected Map<String, Integer> createMap() {
                Map<String,Integer> map=new HashMap<>();
                map.put("head",1);
                map.put("left",25);
                return map;
            }
            @Override
            protected  Page<DbConfigResource> executeQueryComponent() {
                return   dbService.getPageing(query);
            }

            @Override
            protected  Page<DbDbConfigItemView> afterRelatedQueryHandler() {
                List<DbDbConfigItemView> webskinItemViewList=new ArrayList<>(data.getSize());
                for(IResource webskin: data.getContent()){
                    BaseAccount account=null;
                    try{
                        account=accountService.getAccountByAccount(webskin.getCreateUser());
                    }catch (Exception e){
                        logger.info("异常数据",e.getMessage());
                    }
                    if(account==null){
                        account=new BaseAccount();
                    }
                    DbDbConfigItemView webskinItemView=new DbDbConfigItemView(webskin,account);
                    webskinItemViewList.add(webskinItemView);

                }

                return  new PageImpl<>(webskinItemViewList, query, data.getTotalElements());
            }
        };
        return handler;
    }

    @Override
    void deleteResource(String pid) throws Exception {
        dbService.deleteResource(pid);
    }
}
