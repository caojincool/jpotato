package com.lemsun.component.lkg.service.impl;

import com.lemsun.component.lkg.BasePackage;
import com.lemsun.component.lkg.ExportPackage;
import com.lemsun.component.lkg.PackageException;
import com.lemsun.component.lkg.VirtualDirectoryComponent;
import com.lemsun.component.lkg.repository.ExportRepository;
import com.lemsun.component.lkg.service.IExportService;
import com.lemsun.core.AbstractPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-6-17
 * Time: 下午4:51
 */
@Service
public class ExportServiceImpl implements IExportService {

    private ExportRepository repository;

    @Autowired
    public ExportServiceImpl(ExportRepository repository) {
        this.repository = repository;
    }


    @Override
    public BasePackage add(BasePackage basePackage) throws PackageException {
        //To change body of implemented methods use File | Settings | File Templates.
        return repository.add(basePackage);
    }

    @Override
    public Page<BasePackage> getBasePackagePagging(AbstractPageRequest request) {
        return repository.getBasePackagePagging(request);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BasePackage update(BasePackage ep) throws PackageException {
        return repository.update(ep);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delPackage(String lid)throws PackageException {
        repository.delPackage(lid);
    }

    @Override
    public BasePackage getBasePackageByLid(String lid) throws PackageException {
        return repository.getBasePackageByLid(lid);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
