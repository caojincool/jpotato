package com.lemsun.web.manager.controller.component;

import com.lemsun.core.*;
import com.lemsun.core.service.INavigationService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.service.IDbService;
import com.lemsun.data.service.ITableService;
import com.lemsun.data.tables.TableGroupResource;
import com.lemsun.data.viproject.TableImportInfo;
import com.lemsun.data.viproject.importmodel.ImportIndexModel;
import com.lemsun.data.viproject.importmodel.ImportTableGroupModel;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.FourIndexTreeStore;
import com.lemsun.web.manager.controller.model.component.FourTableGroupTreeStore;
import com.lemsun.web.model.HeaderTitle;
import com.lemsun.web.model.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 导入4代
 * User: Lucklim
 * Date: 13-1-17
 * Time: 上午10:31
 */
@Controller
@RequestMapping("component/importfour")
public class ImportFourController {
    private static final Logger log = LoggerFactory.getLogger(ImportFourController.class);

    @Autowired
    IResourceService resourceService;
    @Autowired
    IDbService dbService;
    @Autowired
    ITableService tableService;
    @Autowired
    INavigationService navigationService;

    /**
     * 选择DB和帐套  显示页
     *
     * @return
     */
    @RequestMapping(value = "db", method = RequestMethod.GET)
    public ModelAndView SelectDb(HttpServletRequest request) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);
        ModelAndView view = new ModelAndView("component/importFour/import_selectdb");
        view.addObject("db", resourceService.getByCategory(BaseCategory.DB.getCategory()));
        return view;
    }

    /**
     * 根据数据库组件ID返回相应数据库中的帐套
     *
     * @param dbpid
     * @return
     */
    @RequestMapping("getaccount")
    public ResponseEntity getAccountByDb(String dbpid) {
        try {
            if (log.isDebugEnabled()) log.debug("开始连接四代数据库");
            DbConfigResource dbConfigResource = dbService.getDbconfigResource(dbpid);
            if (dbConfigResource == null) throw LemsunException.ResourceException;

            return new ResponseEntity<>(tableService.getFourAccount(dbConfigResource, ""));
        } catch (Exception e) {
            if (log.isDebugEnabled()) log.debug("根据数据库组件PID获取帐套错误:" + e.getMessage());
            return new ResponseEntity(false, e.getMessage());
        }
    }

    /**
     * 选择表组     显示页面
     *
     * @param dblink
     * @param account
     * @return
     */
    @RequestMapping(value = "selecttable", method = RequestMethod.GET)
    public ModelAndView selectTableGroup(HttpServletRequest request,String dblink, String account) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);
        ModelAndView view = new ModelAndView("component/importFour/import_tablegroup");
        view.addObject("dbpid", dblink);
        view.addObject("vaccount", account);
        return view;
    }

    /**
     * 获取4代目录树(仅目录)
     */
    @ResponseBody
    @RequestMapping("accountnavigate")
    public List<FourIndexTreeStore> getAccountNavigate(String dbpid, String account, String time, String tablegroup){
        Date date = getDateTime(time);
        if (log.isDebugEnabled()) log.debug("根据帐套得到表组,开始连接四代数据库");
        DbConfigResource dbConfigResource = dbService.getDbconfigResource(dbpid);
        if (dbConfigResource == null) {
            if (log.isDebugEnabled()) log.debug("没有找到数据库组件");
            return null;
        }

        return (new FourIndexTreeStore())
                .convertViNavigate(
                        tableService.getFourIndexTree(dbConfigResource, account, date)
                        , tableService
                        , dbConfigResource);

    }

    /**
     * 得到帐套下的表组
     * @param dbpid
     * @param account
     * @param code
     * @param time
     * @return
     */
    @ResponseBody
    @RequestMapping("accounttablegrup")
    public List<FourTableGroupTreeStore> getTableGroupByAccountCode(String dbpid, String account, String code, String time) {
        if (StringUtils.isEmpty(code)) return new ArrayList<>();
        Date date = getDateTime(time);
        if (log.isDebugEnabled()) log.debug("根据帐套得到表组,开始连接四代数据库");
        DbConfigResource dbConfigResource = dbService.getDbconfigResource(dbpid);
        if (dbConfigResource == null) {
            if (log.isDebugEnabled()) log.debug("没有找到数据库组件");
            return null;
        }
        
        List<FourTableGroupTreeStore> relist = (new FourTableGroupTreeStore())
                .convertViNavigate(
                        tableService.getFourTableGroupByIndex(dbConfigResource, code, date)
                        , tableService
                        , dbConfigResource);
        if (relist == null) relist = new ArrayList<>();
        return relist;
    }

    private Date getDateTime(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(time);
        } catch (Exception ex) {
        }
        if (date == null) date = new Date();
        return date;
    }

    /**
     * 保存4代中的表组并进入添加权限页
     *
     * @param codes
     * @return
     */
    @RequestMapping(value = "addtablegrup", method = RequestMethod.POST)
    public ModelAndView addTablegrup(HttpServletRequest request,String dbpid, String codes, String account) throws Exception {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);
        if (StringUtils.isEmpty(dbpid) || StringUtils.isEmpty(codes)) throw new Exception("数据库资源ID和组件CODE不能为空");
        DbConfigResource dbConfigResource = dbService.getDbconfigResource(dbpid);
        if (dbConfigResource == null) {
            throw new Exception("没有找到相应的数据资源");
        }

        String[] codessp = codes.split("\\|\\|");
        if (codessp.length < 1) throw new Exception("没有可添加或者更新的组件");
        if (log.isDebugEnabled()) log.debug("开始导入4代表组 codes=" + codes + ";  dbpid=" + dbpid);
        Collection<TableImportInfo> tableImportInfos = tableService.importTableGroups(codessp, dbConfigResource,request);

        ModelAndView modelAndView = new ModelAndView("component/importFour/import_shoaddtgmessage");
        modelAndView.addObject("dbpid", dbpid);
        modelAndView.addObject("vaccount", account);
        Collection<TableImportInfo> errorinfos = getErrorInfo(tableImportInfos);

        modelAndView.addObject("codes", getSuccessCodes(tableImportInfos));
        modelAndView.addObject("totalcount", tableImportInfos == null ? 0 : tableImportInfos.size());
        modelAndView.addObject("errorcount", errorinfos == null ? 0 : errorinfos.size());
        modelAndView.addObject("message", errorinfos);
        return modelAndView;
    }

    private String getSuccessCodes(Collection<TableImportInfo> tableImportInfos) {
        String codes = "";
        if (tableImportInfos == null) return codes;
        Collection<TableImportInfo> errinfo = new ArrayList<>();
        for (TableImportInfo t : tableImportInfos) {
            if (t.isSuccess())
                codes += t.getCode() + "||";
        }
        return codes;
    }

    private Collection<TableImportInfo> getErrorInfo(Collection<TableImportInfo> tableImportInfos) {
        if (tableImportInfos == null) return null;
        Collection<TableImportInfo> errinfo = new ArrayList<>();
        for (TableImportInfo t : tableImportInfos) {
            if (!t.isSuccess())
                errinfo.add(t);
        }
        return errinfo;
    }

    /**
     * 导入帐套下所有的表组
     *
     * @param dbpid
     * @param account
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "addtablegrup", method = RequestMethod.GET)
    public ResponseEntity addTablegrup(String dbpid, String account) {
        try {
            DbConfigResource dbConfigResource = dbService.getDbconfigResource(dbpid);
            if (dbConfigResource == null) {
                return new ResponseEntity(false, "没有找到相应的数据资源");
            }
            Collection<TableImportInfo> tableImportInfos = tableService.importTableGroups(dbConfigResource);
            return new ResponseEntity(true, getReturnMessage(tableImportInfos));
        } catch (Exception ex) {
            return new ResponseEntity(false, ex.getMessage());
        }
    }

    private String getReturnMessage(Collection<TableImportInfo> tableImportInfos) {
        if (tableImportInfos == null) return "没有要导入的记录";
        int success = 0, error = 0;
        for (TableImportInfo t : tableImportInfos) {
            if (t.isSuccess())
                success++;
            else
                error++;
        }
        return "一共要导入" + tableImportInfos.size() + "个表组,成功" + success + "个,失败" + error + "个";
    }

    /**
     * 显示导入完成信息
     * @param request
     * @param dbpid
     * @param codes
     * @param account
     * @return
     */
    @RequestMapping(value = "showaddtablemsg")
    public ModelAndView showAddTableGroupMessage(HttpServletRequest request,String dbpid, String codes, String account) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);
        ModelAndView modelAndView = new ModelAndView("component/importFour/import_shoaddtgmessage");
        modelAndView.addObject("dbpid", dbpid);
        modelAndView.addObject("codes", codes);
        modelAndView.addObject("vaccount", account);
        return modelAndView;
    }

    /**
     * 添加权根
     *
     * @param dbpid
     * @param codes
     * @return
     */
    @RequestMapping(value = "addjurisdiction", method = RequestMethod.POST)
    public ModelAndView addJurisdiction(HttpServletRequest request,String dbpid, String codes, String account) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);
        ModelAndView modelAndView = new ModelAndView("component/importFour/import_Addjurisdiction");
        modelAndView.addObject("dbpid", dbpid);
        modelAndView.addObject("codes", codes);
        modelAndView.addObject("vaccount", account);
        return modelAndView;
    }

    /**
     * 添加导航显示 (用POST传值防止数据量过大)
     *
     * @param dbpid
     * @param codes
     * @return
     */
    @RequestMapping(value = "addaccountinnav", method = RequestMethod.POST)
    public ModelAndView addAccountInNavigation(HttpServletRequest request,String dbpid, String codes, String account) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);
        ModelAndView modelAndView = new ModelAndView("component/importFour/import_addaccountinnav");
        modelAndView.addObject("dbpid", dbpid);
        modelAndView.addObject("codes", codes);
        modelAndView.addObject("vaccount", account);
        return modelAndView;
    }

    /**
     * 添加导航入库
     * @param request
     * @param dbpid
     * @param codes
     * @param navid
     * @return
     */
    @RequestMapping(value = "addcodeinnav", method = RequestMethod.POST)
    public ResponseEntity addCodeInNavigation(HttpServletRequest request,String dbpid, String codes, String navid) {
        try {
            if (StringUtils.isEmpty(dbpid) || StringUtils.isEmpty(codes)) throw new Exception("数据库资源ID和组件CODE不能为空");
            DbConfigResource dbConfigResource = dbService.getDbconfigResource(dbpid);
            if (dbConfigResource == null) {
                throw new Exception("没有找到相应的数据资源");
            }

            //得到成功导入的组件目录树型结构模型
            List<ImportIndexModel>  importIndexModels=(List<ImportIndexModel>)request.getSession().getAttribute("importTempModel");
            if(importIndexModels==null||importIndexModels.size()==0)    throw new Exception("没有可添加或者更新的组件");

            Navigation navparent=navigationService.get(navid);
            AddNav(importIndexModels,navparent,dbConfigResource);



            String[] codessp = codes.split("\\|\\|");
            if (codessp.length < 1) throw new Exception("没有可添加或者更新的组件");
            for (String c : codessp) {
                if (!StringUtils.isEmpty(c)) {
                    TableGroupResource tableGroupResource = tableService.getFourByCode(dbConfigResource, c);
                    LemsunResource resource= (LemsunResource) resourceService.get(tableGroupResource.getPid());
                    if (tableGroupResource != null) {
                        try {
                            NavigationComponent navigationComponent = new NavigationComponent();
                            //navigationComponent.setNavigation(navigationService.get(navid));
                            //navigationComponent.setLmsResource(resource);
                            navigationService.addComponent(navigationComponent);
                        } catch (Exception ex) {
                            if (log.isDebugEnabled())
                                log.debug("加入导航出错 PID:" + tableGroupResource.getPid() + " 错误信息:" + ex.getMessage());
                        }
                    }
                }
            }
            return new ResponseEntity(true);
        } catch (Exception ex) {
            return new ResponseEntity(false, ex.getMessage());
        }
    }

    private void AddNav(List<ImportIndexModel> list,Navigation parentnav,DbConfigResource dbConfigResource) throws Exception {
        for (ImportIndexModel im:list){
            Navigation newNav=new Navigation(im.getName());
            //navigation 为 null 时，表示添加的是根节点
            newNav.setParent(parentnav);
            newNav.setCreateUser("system");
            navigationService.create(newNav);
            //下级目录
            if(im.getChildren()!=null){
                AddNav(im.getChildren(),newNav,dbConfigResource);
            }
            //表组
            if(im.getTableGroupModels()!=null){
                for (ImportTableGroupModel tb:im.getTableGroupModels()){
                    TableGroupResource tableGroupResource = tableService.getFourByCode(dbConfigResource,tb.getCode());
                    LemsunResource resource= (LemsunResource) resourceService.get(tableGroupResource.getPid());
                    if (tableGroupResource != null) {
                        try {
                            NavigationComponent navigationComponent = new NavigationComponent();
                            //navigationComponent.setNavigation(newNav);
                            //navigationComponent.setLmsResource(resource);
                            navigationService.addComponent(navigationComponent);
                        } catch (Exception ex) {
                            if (log.isDebugEnabled())
                                log.debug("加入导航出错 PID:" + tableGroupResource.getPid() + " 错误信息:" + ex.getMessage());
                        }
                    }
                }
            }
        }
    }
}
