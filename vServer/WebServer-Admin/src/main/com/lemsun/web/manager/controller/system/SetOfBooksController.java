package com.lemsun.web.manager.controller.system;

import com.lemsun.web.manager.controller.model.query.AccountQuery;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.*;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.core.query.LemsunResourceQuery;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.ISetOfBooksService;
import com.lemsun.web.manager.controller.model.query.ComponentQuery;
import com.lemsun.web.manager.controller.model.system.SetOfBooksAccounts;
import com.lemsun.web.manager.controller.model.system.SetofBooksResources;
import com.lemsun.web.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-7-25
 * Time: 上午8:19
 */
@Controller
@RequestMapping("setofbooks")
public class SetOfBooksController {

    @Autowired
    private ISetOfBooksService service;

    @Autowired
    private JsonObjectMapper mapper;

    /**
     * 帐套管理视图
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {
        request.getSession().setAttribute("head",7);
        request.getSession().setAttribute("left",4);
        return "system/setofbooks/setOfBooksView";
    }
    /**
     * 帐套管理视图
     */
    @RequestMapping(value = "/viewiframe")
    public String viewiframe() {
        return "system/setofbooks/index";
    }
    /**
     * 获取所有帐套信息
     */
    @RequestMapping(value = "/view")
    public ResponseEntity<List<SetOfBooks>> view() {
        List<SetOfBooks> list = service.getSetOfBooks();

        return new ResponseEntity<>(list);
    }

    /**
     * 增加帐套
     */
    @RequestMapping(value = "/add")
    public ResponseEntity<String> add(SetOfBooks setOfBooks) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>();

        String message = "插入成功";
        boolean success = true;

        try {
            service.insert(setOfBooks);
        } catch (Exception e) {
            message = "由于" + e.getMessage() + "插入失败";
            success = false;
        }

        responseEntity.setSuccess(success);
        responseEntity.setMessage(message);
        return responseEntity;
    }

    /**
     * 保存一个帐套
     */
    @RequestMapping(value = "/save")
    public ResponseEntity<String> save(SetOfBooks setOfBooks) throws Exception {
        ResponseEntity<String> responseEntity = new ResponseEntity<>();

        String message = "保存成功";
        boolean success = true;

        try {
            service.save(setOfBooks);
        } catch (Exception e) {
            message = "由于" + e.getMessage() + "保存失败";
            success = false;
        }

        responseEntity.setSuccess(success);
        responseEntity.setMessage(message);
        return responseEntity;
    }

    /**
     * 返回某个帐套下的组件列表
     */
    @RequestMapping(value = "/sbResource/list")
    public ResponseEntity<Page<SetOfBooksResource>> getResources(String id, int limit, int page, int start) {
        LemsunResourceQuery query = new LemsunResourceQuery();
        query.setOffset(start);
        query.setPageSize(limit);
        query.setPageNumber(page);

        ResponseEntity<Page<SetOfBooksResource>> responseEntity=new ResponseEntity<>();
        String message = "加载成功";
        boolean success = true;

        Page<SetOfBooksResource> resources = null;
        try {
            resources = service.getSetOfBooksResourceBySid(query, id);
        } catch (Exception e) {
            message = "由于" + e.getMessage() + "保存失败";
            success = false;
        }

        responseEntity.setSuccess(success);
        responseEntity.setMessage(message);
        responseEntity.setEntity(resources);
        responseEntity.setTotalCount(resources.getTotalPages());

        return responseEntity;
    }

    @RequestMapping(value = "/sobAccount/list")
    public ResponseEntity<Page<SetOfBooksAccount>> getSobAccounts(String sid, int limit, int page, int start) {
        LemsunResourceQuery query = new LemsunResourceQuery();
        query.setOffset(start);
        query.setPageSize(limit);
        query.setPageNumber(page);

        ResponseEntity<Page<SetOfBooksAccount>> responseEntity=new ResponseEntity<>();
        String message = "加载成功";
        boolean success = true;

        Page<SetOfBooksAccount> resources = null;
        try {
            resources = service.getSetOfBooksAccountBySid(query, sid);
        } catch (Exception e) {
            message = "由于" + e.getMessage() + "保存失败";
            success = false;
        }

        responseEntity.setSuccess(success);
        responseEntity.setMessage(message);
        responseEntity.setEntity(resources);
        responseEntity.setTotalCount(resources.getTotalPages());

        return responseEntity;
    }

    /**
     *　获取被选组件
     */
    @RequestMapping(value = "/lsResource/list")
    public ResponseEntity<Page<LemsunResource>> getCheckResource(ComponentQuery query) throws Exception {

        //获取该帐套下所有的组件编码
        List<String> pids=service.getSobResourcePids(query.getNavPid());
        query.setPids(pids);

        IResourceService resourceService= Host.getInstance().getContext().getBean(IResourceService.class);

        Page<LemsunResource> lemsunResources=resourceService.getPageing(query, LemsunResource.class);

        return new ResponseEntity<>(lemsunResources);
    }

    /**
     * 获取被选账户
     */
    @RequestMapping(value = "/baccount/list")
    public ResponseEntity<Page<IAccount>> getCheckAccount(AccountQuery query) throws Exception {

        List<String> accouns=service.getSobAccounts(query.getSid());
        query.setAccounts(accouns);

        IAccountService accountService=Host.getInstance().getContext().getBean(IAccountService.class);

        Page<IAccount> data=accountService.getPageData(query);

        return new ResponseEntity<>(data);
    }

    /**
     * 获取一个帐套下的账户
     */
    @RequestMapping(value = "/sbAccount/list")
    public ResponseEntity<Page<SetOfBooksAccount>> getAccount(String sid, int limit, int page, int start) throws Exception {
        AccountQuery query = new AccountQuery();
        query.setOffset(start);
        query.setPageSize(limit);
        query.setPageNumber(page);

        Page<SetOfBooksAccount> accounts = service.getSetOfBooksAccountBySid(query, sid);

        ResponseEntity<Page<SetOfBooksAccount>> responseEntity = new ResponseEntity<>();
        responseEntity.setTotalCount(accounts.getTotalElements());
        responseEntity.setSuccess(true);
        responseEntity.setEntity(accounts);

        return responseEntity;
    }

    /**
     * 移除一个帐套
     */
    @RequestMapping(value = "/remove")
    public ResponseEntity<String> remove(String id) {
        service.remove(id);

        return new ResponseEntity<>("success");
    }

    /**
     * 挂载组件
     */
    @RequestMapping(value = "/sbResource/add")
    public ResponseEntity<String> addResource(HttpServletRequest request) throws IOException {

        SetofBooksResources booksResources = mapper.readValue(request.getInputStream(), SetofBooksResources.class);
        ResponseEntity<String> responseEntity = new ResponseEntity<>();

        String message = "保存成功";
        boolean success = true;

        try {
            for (SetOfBooksResource sobr:booksResources.getResources()){
                service.insertResource(sobr);
            }
        } catch (Exception e) {
            message = "由于" + e.getMessage() + "保存失败";
            success = false;
        }

        responseEntity.setSuccess(success);
        responseEntity.setMessage(message);
        return responseEntity;
    }

    /**
     * 移除一个组件
     */
    @RequestMapping(value = "/sbResource/remove")
    public ResponseEntity<String> removeResource(HttpServletRequest request){
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        String message = "移除成功!";
        boolean success = true;

        SetOfBooksResource booksResource = null;
        try {
            booksResource = mapper.readValue(request.getInputStream(),SetOfBooksResource.class);
            service.removeSobResource(booksResource);
        } catch (IOException e) {
            message = "组件关系移除异常,请联系管理员!";
            success = false;
        }

        responseEntity.setSuccess(success);
        responseEntity.setMessage(message);
        return responseEntity;
    }

    /**
     * 增加一个账户
     */
    @RequestMapping(value = "/sbAccount/add")
    public ResponseEntity<String> addAccount(HttpServletRequest request) throws IOException {

        SetOfBooksAccounts setOfBooksAccounts = mapper.readValue(request.getInputStream(), SetOfBooksAccounts.class);
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        String message = "挂载成功!";
        boolean success = true;
        try {
            for (SetOfBooksAccount soba:setOfBooksAccounts.getSetOfBooksAccount()){
                service.insertAccount(soba);
            }
        } catch (Exception ex) {
            message = "由于" + ex.getMessage() + "保存失败";
            success = false;
        }
        responseEntity.setSuccess(success);
        responseEntity.setMessage(message);
        return responseEntity;
    }

    /**
     * 移除一个账户
     */
    @RequestMapping(value = "/sbAccount/remove")
    public ResponseEntity<String> removeAccount(HttpServletRequest request){
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        String message = "移除成功!";

        boolean success = true;
        SetOfBooksAccount booksAccount= null;
        try {
            booksAccount = mapper.readValue(request.getInputStream(),SetOfBooksAccount.class);
            service.removeSobAccount(booksAccount);
        } catch (IOException e) {
            message = "账户移除异常,请联系管理员!";
            success = false;
        }

        responseEntity.setSuccess(success);
        responseEntity.setMessage(message);
        return responseEntity;

    }
}
