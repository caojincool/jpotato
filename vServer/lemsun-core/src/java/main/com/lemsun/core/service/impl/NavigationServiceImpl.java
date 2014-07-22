package com.lemsun.core.service.impl;
import com.lemsun.core.*;
import com.lemsun.core.repository.NavigationRepository;
import com.lemsun.core.service.INavigationService;
import com.lemsun.core.service.IResourceService;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 实现对导航及导航挂载组件的服务
 * User: Xudong
 * Date: 12-10-23
 * Time: 下午3:05
 */
@Service
public class NavigationServiceImpl implements INavigationService {

    private NavigationRepository repository;

    @Autowired
    public NavigationServiceImpl(NavigationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Navigation getRoot() {
        Navigation navigation=repository.getRoot();
        //计算当前导航下的组件个数
        setCount(navigation);

        return navigation;
    }

    private void setCount(Navigation navigation){
        if (navigation == null)
            return;

        List<Navigation> child=navigation.getChild();

        if (child == null || child.isEmpty()) return;

        for (Navigation navigation1:child){

            navigation1.setNavResourceTotal(repository.getCountByNavPid(navigation1.getPid()));
            setCount(navigation1);
        }

    }

    @Override
    public void create(Navigation navigation) throws Exception {
        repository.create(navigation);
    }


    @Override
    public Navigation get(String pid) {
        return repository.get(pid);
    }


    @Override
    public void edit(Navigation navigation) {
        repository.edit(navigation);
    }


    @Override
    public void remove(String pid) {
        repository.remove(pid);

        //移除对应的关系
        List<NavigationComponent> components=getAllComponent(pid);
        for (NavigationComponent component:components){
            removeComponent(component);
        }
    }

    @Override
    public List<Navigation> getChild(String oid) {
        ObjectId navid=new ObjectId(oid);

        return repository.getChild(navid);
    }

    @Override
    public void addComponent(NavigationComponent component) throws Exception {
        if (component.getNavPid() == null || component.getNavPid()== "")
            throw new Exception("没有导航文件夹对象.");
        if (component.getResourcePid() == null)
            throw new Exception("组件对象为空!");
        if (StringUtils.isEmpty(component.getOperator()))
            component.setOperator(Host.getInstance().getCurrentAccount().getAccount());
        if (component.getHitchTime() == null)
            component.setHitchTime(new Date());
        repository.addComponent(component);
    }

    @Override
    public void addComponents(List<NavigationComponent> components) {
        repository.addComponents(components);
    }

    @Override
    public void removeComponent(NavigationComponent nc){
        repository.removeComponent(nc);
    }

    @Override
    public void removeComponent(IResource lmsResource){
        List<NavigationComponent> navigationComponents = repository.getNavigationByResourcePid(lmsResource);

        for (NavigationComponent navigationComponent : navigationComponents)
            removeComponent(navigationComponent);
    }

    @Override
    public List<String> getAllResourcePidByNavId(String navid){
        return repository.getAllResourcePidByNavid(navid);
    }

    @Override
    public List<NavigationComponent> getAllComponent(String navid) {
        return repository.getAllComponents(navid);
    }

    @Override
    public Page<NavigationComponent> getNComponentPagging(AbstractPageRequest request) {
        Page<NavigationComponent> componentPages = repository.getNComponentPagging(request);
        List<NavigationComponent> components = new ArrayList<>(componentPages.getSize());

        IResourceService resourceService=Host.getInstance().getContext().getBean(IResourceService.class);

        for (NavigationComponent navigationComponent : componentPages) {
            LemsunResource lemsunResource = resourceService.getBaseResource(navigationComponent.getResourcePid());
            navigationComponent.setLemsunResource(lemsunResource);
            components.add(navigationComponent);
        }
        return new PageImpl<>(components, request, componentPages.getSize());
    }

    @Override
    public List<Navigation> getNavigationByResource(IResource resource){
        return repository.getNavigationByResourcePid(resource.getPid());
    }
}

