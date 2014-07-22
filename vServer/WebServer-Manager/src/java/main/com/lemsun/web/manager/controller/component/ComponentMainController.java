package com.lemsun.web.manager.controller.component;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.Navigation;
import com.lemsun.core.NavigationComponent;
import com.lemsun.core.member.ResourceReturnData;
import com.lemsun.core.service.INavigationService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.resource.FileResource;
import com.lemsun.data.resource.ImageResource;
import com.lemsun.data.service.IDbService;
import com.lemsun.data.service.ITableService;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.data.tables.TableResource;
import com.lemsun.form.*;
import com.lemsun.web.Session;
import com.lemsun.web.manager.model.component.*;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.data.viproject.TableImportInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 组件管理
 * User: Lucklim
 * Date: 12-11-19
 * Time: 下午12:45
 */
@Controller
@RequestMapping("component/componentmain")
public class ComponentMainController {
    private static final Logger log = LoggerFactory.getLogger(ComponentMainController.class);

    @Autowired
    IResourceService resourceService;


    @Autowired
    IDbService dbService;

    @Autowired
    INavigationService navigationService;

    @Autowired
    ITableService tableService;

    @RequestMapping(value = {"","/","view"})
    public String view()
    {
        return "admin/component/componentMain";
    }


    @ResponseBody
    @RequestMapping("list/get")
    public List<ResourceTreeStore> getData(ResourceSelectQuery query)
    {
        ResourceReturnData returnData= resourceService.getPagging(query, query.getName(),query.getCategory());
        return (new ResourceTreeStore()).convertLemsunResource(returnData.getLemsunResources());
    }

    @ResponseBody
    @RequestMapping("list/getpaging")
    public Page<LemsunResource> getDataPaging(ResourceSelectQuery query)
    {
        ResourceReturnData returnData= resourceService.getPagging(query, query.getName(),query.getCategory());
        //以下为欺骗EXT分页组件数据以减少传�
        LemsunResource lr=new LemsunResource("name","category");
        List<LemsunResource> lemsunResourceList=new ArrayList<>();
        lemsunResourceList.add(lr);
        return new PageImpl<>(lemsunResourceList,query,returnData.getCount());
    }

    /**
     * 获取4代帐�
     * @param dbpid
     * @return
     */
    @ResponseBody
    @RequestMapping("four/getaccount")
    public List<ViNavigateTreeStore> getFourNavigate(String dbpid,String name)
    {
        if(log.isDebugEnabled())log.debug("开始连接四代数据库");
        DbConfigResource dbConfigResource =dbService.getDbconfigResource(dbpid);
        if(dbConfigResource==null){
            if(log.isDebugEnabled())log.debug("没有找到数据库组件");
            return null;
        }

        return null;// (new ViNavigateTreeStore()).convertViNavigate(tableService.getFourNavigatesAccount(dbConfigResource,name));
    }

    @ResponseBody
    @RequestMapping("four/accountnavigate")
    public List<ViNavigateTreeStore> getAccountNavigate(String dbpid,String account,String time,String tablegroup)
    {
        Date date = getDateTime(time);
        if(log.isDebugEnabled())log.debug("根据帐套得到表组,开始连接四代数据库");
        DbConfigResource dbConfigResource =dbService.getDbconfigResource(dbpid);
        if(dbConfigResource==null){
            if(log.isDebugEnabled())log.debug("没有找到数据库组件");
            return null;
        }
        return  null;//(new ViNavigateTreeStore()).convertViNavigate(tableService.getFourAccountViNavigateIndex(dbConfigResource,account,date),tableService,dbConfigResource);
    }

    @ResponseBody
    @RequestMapping("four/accounttablegrup")
    public List<ViNavigateTreeStore> getTableGroupByAccountCode(String dbpid,String account,String code,String time)
    {
        if(StringUtils.isEmpty(code))return new ArrayList<>();
        Date date = getDateTime(time);
        if(log.isDebugEnabled())log.debug("根据帐套得到表组,开始连接四代数据库");
        DbConfigResource dbConfigResource =dbService.getDbconfigResource(dbpid);
        if(dbConfigResource==null){
            if(log.isDebugEnabled())log.debug("没有找到数据库组件");
            return null;
        }
        List<ViNavigateTreeStore> relist=  null;//(new ViNavigateTreeStore()).convertViNavigate(tableService.getFourViNavigateByIndex(dbConfigResource,code,date),tableService,dbConfigResource);
        if(relist==null)relist=new ArrayList<>();
        return relist;
    }

    private Date getDateTime(String time) {
        Date date=null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(time);
        }catch (Exception ex){}
        if(date==null)date=new Date();
        return date;
    }

    /**
     * 保存4代中的表组并进入添加权限页
     * @param codes
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addtablegrup",method =RequestMethod.POST)
    public ResponseEntity  addTablegrup(String dbpid,String codes) throws Exception {
        if(StringUtils.isEmpty(dbpid)||StringUtils.isEmpty(codes))throw new Exception("数据库资源ID和组件CODE不能为空");
        DbConfigResource dbConfigResource =dbService.getDbconfigResource(dbpid);
        if(dbConfigResource==null){
            throw new Exception("没有找到相应的数据资源");
        }

        String[] codessp=codes.split("\\|\\|");
        if(codessp.length<1)throw new Exception("没有可添加或者更新的组件");
        if(log.isDebugEnabled())log.debug("开始导入4代表组 codes="+codes+";  dbpid="+dbpid);

        Collection<TableImportInfo> tableImportInfos=tableService.importTableGroups(codessp,dbConfigResource);

        return new ResponseEntity(true,getReturnMessage(tableImportInfos));
    }

    private String getReturnMessage(Collection<TableImportInfo> tableImportInfos){
        if(tableImportInfos==null)return "没有要导入的记录";
        int success=0,error=0;
        for (TableImportInfo t:tableImportInfos){
            if(t.isSuccess())
                success++;
            else
                error++;
        }
        return "一共要导入"+tableImportInfos.size()+"个表组,成功"+success+"个,失败"+error+"个";
    }

    /**
     * 导入帐套下所有的表组
     * @param dbpid
     * @param account
     * @param date
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "addtablegrup",method =RequestMethod.GET)
    public ResponseEntity  addTablegrup(String dbpid,String account,String date) throws Exception {
        Date datetime = getDateTime(date);
        DbConfigResource dbConfigResource =dbService.getDbconfigResource(dbpid);
        if(dbConfigResource==null){
            return new ResponseEntity(false, "没有找到相应的数据资源");
        }
        Collection<TableImportInfo> tableImportInfos= tableService.importTableGroups(dbConfigResource);
        return new ResponseEntity(true,getReturnMessage(tableImportInfos));
    }

    /**
     * 添加权根
     * @param dbpid
     * @param codes
     * @return
     */
    @RequestMapping(value = "addjurisdiction",method = RequestMethod.POST)
    public ModelAndView addJurisdiction(String dbpid,String codes){
        ModelAndView modelAndView=new ModelAndView("admin/component/importfourAddjurisdiction");
        modelAndView.addObject("dbpid",dbpid);
        modelAndView.addObject("codes",codes);
        return modelAndView;
    }

    @RequestMapping(value = "addaccountinnav",method =RequestMethod.POST)
    public ModelAndView addAccountInNavigation(String dbpid,String codes)
    {
        ModelAndView modelAndView=new ModelAndView("admin/component/importfourAccountInNavigation");
        modelAndView.addObject("dbpid",dbpid);
        modelAndView.addObject("codes",codes);
        return modelAndView;
    }

    @RequestMapping(value = "addcodeinnav",method =RequestMethod.POST)
    public ResponseEntity addCodeInNavigation(HttpServletRequest request, String dbpid,String codes,String navid) throws Exception {
        if(StringUtils.isEmpty(dbpid)||StringUtils.isEmpty(codes))throw new Exception("数据库资源ID和组件CODE不能为空");
        DbConfigResource dbConfigResource =dbService.getDbconfigResource(dbpid);
        if(dbConfigResource==null){
            throw new Exception("没有找到相应的数据资源");
        }
        String[] codessp=codes.split("\\|\\|");
        if(codessp.length<1)throw new Exception("没有可添加或者更新的组件");
        for (String c:codessp){
            if(!StringUtils.isEmpty(c)){
                TableGroupResource tableGroupResource= tableService.getFourByCode(dbConfigResource,c);
                if(tableGroupResource!=null){
                    try{
                        NavigationComponent navigationComponent=new NavigationComponent();
                        navigationComponent.setCategory(tableGroupResource.getCategory());
                        navigationComponent.setCreateUser("System");
                        //navigationComponent.setCreateUser(Session.getAccount(request).getAccount().getShowName());
                        navigationComponent.setName(tableGroupResource.getName());
                        navigationComponent.setResourcePid(tableGroupResource.getPid());
                        navigationService.addComponent(navid,navigationComponent);
                    }catch (Exception ex){
                        if(log.isDebugEnabled())log.debug("加入导航出错 PID:"+tableGroupResource.getPid()+" 错误信息:"+ex.getMessage());
                    }
                }
            }
        }
        return new ResponseEntity(true);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView addComponent(CreateResource resource) throws Exception {

        if(resource.getCategory().equals(BaseCategory.DBTABEL_GROUP_4.getCategory()))
        {
            //跳转�代导入页
            if(log.isDebugEnabled())log.debug("进入4代导入页面");
            return new ModelAndView("redirect:/component/componentmain/importfour?dbpid="+resource.getParentid());
        }
        if(StringUtils.isEmpty(resource.getCompontentname())||StringUtils.isEmpty(resource.getCategory()))
        {
            throw new Exception("名称和类型不能为空");
        }
        IResource res=getResource(resource.getCategory(),resource.getCompontentname(),resource.getParentid());
        if(res==null)
            throw new Exception("创建资源出错");
        dbService.addResource(res);
        return new ModelAndView("redirect:/component/componentmain/update?category="+res.getCategory()+"&pid="+res.getPid());
    }

    @RequestMapping("update")
    public ModelAndView updateComponent(String category, String pid) throws Exception {
        if(category.equals(BaseCategory.DBTABEL_GROUP_4.getCategory()))
        {
            TableGroupResource fortg=resourceService.get(pid,TableGroupResource.class);
            if(fortg==null)throw new Exception("找不到相应的组件");
            return new ModelAndView("redirect:/component/componentmain/importfour?dbpid="+fortg.getParentPid());
        }
        ModelAndView modelAndView=getModelAndViewByCategory(category);
        Class<?> cls= getResouceClassType(category);
        if(cls==null)
            throw new Exception("找不该类型的类文件");
        IResource resource= (IResource)resourceService.get(pid,cls);
        if(resource==null)
            throw new Exception("资源未找到");
        modelAndView.addObject("resource", resource);
        return modelAndView;
    }

    //导入4代页
    @RequestMapping("importfour")
    public ModelAndView ImportFour(String dbpid)
    {
        ModelAndView mview=new ModelAndView("admin/component/importfour");
        mview.addObject("dbpid",dbpid);
        return mview;
    }
    //导入帐套�
    @RequestMapping("importaccount")
    public ModelAndView ImportAccount(String dbpid,String account)
    {
        ModelAndView mview=new ModelAndView("admin/component/importfourAccount");
        mview.addObject("dbpid",dbpid);
        mview.addObject("account",account);
        return mview;
    }

    /**
     * 2013-1-4 郭茂添加
     * 给组件分配权�
     * @param category 组件类型
     * @param pid 组件pid
     * @return 跳转到分配权限界�
     * @throws Exception
     */
    @RequestMapping("quanxian")
    public ModelAndView quanxianComponent(String category, String pid) throws Exception {

        ModelAndView modelAndView= new ModelAndView("/admin/component/componentQuanXian");

        Class<?> cls= getResouceClassType(category);
        if(cls==null)
            throw new Exception("找不该类型的类文件");
        IResource resource= (IResource)resourceService.get(pid,cls);
        if(resource==null)
            throw new Exception("资源未找到");
        modelAndView.addObject("resource",resource);
        return modelAndView;
    }

    @RequestMapping("quanxiansubmit")
    public ModelAndView quanxiansubComponent(String category, String pid) throws Exception {

        //执行权限数据


        //执行完成后跳转到下一步导航挂载页�
        ModelAndView modelAndView= new ModelAndView("/admin/component/componentDaoHang");
        Class<?> cls= getResouceClassType(category);
        if(cls==null)
            throw new Exception("找不该类型的类文件");
        IResource resource= (IResource)resourceService.get(pid,cls);
        if(resource==null)
            throw new Exception("资源未找到");
        modelAndView.addObject("resource",resource);

        //0表示文件夹，1表示组件
        List<Navigation> list = navigationService.getRootByCategory("0");
        modelAndView.addObject("navigation",list);

        return modelAndView;
    }

    /**
     * 2013-1-4 郭茂添加
     * 给组件分配权�
     * @param category 组件类型
     * @param pid 组件pid
     * @return 跳转到分配权限界�
     * @throws Exception
     */
    @RequestMapping("daohangsubmit")
    public ModelAndView daohangComponent(String category, String pid) throws Exception {

        ModelAndView modelAndView= new ModelAndView("/admin/component/componentDaoHang");
//
//		Class<?> cls= getResouceClassType(category);
//		if(cls==null)
//			throw new Exception("找不该类型的类文�);
//		IResource resource= (IResource)resourceService.get(pid,cls);
//		if(resource==null)
//			throw new Exception("资源未找�);
//		modelAndView.addObject("resource",resource);
//
//		Navigation navigation = navigationService.getRootByCategory("0");
//		List<NavigationTreeStore> list = (new NavigationTreeStore()).convertNavigation(navigation,true);
//		modelAndView.addObject("navigation",list);
//
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("delete")
    public void deleteComponent(String pid)
    {
        resourceService.deleteResource(pid);
    }

    private Class<?> getResouceClassType(String category)
    {
        Class<?> cls=null;
        if(!StringUtils.isEmpty(category))
        {
            if (category.equals(BaseCategory.SCRIPT.getCategory()))
            {
                cls = ScriptResource.class;
            }else if(category.equals(BaseCategory.WEB_SCRIPT.getCategory()))
            {
                cls=WebScriptResource.class;
            }else if(category.equals(BaseCategory.WPF_SCRIPT.getCategory()))
            {
                cls=WpfScriptResource.class;
            }else if(category.equals(BaseCategory.DB.getCategory()))
            {
                cls=DbConfigResource.class;
            }else if(category.equals(BaseCategory.DBTABEL_GROUP_5.getCategory()))
            {
                cls=TableGroupResource.class;
            }else if(category.equals(BaseCategory.DBTABEL.getCategory()))
            {
                cls=TableResource.class;
            }else if(category.equals(BaseCategory.WEB_SKIN.getCategory()))
            {
                cls=WebPageResource.class;

            }else if(category.equals(BaseCategory.WPF_SKIN.getCategory()))
            {
                cls=WpfPageResource.class;

            }else if(category.equals(BaseCategory.IMAGE.getCategory()))
            {
                cls=ImageResource.class;
            }else if(category.equals(BaseCategory.RESOURCE.getCategory()))
            {
                cls=FileResource.class;
            }
        }
        return cls;
    }

    private IResource getResource(String category,String name,String pid)
    {
        IResource resource = null;

        if(StringUtils.equals(category, BaseCategory.SCRIPT.getCategory())) {

        } else

        if(!StringUtils.isEmpty(category))
        {
            if(category.equals(BaseCategory.SCRIPT.getCategory()))
            {
                resource=new ScriptResource(name,category);
            }else if(category.equals(BaseCategory.WEB_SCRIPT.getCategory()))
            {
                resource=new WebScriptResource(name,category);

            }else if(category.equals(BaseCategory.WPF_SCRIPT.getCategory()))
            {
                resource=new WpfScriptResource(name,category);

            }else if(category.equals(BaseCategory.DB.getCategory()))
            {
                resource=new DbConfigResource(name);

//            }else if(category.equals(BaseCategory.DBTABEL_GROUP_4.getCategory()))
//            {
//                DbConfigResource dbConfigResource4=dbService.getDbconfigResource(pid);
//                if(dbConfigResource4!=null)
//                    resource=new TableGroupResource(name,category,4,dbConfigResource4);

            }else if(category.equals(BaseCategory.DBTABEL_GROUP_5.getCategory()))
            {
                DbConfigResource dbConfigResource5=dbService.getDbconfigResource(pid);
                if(dbConfigResource5!=null)
                    resource=new TableGroupResource(name,category,5,dbConfigResource5);

            }else if (category.equals(BaseCategory.DBTABEL.getCategory()))
            {
                TableGroupResource tableGroupResource=dbService.getTableGroupResourceByPid(pid);
                if(tableGroupResource!=null)
                    resource=new TableResource(name,pid,tableGroupResource.getDbConfig());

            }else if(category.equals(BaseCategory.WEB_SKIN.getCategory()))
            {
                resource=new WebPageResource(name,category);
            }else if(category.equals(BaseCategory.WPF_SKIN.getCategory()))
            {
                resource=new WpfPageResource(name,category);

            }else if(category.equals(BaseCategory.IMAGE.getCategory()))
            {
                resource=new ImageResource(name,category);
            }else if(category.equals(BaseCategory.RESOURCE.getCategory()))
            {
                resource=new FileResource(name,category);
            }
        }
        if(resource!=null&&!StringUtils.isEmpty(pid))
            resource.setParentPid(pid);
        return resource;
    }

    //根据类别返回相应的View
    private ModelAndView getModelAndViewByCategory(String category)
    {
        String pageView="redirect:/admin";
        if(!StringUtils.isEmpty(category))
        {
            if (category.equals(BaseCategory.SCRIPT.getCategory()))
            {
                pageView="createScript";
            }else if(category.equals(BaseCategory.WEB_SCRIPT.getCategory()))
            {
                pageView="createWebScript";
            }else if(category.equals(BaseCategory.WPF_SCRIPT.getCategory()))
            {
                pageView="createWpfScript";
            }else if(category.equals(BaseCategory.DB.getCategory()))
            {
                pageView="createDb";
//            }else if(category.equals(BaseCategory.DBTABEL_GROUP_4.getCategory()))
//            {
            }else if(category.equals(BaseCategory.DBTABEL_GROUP_5.getCategory()))
            {
                pageView="createTableGroup";
            }else if(category.equals(BaseCategory.DBTABEL.getCategory()))
            {
                pageView="createTable";
            }else if(category.equals(BaseCategory.WEB_SKIN.getCategory()))
            {
                pageView="createWebSkin";
            }else if(category.equals(BaseCategory.WPF_SKIN.getCategory()))
            {
                pageView="createWpfSkin";
            }else if(category.equals(BaseCategory.IMAGE.getCategory()))
            {
                pageView="createImage";
            }else if(category.equals(BaseCategory.RESOURCE.getCategory()))
            {
                pageView="createResource";
            }
        }
        ModelAndView modelAndView;
        if("redirect:/admin".equals(pageView))
            modelAndView=new ModelAndView(pageView);
        else
            modelAndView=new ModelAndView("admin/component/"+pageView);
        return modelAndView;
    }
}
