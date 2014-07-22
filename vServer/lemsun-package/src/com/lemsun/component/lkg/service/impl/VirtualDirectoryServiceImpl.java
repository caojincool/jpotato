package com.lemsun.component.lkg.service.impl;

import com.lemsun.component.lkg.BasePackage;
import com.lemsun.component.lkg.VirtualDirectory;
import com.lemsun.component.lkg.VirtualDirectoryComponent;

import com.lemsun.component.lkg.model.AddVdcForm;
import com.lemsun.component.lkg.repository.VirtualDirectoryRepository;
import com.lemsun.component.lkg.service.IVirtualDirectoryService;
import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.core.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lmy
 * Date: 13-8-26
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class VirtualDirectoryServiceImpl  implements IVirtualDirectoryService {
    private VirtualDirectoryRepository repository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    public VirtualDirectoryServiceImpl(VirtualDirectoryRepository repository) {
        this.repository = repository;
    }
    @Override
    public VirtualDirectory getRoot(String lid) {
        return repository.getRoot(lid);
    }



    @Override
    public void create(VirtualDirectory VirtualDirectory) {
        repository.create(VirtualDirectory);
    }

    @Override
    public VirtualDirectory getVirtualDirectoryByPid(String pid) {
        return repository.getNavigationByPid(pid);
    }



    @Override
    public void deleteVirtualDirectory(String pid) {
        repository.deleteVirtualDirectory(pid);
    }





    @Override
    public void delVirtualDirectoryComponentByIds(String[] ids) {
        //To change body of implemented methods use File | Settings | File Templates.
        repository.delVirtualDirectoryComponentByIds(ids);
    }

    @Override
    public Page<VirtualDirectoryComponent> getNComponentPagging(AbstractPageRequest request) {
        Page<VirtualDirectoryComponent> virtualDirectoryComponents=repository.getVDComponentPaggingByVirtualDirectoryId(request);
         List<String> pids=new ArrayList<>();
        for(VirtualDirectoryComponent vd :virtualDirectoryComponents){
            pids.add(vd.getPid());
        }
        List<LemsunResource> lemsunResources=resourceRepository.getResourceByPids(pids);
        for(VirtualDirectoryComponent vdc:virtualDirectoryComponents){
            for(LemsunResource lr:lemsunResources){
              if(vdc.getPid().equalsIgnoreCase(lr.getPid())){
                  vdc.encapstation(lr);
              }
            }
        }
        return   virtualDirectoryComponents;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addVirtualDirectoryComponent(AddVdcForm addVdcForm) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        repository.addVirtualDirectoryComponent(addVdcForm);
    }

    @Override
    public List<VirtualDirectoryComponent> getVirtualDirectoryComponentsByPackageId(String packageId) {
       return repository.getVirtualDirectoryComponentsByPackageId(packageId);
    }

    @Override
    public List<BasePackage> getBasePackageByPid(String pid) {
        return repository.getBasePackageByPid(pid);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeComponent(String pid) {
        repository.removeComponent(pid);
    }
}
