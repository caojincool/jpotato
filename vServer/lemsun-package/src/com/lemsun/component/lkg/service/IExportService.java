package com.lemsun.component.lkg.service;

import com.lemsun.component.lkg.BasePackage;
import com.lemsun.component.lkg.ExportPackage;
import com.lemsun.component.lkg.PackageException;
import com.lemsun.component.lkg.VirtualDirectoryComponent;
import com.lemsun.core.AbstractPageRequest;
import org.springframework.data.domain.Page;

import java.io.OutputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-6-17
 * Time: 下午4:16
 */
public interface IExportService {



    /**
     * 增加导出组件包
     *
     * @param ep
     */
    BasePackage add(BasePackage ep) throws PackageException;

    /**
     * 编辑基本信息
     * @param ep
     * @return
     * @throws PackageException
     */
    BasePackage update(BasePackage ep) throws PackageException;

    /**
     * 删除组件包
     *
     * @param lid
     */
    void delPackage(String lid)throws PackageException;




    /**
     * 分页获取组件包
     * @param request
     * @return
     */
    Page<BasePackage> getBasePackagePagging(AbstractPageRequest request);

    /**
     * 查询组件包 通过lid
     * @param lid
     * @return
     * @throws PackageException
     */
    BasePackage getBasePackageByLid(String lid) throws PackageException;

}
