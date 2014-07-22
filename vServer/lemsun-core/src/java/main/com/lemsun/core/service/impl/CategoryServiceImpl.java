package com.lemsun.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemsun.core.BaseCategory;
import com.lemsun.core.ICategory;
import com.lemsun.core.repository.BaseCategoryRepository;
import com.lemsun.core.service.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-23
 * Time: 上午9:52
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    private BaseCategoryRepository categoryRepository;
    private Hashtable<String, ICategory> categorys = new Hashtable<>();

	/**
	 * 返回可创建的组件类型
	 */
	private Hashtable<String, ICategory> categorysIsCreate = new Hashtable<>();

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private ICategory root;


    @Autowired
    public CategoryServiceImpl(BaseCategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
        init();
    }


    /**
     * 初始化
     */
    public void init() {
        initBaseCategory(BaseCategory.ROOT);
        categoryRepository.removeBaseCategoryByCategorys(baseCategorys);
        categorys.clear();
    }

    private List<String> baseCategorys=new ArrayList<>();

    /**
     * 初始化根节点
     * @param category
     */
    private void initBaseCategory(BaseCategory category)
    {
        BaseCategory cate = categoryRepository.getBaseCategoryByCategory(category.getCategory());

        if(cate == null) {
            try {
                categoryRepository.createCategory(category);
                cate = categoryRepository.getBaseCategoryByCategory(category.getCategory());
            } catch (Exception e) {

            }
        }

        category.setPid(cate.getPid());
        category.setId(cate.getId());

        baseCategorys.add(category.getCategory());

        List<ICategory> categories= category.getChild();
        if(categories!=null){
            for(int i=0;i<categories.size();i++)
            {
                BaseCategory bc= (BaseCategory)categories.get(i);
                bc.setParent(category);
                initBaseCategory(bc) ;
            }
        }
    }

    @Override
    public ICategory getRoot() {
        if(root == null || categoryRepository.isChanged())
        {
            root = BaseCategory.ROOT;
            categorys.clear();
            loadCategory(root);
        }
        return root;
    }


    @Override
    public ICategory getByName(String category)
    {
        if(categorys.containsKey(category)) {
            return categorys.get(category);
        }
        return null;
    }

    @Override
    public BaseCategory getByPid(String pid) {
        return categoryRepository.getBaseCategoryByPid(pid);
    }

    @Override
    public boolean contains(String category) {
        return categorys.containsKey(category);
    }

    @Override
    public Collection<ICategory> getAll()
    {
        if(categorys.isEmpty()){
            getRoot();
        }
        return categorys.values();
    }

    @Override
    public Collection<ICategory> getBaseCategoryIsCreate(){
        List<BaseCategory> result = categoryRepository.getBaseCategoryIsCreate();
        for (BaseCategory aResult : result) {
            categorysIsCreate.put(aResult.getCategory(), aResult);
        }
        return categorysIsCreate.values();
    }

    @Override
    public void edit(BaseCategory category) {
        categoryRepository.updateBaseCategory(category);
    }

    private void loadCategory(ICategory category) {
        if(category == null) return;
        if(!categorys.containsKey(category.getCategory()))
        {
            categorys.put(category.getCategory(), category);
        }
        List<ICategory> child = category.getChild();
        if(child != null) {
            for(ICategory c : child) {
                loadCategory(c);
            }
        }
    }

    @Override
    public String toString() {

        ObjectMapper mapper = new ObjectMapper();

        String json;

        try {
            json = mapper.writeValueAsString(getRoot());
        } catch (IOException e) {
            json = super.toString();
        }
        return json;
    }

}
