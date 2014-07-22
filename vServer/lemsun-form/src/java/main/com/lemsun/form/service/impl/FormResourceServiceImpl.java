package com.lemsun.form.service.impl;

import com.lemsun.core.formula.IFunctionStatement;
import com.lemsun.form.InnerFunctionDefines;
import com.lemsun.form.repository.FunctionStatementRepository;
import com.lemsun.form.service.IFormResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基本的显示组件帮助
 */
@Service
public class FormResourceServiceImpl implements IFormResourceService {

    private FunctionStatementRepository repository;

    @Autowired
    public FormResourceServiceImpl(FunctionStatementRepository repository) {
        this.repository = repository;
    }

    @Override
    public void insertStatement(IFunctionStatement funtion) {
        repository.save(funtion);
    }

    @Override
    public void updateStatement(IFunctionStatement funtion) {
        repository.update(funtion);
    }

    @Override
    public void dateteStatement(String parentpid, String name) {
        repository.delete(parentpid, name);
    }

    @Override
    public void removeStatement(String parentpid) {
        repository.remove(parentpid);
    }

    @Override
    public List<InnerFunctionDefines> getStatementsByParentpid(String parentpid) {
        return repository.queryForByPid(parentpid);
    }

    @Override
    public InnerFunctionDefines getStatement(String parentpid,String  name) {
        return repository.get(parentpid,name);
    }

    @Override
    public String formatFunciton(String parentpid) {
        List<InnerFunctionDefines> defineses=repository.queryForByPid(parentpid);
        StringBuilder sb=new StringBuilder();
        for (InnerFunctionDefines define:defineses){
            sb.append(define.convertToScript()+"/n");
        }
        return sb.toString();
    }
}
