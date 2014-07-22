package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.auth.BaseAccount;
import com.lemsun.core.*;
import com.lemsun.core.auth.Security;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.service.IPlateformInstanceService;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.lmstable.Column;
import com.lemsun.data.lmstable.Table5GroupResource;
import com.lemsun.data.lmstable.Table5Resource;
import com.lemsun.data.service.IDbService;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.web.inteceptor.PrepareModelInteceptor;
import com.lemsun.web.manager.controller.model.component.TableGroup5;
import com.lemsun.web.manager.controller.model.component.WebPageResourceModel;
import com.lemsun.web.manager.controller.model.query.TableGroup5Query;
import com.lemsun.web.manager.controller.model.table.CreateTableParam;
import com.lemsun.web.model.HeaderTitle;
import com.lemsun.web.model.ResponseEntity;
import com.lemsun.web.model.view.Table5GroupItemView;
import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * User: 宗旭东
 * Date: 13-3-18
 * Time: 下午5:30
 */
@Controller
@RequestMapping("component/tabelgp5")
public class TableGroup5Controller extends BaseController{

    @Autowired
    private IDbService dbService;

    @Autowired
    private JsonObjectMapper jsonObjectMapper;

    @Autowired
    private ILmsTableService lmsTableService;
    /**
     * 获取数据连接对象的json字符串
     */
    public String getdbList() throws IOException {
        List<DbConfigResource> res = dbService.getAll();

        List<Map> json = new ArrayList<>(res.size());

        for (DbConfigResource r : res) {
            Map<String, Object> row = new HashMap<>();
            row.put("pid", r.getPid());
            row.put("name", r.getName());
            row.put("category", r.getDbCategory().getName());
            json.add(row);
        }
        return jsonObjectMapper.writeValueAsString(json);
    }

    /**
     * 创建5代数据库表 视图
     */
    @RequestMapping(value = {"add"}, method = RequestMethod.GET)
    public ModelAndView view(HttpServletRequest request) throws IOException {

        HeaderTitle title = PrepareModelInteceptor.getPageHeaderTitle(request);
        title.setDisable(true);

        if (request.getSession().getAttribute("resource") == null) {
            return new ModelAndView("redirect:" + PrepareModelInteceptor.getRootPath() + "component/main/create");
        }

        ModelAndView view = new ModelAndView("component/add/TABELGP5");
        LemsunResource lemsunResource = (LemsunResource) request.getSession().getAttribute("resource");

        PlateformInstance plateformInstance = Host.getInstance().getContext().getBean(IPlateformInstanceService.class).getSystemInstance();

        view.addObject("plateform", plateformInstance.getToken());
        view.addObject("lmsResource", jsonObjectMapper.writeValueAsString(lemsunResource));
        view.addObject("dbs", getdbList());
        view.addObject("pid", lemsunResource.getPid());
        view.addObject("category", lemsunResource.getCategory());
        return view;
    }

    /**
     * 处理创建5代数据库表
     */
    @RequestMapping(value = {"create/table"}, method = RequestMethod.POST)
    @Security(ignore=true)
    public ResponseEntity<String> createTable(HttpServletRequest request) {

        ResponseEntity<String> re = new ResponseEntity<>("OK");

        try
        {
            String data = IOUtils.toString(request.getInputStream(), Charsets.UTF_8);
            LemsunResource lemsunResource = (LemsunResource) request.getSession().getAttribute("resource");

            CreateTableParam param = jsonObjectMapper.readValue(data, CreateTableParam.class);

            Table5GroupResource groupResource = param.createGroupResource(lemsunResource);

            SpringContextUtil.getBean(ILmsTableService.class).create(groupResource);

        }
        catch (Exception ex) {
            re.setSuccess(false);
            re.setMessage(ex.toString());

        }

        return re;
    }

    /**
     * 编辑5代数据表 视图
    */
    @RequestMapping(value = {"{pid}/edit"}, method = RequestMethod.GET)
    public ModelAndView viewEdit(HttpServletRequest request, @PathVariable String pid) throws IOException {
        ModelAndView view = new ModelAndView("component/editer/tablesgp5");
        view.addObject("pid",pid);
        return view;
    }
    @RequestMapping(value = {"{pid}/getAllTableColumns"})
    @ResponseBody
    public  List<Column>   getAllTableColumns(@PathVariable String pid,int cate) {
        List<Column> cols=lmsTableService.getResourceColumns(pid,cate);
        return cols;
    }
    /**
     * 提交表格编辑后的数据
     * @param request 请求对象
     * @return 返回成功后的视图
     */
    @RequestMapping(value = {"{pid}/edit"}, method = RequestMethod.POST)
    public  ResponseEntity<String>  viewEdit(HttpServletRequest request) {
        ResponseEntity<String> re = new ResponseEntity<>("OK");
        try
        {
            TableGroup5 model = mapper.readValue(request.getReader(), TableGroup5.class);
            lmsTableService.updateResourceColumns(model.getPid(),model.getCate(),model.getCols());
        }
        catch (Exception ex) {
            re.setSuccess(false);
            re.setMessage("修改表组失败！");
        }
        return re;
    }

    /**
     * 显示组件视图
     */
    @RequestMapping("view")
    public ModelAndView view(HttpServletRequest request, TableGroup5Query query) throws IOException {
        return super.view(query);
    }
    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        ComponentQueryHandler handler= new ComponentQueryHandler(){

            @Override
            protected Map<String, Integer> createMap() {
                Map<String,Integer> map=new HashMap<>();
                map.put("head",1);
                map.put("left",24);
                return map;
            }
            @Override
            protected  Page<Table5GroupResource> executeQueryComponent() {
                return   lmsTableService.getPageing(query);
            }

            @Override
            protected  Page<Table5GroupItemView> afterRelatedQueryHandler() {
                List<Table5GroupItemView> webskinItemViewList=new ArrayList<>(data.getSize());
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
                    Table5GroupItemView webskinItemView=new Table5GroupItemView((Table5GroupResource)webskin,account);
                    webskinItemViewList.add(webskinItemView);

                }

                return  new PageImpl<>(webskinItemViewList, query, data.getTotalElements());
            }
        };
        return handler;
    }
    @Override
    public ModelAndView delete(String type, HttpServletRequest request, @PathVariable String pid) throws Exception {
        ModelAndView view= super.delete(type, request, pid);
        List<Table5Resource>  table5Resources=lmsTableService.getTable5Resources(pid);
        view.addObject("table5Resources",table5Resources);
        return view;
    }

    @Override
    public ModelAndView details(String type, HttpServletRequest request, @PathVariable String pid) throws Exception {
        ModelAndView view=super.details(type, request, pid);
        List<Table5Resource>  table5Resources=lmsTableService.getTable5Resources(pid);
        view.addObject("table5Resources",table5Resources);
        return view;
    }

    @Override
    void deleteResource(String pid) throws Exception {
        System.err.print(pid);
        lmsTableService.deleteTableGroup(pid);
    }
}
