package com.lemsun.component.lkg.service;

import com.lemsun.component.lkg.BasePackage;
import com.lemsun.component.lkg.VirtualDirectory;
import com.lemsun.component.lkg.VirtualDirectoryComponent;
import com.lemsun.component.lkg.model.AddVdcForm;
import com.lemsun.core.*;
import org.springframework.data.domain.Page;


import java.util.List;

/**
 *
 * User: lmy
 * Date: 13-8-26
 * Time: 下午2:32
 * To change this template use File | Settings | File Templates.
 */
public interface IVirtualDirectoryService {


    /**
     * 返回pid为LCD+lid（组件包id）下目录
     * @return 返回根节点
     */
    VirtualDirectory getRoot(String lid);





    /**
     * 创建保存一个导航节点, 并把导航节点中的子节点跟组件一起保存
     * @param VirtualDirectory 节点
     */
    void create(VirtualDirectory VirtualDirectory);

    /**
     * 根据pid获取VirtualDirectory
     * @param pid 组件pid
     * @return 组件
     */
    VirtualDirectory getVirtualDirectoryByPid(String pid);
    /**
     * 删除导航信息，通过pid
     * @param pid 导航信息pid
     */
    void deleteVirtualDirectory(String pid);


    /**
     * 删除导航组件挂载信息，通过主键
     * @param ids 需要删除id集合
     */
    void delVirtualDirectoryComponentByIds(String[] ids);

    /**
     * 导航挂载组件分页
     * @param request 分页请求对象
     * @return 返回分页数据
     */
    Page<VirtualDirectoryComponent> getNComponentPagging(AbstractPageRequest request);

    /**
     * 添加导航挂载组件
     *
     * @throws Exception
     */
    void addVirtualDirectoryComponent(AddVdcForm addVdcForm) throws Exception;

    /**
     * 获取软件包下所有组件
     * @param packageId
     * @return
     */
    List<VirtualDirectoryComponent> getVirtualDirectoryComponentsByPackageId(String packageId);

    /**
     * 根据pid获取BasePackage
     * @param pid 组件pid
     * @return 组件
     */
    List<BasePackage> getBasePackageByPid(String pid);

    /**
     * 根据组件pid 移除所有虚拟文件夹下组件
     * @param pid
     */
     void removeComponent(String pid);
}
