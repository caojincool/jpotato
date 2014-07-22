package com.lemsun.auth.service.impl;


import com.lemsun.auth.repository.SetOfBooksRepository;
import com.lemsun.auth.service.IAccountService;
import com.lemsun.core.*;
import com.lemsun.core.service.IResourceService;
import com.lemsun.core.service.ISetOfBooksService;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-7-24
 * Time: 上午9:15
 */
@Service
public class SetOfBooksServiceImpl implements ISetOfBooksService  {

    private SetOfBooksRepository repository;

    @Autowired
    public SetOfBooksServiceImpl(SetOfBooksRepository repository) {
        this.repository = repository;
    }

    @Override
    public void insert(SetOfBooks setOfBooks) throws Exception {
        repository.add(setOfBooks);
    }

    @Override
    public void save(SetOfBooks setofbooks) throws Exception {
        repository.save(setofbooks);
    }

    @Override
    public SetOfBooks getSetOfBooks(String id) throws Exception {

        if (StringUtils.isEmpty(id))
            throw new Exception("组件帐套为空,不能获取帐套信息!");
        ObjectId oid=new ObjectId(id);

        return repository.getSetOfBooks(oid);
    }

    @Override
    public List<SetOfBooks> getSetOfBooks() {
        return repository.load();
    }

    @Override
    public void remove(String id) {
        if (StringUtils.isEmpty(id))
            return;

        ObjectId oid=new ObjectId(id);
        repository.remove(oid);
    }

    @Override
    public void insertAccount(SetOfBooksAccount soba) throws Exception {
        repository.addSofAccount(soba);
    }

    @Override
    public void insertResource(SetOfBooksResource sobr) throws Exception {
        repository.addSofResource(sobr);
    }

    @Override
    public void removeSobResourceBySid(String sid) throws Exception {
        repository.removeSofResourceBySid(sid);
    }

    @Override
    public void removeSobResource(SetOfBooksResource sobr) {
        repository.removeSofResource(sobr);
    }

    @Override
    public void removeSobResourceByResource(IResource lmsResource) {
        repository.removeSofResourceByResource(lmsResource);
    }

    @Override
    public void removeSobAccount(SetOfBooksAccount soba) {
        repository.removeSofAccount(soba);
    }

    @Override
    public void removeSobAccountByAccount(IAccount account) {
        repository.removeSofAccountByAccount(account);
    }

    @Override
    public List<SetOfBooks> getSetOfBooksByAccount(IAccount account) {
        return  repository.getSetOfBooksByAccount(account);
    }

    @Override
    public List<SetOfBooks> getSetOfBooksByResource(IResource resource) throws Exception {
        return  repository.getSetOfBooksByResource(resource);
    }

    @Override
    public List<String> getSobResourcePids(String sid) throws Exception {
        return repository.getSobResourcePids(sid);
    }

    @Override
    public List<String> getSobAccounts(String sid) throws Exception {
        return repository.getSobAccounts(sid);
    }

    @Override
    public Page<SetOfBooksAccount> getSetOfBooksAccountBySid(IPageQuery request, String sid) throws Exception {
        if (StringUtils.isEmpty(sid))
            throw new Exception("帐套编码为空!");

        Page<SetOfBooksAccount> accounts=repository.getSetOfBooksAccountBySid(request,sid);
        List<SetOfBooksAccount> list=new ArrayList<>(accounts.getSize());
        IAccountService accountService=Host.getInstance().getContext().getBean(IAccountService.class);

        for (SetOfBooksAccount soba:accounts){
            IAccount account=accountService.get(soba.getAccount());
            if (account!=null){
                soba.setBaseAccount(account);
                list.add(soba);
            }
        }

        return new PageImpl<>(list,request,list.size());
    }

    @Override
    public Page<SetOfBooksResource> getSetOfBooksResourceBySid(IPageQuery request, String sid) throws Exception {

        if (StringUtils.isEmpty(sid))
            throw new Exception("帐套编码为空!");

        Page<SetOfBooksResource> sobrs=repository.getSetOfBooksResourceBySid(request,sid);
        List<SetOfBooksResource> list=new ArrayList<>(sobrs.getSize());
        IResourceService resourceService=Host.getInstance().getContext().getBean(IResourceService.class);

        for (SetOfBooksResource sobr:sobrs){
            LemsunResource lr=resourceService.getBaseResource(sobr.getResourcePid());
            if (lr!=null){
                sobr.setLemsunRessource(lr);
                list.add(sobr);
            }
        }

        return new PageImpl<>(list, request, list.size());
    }
}
