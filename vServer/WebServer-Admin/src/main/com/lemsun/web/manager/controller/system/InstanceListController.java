package com.lemsun.web.manager.controller.system;

import com.lemsun.core.Plateform;
import com.lemsun.core.PlateformInstance;
import com.lemsun.core.service.IPlateformInstanceService;
import com.lemsun.core.service.IPlateformService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.query.InstanceQuery;
import com.lemsun.web.manager.controller.model.system.CategoryModel;
import com.lemsun.web.manager.controller.model.system.InstanceBean;
import com.lemsun.web.manager.controller.model.system.InstanceModel;
import com.lemsun.web.model.HeaderTitle;
import com.lemsun.web.model.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统实例控制器
 * User: Lucklim
 * Date: 12-11-19
 * Time: 下午12:49
 */
@Controller
@RequestMapping("system/instance")
public class InstanceListController {

    @Autowired
    private IPlateformService plateformService;

    @Autowired
    private IPlateformInstanceService plateformInstanceService;

    private final static Logger log = LoggerFactory.getLogger(InstanceListController.class);

    /**
     * 默认系统实例路由
     *
     * @return 系统实例集合界面
     */
    @RequestMapping(value = {"", "/", "view", "/list"})
    public ModelAndView view(HttpServletRequest request) {
        request.getSession().setAttribute("head",5);
        request.getSession().setAttribute("left",3);

        ModelAndView view=new ModelAndView("system/instanceView");
        List<Plateform> result = plateformService.getAllCategory();

        view.addObject("categorylist",result);
        return view;
    }

    /**
     * 分页获取系统实例路由
     *
     * @param query 查询条件
     * @return 系统实例分页数据
     */
    @RequestMapping("/list/get")
    public ResponseEntity<ArrayList<InstanceBean>> getData(InstanceQuery query) {
        ResponseEntity<ArrayList<InstanceBean>> responseEntity = new ResponseEntity<ArrayList<InstanceBean>>();

        Page<PlateformInstance> result = plateformInstanceService.getPagging(query);

        ArrayList<InstanceBean> list = new ArrayList<InstanceBean>();
        for (int i = 0; i < result.getContent().size(); i++) {
            InstanceBean dt = new InstanceBean();
            dt.setId(result.getContent().get(i).getId());
            dt.setName(result.getContent().get(i).getName());
            dt.setIp(result.getContent().get(i).getIp());
            dt.setSysName(result.getContent().get(i).getOwner().getName());
            dt.setAddress(result.getContent().get(i).getAddress());
            dt.setTime(result.getContent().get(i).getQyDate());
            list.add(dt);
        }
        //setSuccess设置为false时，不会刷新数据
        responseEntity.setSuccess(true);
        responseEntity.setEntity(list);
        responseEntity.setTotalCount(result.getTotalElements());
        return responseEntity;
    }

    /**
     * 获取所有系统类型
     *
     * @param response 标识
     * @return 所有系统类型集合
     */
    @ResponseBody
    @RequestMapping("/list/getcategory")
    public ResponseEntity<ArrayList<CategoryModel>> getAllCategoryData(HttpServletResponse response) {
        ResponseEntity<ArrayList<CategoryModel>> responseEntity = new ResponseEntity<ArrayList<CategoryModel>>();

        List<Plateform> result = plateformService.getAllCategory();
        ArrayList<CategoryModel> list = new ArrayList<CategoryModel>();
        for (Plateform aResult : result) {
            CategoryModel dt = new CategoryModel();
            dt.setCid(aResult.getId().toString());
            dt.setCname(aResult.getName());
            list.add(dt);
        }

        if (list.size() > 0) responseEntity.setSuccess(true);
        responseEntity.setEntity(list);
        return responseEntity;
    }

    /**
     * 新增实例界面路由
     *
     * @return 跳转到新增实例界面
     */
    @RequestMapping("/create")
    public ModelAndView createView(InstanceModel context, HttpServletRequest request) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        List<Plateform> pf = plateformService.getAllCategory();
        ModelAndView view = new ModelAndView("system/instance_create");
        view.addObject("imodel", context);
        view.addObject("categorylist", pf);
        return view;
    }

    /**
     * 创建系统实例 安全信息
     *
     * @param context 实例
     * @return 创建系统实例 安全信息界面
     */
    @RequestMapping(value = "/create/security", method = RequestMethod.POST)
    public ModelAndView createSecurityView(InstanceModel context, HttpServletRequest request) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        ModelAndView view = new ModelAndView("system/instance_security");
        view.addObject("imodel", context);
        return view;
    }

    /**
     * 创建系统实例 相关注册信息
     *
     * @param context 实例
     * @return 创建系统实例 相关注册信息界面
     */
    @RequestMapping(value = "/create/reginfo", method = RequestMethod.POST)
    public ModelAndView createReginfoView(InstanceModel context, HttpServletRequest request) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        ModelAndView view = new ModelAndView("system/instance_reginfo");
        view.addObject("imodel", context);
        return view;
    }

    /**
     * 新增系统实例
     *
     * @param context 实例对象
     * @return 系统列表界面
     * @throws java.text.ParseException 日期转换异常
     */
    @RequestMapping(value = "/create/save", method = RequestMethod.POST)
    public ModelAndView saveContext(InstanceModel context) throws ParseException {

        //实例化此类时，需传入两个参数，参数一：实例名称 参数二：类型
        PlateformInstance plateformInstance = context.encapseModel();
        plateformInstance.setOwner(plateformService.getByCategory(context.getCategorytype()));

        plateformInstanceService.create(plateformInstance);
        return resetView();
    }

    /**
     * 修改系统实例界面
     *
     * @param pid 系统实例pid
     * @return 返回修改系统实例界面
     */
    @RequestMapping("/{pid}/update")
    public ModelAndView getUpdateView(@PathVariable("pid") String pid, HttpServletRequest request) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        if (StringUtils.isEmpty(pid))
            return resetView();

        if (log.isDebugEnabled())
            log.debug("修改ID为{}的实例信息", pid);

        PlateformInstance pfi = plateformInstanceService.get(pid);

        ModelAndView view = new ModelAndView("system/instance_edit");
        view.addObject("listone", pfi);
        return view;
    }

    /**
     * 修改系统实例
     *
     * @param context 系统实例模型
     * @return 修改后，返回系统实例列表
     * @throws Exception 修改实例异常
     */
    @RequestMapping(value = "/update/save", method = RequestMethod.POST)
    public ModelAndView updateContext(InstanceModel context) throws Exception {

        PlateformInstance plateformInstance = plateformInstanceService.get(context.getPid());
        if (plateformInstance == null) {
            return resetView();
        }

        plateformInstance = context.encapseModel(plateformInstance);

        plateformInstanceService.update(plateformInstance);

        return resetView();
    }

    /**
     * 系统实例预览界面路由
     *
     * @param pid 系统实例pid
     * @return 系统实例预览界面
     */
    @RequestMapping("/{pid}/preview")
    public ModelAndView getPreView(@PathVariable("pid") String pid, HttpServletRequest request) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        if (StringUtils.isEmpty(pid))
            return resetView();

        PlateformInstance pfi = plateformInstanceService.get(pid);
        ModelAndView view = new ModelAndView("system/instance_preview");
        view.addObject("listone", pfi);
        return view;
    }

    /**
     * 修改系统实例界面
     *
     * @param pid 系统实例pid
     * @return 返回修改系统实例界面
     */
    @RequestMapping("/{pid}/deleteview")
    public ModelAndView getDeleteView(@PathVariable("pid") String pid, HttpServletRequest request) {
        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        if (StringUtils.isEmpty(pid))
            return resetView();

        if (log.isDebugEnabled())
            log.debug("删除ID为{}的实例信息", pid);

        PlateformInstance pfi = plateformInstanceService.get(pid);

        ModelAndView view = new ModelAndView("system/instance_delete");
        view.addObject("listone", pfi);
        return view;
    }

    /**
     * 删除系统实例
     *
     * @param pid 系统实例pid
     * @return 系统实例列表界面
     * @throws Exception pid为空
     */
    @RequestMapping("/{pid}/delete")
    public String deleteByPid(@PathVariable("pid") String pid) throws Exception {

        if (StringUtils.isEmpty(pid))
            throw new Exception("pid为空，无法删除!");

        if (log.isDebugEnabled())
            log.debug("删除失败，ID为空{}", pid);

        plateformInstanceService.delete(pid);

        return "redirect:"
                + PrepareModelInteceptor.getRootPath()
                + "system/instance";
    }

    /**
     * 返回系统实例界面方法
     *
     * @return 系统实例界面
     */
    protected static ModelAndView resetView() {
        return new ModelAndView("redirect:" + PrepareModelInteceptor.getRootPath() + "system/instance");
    }
}

