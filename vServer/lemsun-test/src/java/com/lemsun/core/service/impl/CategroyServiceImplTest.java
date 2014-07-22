package com.lemsun.core.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.BaseCategory;
import com.lemsun.core.repository.BaseCategoryRepository;
import com.lemsun.core.service.ICategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-22
 * Time: 下午3:00
 */
public class CategroyServiceImplTest extends TestSupport {


    @Autowired
    private ICategoryService service;

    @Autowired
    private BaseCategoryRepository repository;


    @Test
    public void testCreate() throws Exception {
        repository.createCategory(BaseCategory.REPORTER_SCRIPT);
    }

    @Test
    public void testUpdate() {
        BaseCategory bc = repository.getBaseCategoryByCategory("DBTABEL_4");
        bc.setCreate(false);
        repository.updateBaseCategory(bc);
    }

    /**
     * 测试初始化类型是否可创建方法
     */
    @Test
    public void testEdit() {
        ArrayList<String> list = new ArrayList<>(8);
        list.add(BaseCategory.DB.getCategory());
        list.add(BaseCategory.IMAGE.getCategory());
        list.add(BaseCategory.RESOURCE.getCategory());
        list.add(BaseCategory.SCRIPT.getCategory());
        list.add(BaseCategory.WEB_SCRIPT.getCategory());
        list.add(BaseCategory.WEB_SKIN.getCategory());
        list.add(BaseCategory.WPF_SCRIPT.getCategory());
        list.add(BaseCategory.WPF_SKIN.getCategory());
        for (String aList : list) {
            BaseCategory bc = repository.getBaseCategoryByCategory(aList);
            bc.setCreate(true);
            repository.updateBaseCategory(bc);
        }

        ArrayList<String> listone = new ArrayList<>(4);
        list.add(BaseCategory.DBTABEL_GROUP_4.getCategory());
        list.add(BaseCategory.DBTABEL_GROUP_5.getCategory());
        listone.add(BaseCategory.DBTABEL_4.getCategory());
        listone.add(BaseCategory.ROOT.getCategory());
        for (String aList1 : listone) {
            BaseCategory bc = repository.getBaseCategoryByCategory(aList1);
            bc.setCreate(false);
            repository.updateBaseCategory(bc);
        }
    }
}
