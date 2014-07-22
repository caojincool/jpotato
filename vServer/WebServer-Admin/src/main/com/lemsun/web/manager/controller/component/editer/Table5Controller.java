package com.lemsun.web.manager.controller.component.editer;

import com.lemsun.core.Host;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.lmstable.Table5Resource;
import com.lemsun.data.service.IDbService;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.web.manager.controller.model.query.Table5Query;
import com.lemsun.web.manager.controller.model.table.CreateTableParam;
import com.lemsun.web.manager.controller.util.WebConstant;
import com.lemsun.web.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * 5代表控制层
 * User: 刘晓宝
 * Date: 13-9-14
 * Time: 上午10:07
 */
@Controller
@RequestMapping("component/dbtabel5")
public class Table5Controller extends BaseController{
    @Autowired
    private IDbService dbService;

    @Autowired
    private JsonObjectMapper jsonObjectMapper;
    @Autowired
    private ILmsTableService lmsTableService;
    /**
     * 显示组件视图
     */
    public ModelAndView view(HttpServletRequest request,Table5Query query) throws IOException {
        ModelAndView view = new ModelAndView("component/dbtabel5/view");
        Page<Table5Resource> data = resourceService.getPageing(query,Table5Resource.class);
        request.setAttribute("data",data);
        view.addObject("query", query);
        view.addObject("m", WebConstant.tableCategoryMap);
        request.getSession().setAttribute("head",1);
        request.getSession().setAttribute("left",11);
        return view;
    }
    /**
     * 编辑5代数据表 视图
     */
    @RequestMapping(value = {"{pid}/edit"}, method = RequestMethod.GET)
    public ModelAndView viewEdit(HttpServletRequest request, @PathVariable String pid) throws IOException {

        ModelAndView view = new ModelAndView("component/editer/TABELGP5");

        ILmsTableService service = Host.getInstance().getContext().getBean(ILmsTableService.class);

        LemsunResource lemsunResource =  service.getCurrentResource(pid);

        Table5Resource table = service.getResource(lemsunResource.getPid());

        String face = service.getTableFace(table.getPid());

        view.addObject("lmsResource", jsonObjectMapper.writeValueAsString(lemsunResource));
        view.addObject("dbs", getdbList());
        view.addObject("face", face);
        view.addObject("table", jsonObjectMapper.writeValueAsString(table));
        view.addObject("dbname", table.getDbConfig().getName());

        return view;
    }
    /**
     * 提交表格编辑后的数据
     * @param request 请求对象
     * @return 返回成功后的视图
     */
    @RequestMapping(value = {"{pid}/edit"}, method = RequestMethod.POST)
    public ResponseEntity<String> viewEdit(HttpServletRequest request,  @PathVariable String pid, String tablejson) {
        ResponseEntity<String> re = new ResponseEntity<>("OK");
        try
        {
            CreateTableParam param = jsonObjectMapper.readValue(tablejson, CreateTableParam.class);
            ILmsTableService service = SpringContextUtil.getBean(ILmsTableService.class);
            Table5Resource oldResource = service.getResource(param.getPid());

            oldResource.setColumns(param.getColumns());
            oldResource .setFace(param.getFace());
            oldResource.setUpdateTime(new Date());
            service.updateResource(oldResource);
        }
        catch (Exception ex) {
            re.setSuccess(false);
            re.setMessage(ex.toString());
        }
        return re;
    }
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
    @Override
    void deleteResource(String pid) throws Exception {
        lmsTableService.deleteTable(pid);
    }

    @Override
    protected ComponentQueryHandler createComponentQueryHandler() throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
