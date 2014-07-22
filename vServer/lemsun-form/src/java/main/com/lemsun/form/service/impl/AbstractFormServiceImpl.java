package com.lemsun.form.service.impl;

import com.lemsun.core.AbstractLemsunResource;
import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.formula.IFunctionStatement;
import com.lemsun.core.repository.AbstractRepositorySupport;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.impl.AbstractResourceSupportService;
import com.lemsun.core.util.Pid;
import com.lemsun.form.FormResource;
import com.lemsun.form.InnerFunctionDefines;
import com.lemsun.form.repository.FormResourceRepository;
import com.lemsun.form.repository.FunctionStatementRepository;
import com.lemsun.form.service.IFormResourceService;
import com.lemsun.form.service.IFormService;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * 提供给单据的基本操作
 * User: Xudong
 * Date: 12-10-17
 * Time: 下午3:16
 */
public abstract class AbstractFormServiceImpl<T extends FormResource> extends AbstractResourceSupportService<T> implements IFormService<T> {


    private IFormResourceService service;

    protected AbstractFormServiceImpl(IResourceService resourceService, IFormResourceService functionService) {
        super(resourceService);
        service = functionService;
    }

    @Override
    public String getContext(T resource) {
        return getResourceService().getContent(resource.getPid());
    }

    @Override
    public void update(T resource, String context) {
        getResourceService().updateContent(resource.getPid(), context);
    }


    @Override
    public GridFSDBFile getAttach(String pid, String filename, String filetype) {
        return getResourceService().getAttachFile(pid, filename, filetype);
    }

    @Override
    public void updateAttach(String pid, String filename, String filetype, InputStream stream) {
        getResourceService().uploadAttachFile(pid, stream, filename, filetype);
    }

    @Override
    public void removeAttach(String pid, String filename, String filetype) {
        getResourceService().removeAttach(pid, filename, filetype);
    }

    @Override
    public void insertStatement(IFunctionStatement funtion) {
        service.insertStatement(funtion);
    }

    @Override
    public void updateStatement(IFunctionStatement funtion) {
        service.updateStatement(funtion);
    }

    @Override
    public void dateteStatement(String parentpid,String name) {
        service.dateteStatement(parentpid, name);
    }

    @Override
    public void removeStatement(String parentpid) {
       service.removeStatement(parentpid);
    }

    @Override
    public List<InnerFunctionDefines> getStatementsByParentpid(String parentpid) {
        return service.getStatementsByParentpid(parentpid);
    }

    @Override
    public InnerFunctionDefines getStatement(String parentpid,String  name) {
        return service.getStatement(parentpid, name);
    }

    @Override
    public String formatFunciton(String parentpid) {
        return service.formatFunciton(parentpid);
    }

}
