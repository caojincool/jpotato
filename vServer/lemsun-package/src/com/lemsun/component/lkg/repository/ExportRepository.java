package com.lemsun.component.lkg.repository;

import com.lemsun.component.lkg.*;
import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.repository.ResourcePrimaryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * 制作导出包
 * User: dp
 * Date: 13-6-17
 * Time: 下午4:30
 */
@Repository
public class ExportRepository {

    private MongoTemplate template;
    private ResourcePrimaryRepository rpr;

    private static final String ComponentPackage = "ComponentPackage";
    private static final String VirtualDirectory = "VirtualDirectory";
    @Autowired
    public ExportRepository(MongoTemplate template, ResourcePrimaryRepository rpr) {
        this.template = template;
        this.rpr = rpr;
    }


    public BasePackage add(BasePackage info) throws PackageException {
        if (StringUtils.isEmpty(info.getName()))
            throw PackageException.PackageNameNull;
        info.setLid(rpr.generatorComponentPackage());
        template.insert(info, ComponentPackage);
        VirtualDirectory vd= new VirtualDirectory();
        vd.setPid("LID"+info.getLid());
        vd.setLid(info.getLid());
        vd.setName(info.getName());
        vd.setCreateUser(info.getCreateUser());
        template.insert(vd, VirtualDirectory);
        return info;
    }


    public BasePackage update(BasePackage info) throws PackageException {
        template.save(info);
        return info;
    }

    public void delPackage(String lid) throws PackageException{
        if (StringUtils.isEmpty(lid))
            throw PackageException.PackageLidNull;
        template.remove(query(where("lid").is(lid)),VirtualDirectoryComponent.class);
        template.remove(query(where("lid").is(lid)),VirtualDirectory.class);
        template.remove(query(where("lid").is(lid)),BasePackage.class);
    }

    public Page<BasePackage> getBasePackagePagging(AbstractPageRequest request){
        Query query = request.createQuery();

        long total = template.count(query, ComponentPackage);

        List<BasePackage> data = template.find(query, BasePackage.class, ComponentPackage);

        return new PageImpl<>(data, request, total);
    }
    public BasePackage getBasePackageByLid(String lid) throws PackageException {
        if (StringUtils.isEmpty(lid))
            throw PackageException.PackageLidNull;
         List<BasePackage>  bps=template.find(query(where("lid").in(lid)),BasePackage.class);
        if(bps.size()>0){
            return bps.get(0);
        }
        return null;
    }
}
