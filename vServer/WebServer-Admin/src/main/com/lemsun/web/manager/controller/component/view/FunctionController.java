package com.lemsun.web.manager.controller.component.view;



import com.lemsun.core.jackson.JsonObjectMapper;


import com.lemsun.form.InnerFunctionDefines;
import com.lemsun.form.service.IFormResourceService;
import com.lemsun.form.service.IFormService;
import com.lemsun.web.manager.controller.model.component.Function;
import com.lemsun.web.model.ResponseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * User: 刘晓宝
 * Date: 13-11-13
 * Time: 上午11:53
 */
@org.springframework.stereotype.Controller
@RequestMapping("function")
public class FunctionController {

    @Autowired
    private IFormResourceService service;


    @Autowired
    private JsonObjectMapper mapper;
    /**
     * 处理创建web组件
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<String> doAdd(HttpServletRequest request) {
        ResponseEntity responseEntity=new ResponseEntity<>();
        try{
            Function function = mapper.readValue(request.getReader(), Function.class);
            InnerFunctionDefines functionStatement=service.getStatement(function.getParentPid(), function.getName());
            if(functionStatement!=null){
                responseEntity.setSuccess(false);
                responseEntity.setMessage("一个组件下函数名称不能重复！");
            }else{
                InnerFunctionDefines innerFunctionDefine= new InnerFunctionDefines();
                BeanUtils.copyProperties(function,innerFunctionDefine);
                innerFunctionDefine.setCreateTime(new Date());
                service.insertStatement(innerFunctionDefine);
                responseEntity.setSuccess(true);
            }
        }catch (Exception e){
            responseEntity.setSuccess(false);
            responseEntity.setMessage("保存出错！");
        }
        return responseEntity;
    }
    /**
     * 处理创建web组件
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<String> doUpdate(HttpServletRequest request) {
        ResponseEntity responseEntity=new ResponseEntity<>();
        try{
            Function function = mapper.readValue(request.getReader(), Function.class);
            InnerFunctionDefines functionStatement = service.getStatement(function.getParentPid(),function.getName());
            BeanUtils.copyProperties(function,functionStatement);
            functionStatement.setUpdateTime(new Date());
            service.updateStatement(functionStatement);
            responseEntity.setSuccess(true);
        }catch (Exception e){
            responseEntity.setSuccess(false);
            responseEntity.setMessage(e.getMessage());
        }
        return responseEntity;
    }
    /**
     * 获取组件函数列表
     */
    @RequestMapping(value = "{parentPid}/function/list", method = RequestMethod.GET)
    public ResponseEntity<List<InnerFunctionDefines>> queryForByPid(HttpServletRequest request,@PathVariable String parentPid) {
        List<InnerFunctionDefines> functionStatementLists=service.getStatementsByParentpid(parentPid);
        ResponseEntity<List<InnerFunctionDefines>> responseEntity = new ResponseEntity<>(functionStatementLists);
        return responseEntity;
    }
    /**
     * 处理创建web组件
     */
    @RequestMapping(value = "{parentPid}/delete", method = RequestMethod.POST)
    public ResponseEntity<String> doDelete(@PathVariable String parentPid,String name) {
        ResponseEntity responseEntity=new ResponseEntity<>();
        try{
            service.dateteStatement(parentPid, name);
            responseEntity.setSuccess(true);
        }catch (Exception e){
            responseEntity.setSuccess(false);
            responseEntity.setMessage(e.getMessage());
        }
        return responseEntity;
    }
    /**
     * 处理创建web组件
     */
    @RequestMapping(value = "{parentPid}/get", method = RequestMethod.POST)
    public ResponseEntity<InnerFunctionDefines> get(@PathVariable String parentPid,String name) {
        ResponseEntity responseEntity=new ResponseEntity<>();
        try{
            InnerFunctionDefines functionStatement=service.getStatement(parentPid, name);
            responseEntity.setSuccess(true);
            responseEntity.setEntity(functionStatement);
        }catch (Exception e){
            responseEntity.setSuccess(false);
            responseEntity.setMessage(e.getMessage());
        }
        return responseEntity;
    }
    /**
     * 获取组件内容
     */
    @ResponseBody
    @RequestMapping(value = "{parentPid}/getContext", method = RequestMethod.GET)
    public void getContext(HttpServletRequest request,
                           HttpServletResponse response, @PathVariable String parentPid,String name) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        InnerFunctionDefines functionStatement=service.getStatement(parentPid, name);
        String context = functionStatement.getContext();
        if(context!=null){
            response.getWriter().write(context);
        }else{
            response.getWriter().write("");
        }
    }
    /**
     * 获取组件内容
     */
    @ResponseBody
    @RequestMapping(value = "{parentPid}/formatFunciton", method = RequestMethod.GET)
    public void formatFunciton(HttpServletRequest request,
                           HttpServletResponse response, @PathVariable String parentPid) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String context = service.formatFunciton(parentPid);
        if(context!=null){
            response.getWriter().write(context);
        }else{
            response.getWriter().write("");
        }
    }
}
