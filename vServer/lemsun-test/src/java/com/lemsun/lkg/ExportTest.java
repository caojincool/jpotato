package com.lemsun.lkg;

import com.lemsun.TestSupport;
import com.lemsun.component.lkg.BaseComponent;
import com.lemsun.component.lkg.BasePackage;
import com.lemsun.component.lkg.ExportPackage;
import com.lemsun.component.lkg.PackageException;
import com.lemsun.component.lkg.service.IExportService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-6-17
 * Time: 下午5:24
 */
public class ExportTest extends TestSupport {

    @Autowired
    IExportService exportService;

    @Test
    public void testSave() {
        ExportPackage info = new ExportPackage();
        info.setName("阳煤住房公积金组件包A");

        BaseComponent bc = new BaseComponent();
        bc.setCategory("WEBSKIN");
        bc.setName("组件A");
        bc.setPid("C000000021");
        BaseComponent bc1 = new BaseComponent();
        bc1.setCategory("WEBSKIN");
        bc1.setName("组件A");
        bc1.setPid("C000000021");

        List<BaseComponent> baseComponents = new ArrayList<>();
        baseComponents.add(bc);
        baseComponents.add(bc1);
        info.setComponentList(baseComponents);

        info.setLicensing("许可号");
        info.setTarget("焦煤住房公积金");
        info.setCreateTime(new Date());
        info.setCreateUser("ydp");
        info.setExportScript("导出脚本");
        info.setImportScript("导入脚本");
        info.setRemark("hello");
        info.setVersion("1.0");
        info.setStartFace("123.html");

        try {
            exportService.add(info);
            getLog().debug("插入成功!");
        } catch (PackageException e) {
            getLog().error(e.getMessage());
        }
    }


}
