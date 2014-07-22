package com.lemsun.core.repository;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.ICategory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-10-20
 * Time: 下午4:40
 */
@Repository
public class BaseCategoryRepository extends AbstractLocalFsRepository {

	private ResourcePrimaryRepository primaryRepository;

	@Autowired
	public BaseCategoryRepository(ResourcePrimaryRepository primaryRepository, MongoTemplate template, GridFsOperations gridFsOperations) {
		super(template,gridFsOperations);
		this.primaryRepository = primaryRepository;
	}

	/**
	 * @return 返回根目录
	 **/
	public BaseCategory getRootCategory()
	{
        BaseCategory baseRootCategory= getTemplate().findOne(new Query(where("category").in(BaseCategory.ROOT.getCategory())), BaseCategory.class);
        loadChild(baseRootCategory);
        return baseRootCategory;
	}

    /**
     * 返回根下的下级类型
     * @return 组件类型信息集合
     */
    public List<BaseCategory> getRootsonCategory()
    {
        BaseCategory baseRootCategory= getTemplate().findOne(new Query(where("category").in(BaseCategory.ROOT.getCategory())), BaseCategory.class);
        if(baseRootCategory==null)return new ArrayList<>();
        return getChildCategory(baseRootCategory.getId());
    }

    public List<BaseCategory> getCategoryByResourceCategory(String resourcCategory)
    {
        BaseCategory baseRootCategory= getTemplate().findOne(new Query(where("category").is(resourcCategory)), BaseCategory.class);
        if(baseRootCategory==null)return new ArrayList<>();
        return getChildCategory(baseRootCategory.getId());
    }

	/**
	 * 获取类型子集
	 * @param parentId 父类型主键
	 * @return 子集
	 */
	public List<BaseCategory> getChildCategory(ObjectId parentId)
	{
		return getTemplate().find(query(where("parent.$id").is(parentId)), BaseCategory.class);
	}

    public void loadChild(BaseCategory baseCategory)
    {
        if(baseCategory==null)return;
        List<BaseCategory> child= getChildCategory(baseCategory.getId());
        if(child==null||child.isEmpty())return;

        for (BaseCategory b:child)
        {
            baseCategory.addChild(b);
            loadChild(b);
        }
    }

	/**
	 * 创建一个类型对象
	 * @param category 类型
	 */
	public void createCategory(ICategory category) throws Exception {

        BaseCategory baseCategory= getTemplate().findOne(query(where("category").is(category.getCategory())),BaseCategory.class);
        if(baseCategory!=null)
            throw new Exception("该类型已经存在");
		category.setPid(primaryRepository.generatorCategory(category));
        category.setCreate(true);
		category.setUpdateTime(new Date());
		//category.setCreateUser(Account.SYSTEM.getAccount());

		getTemplate().save(category);

		List<ICategory> child = category.getChild();
		if(child != null)
			for(ICategory c : child) {
				createCategory(c);
			}
	}

    /**
     * 修改组件类型信息
     * @param category 组件类型信息
     */
    public  void  updateBaseCategory(BaseCategory category)
    {
        BaseCategory baseCategory= getBaseCategoryByPid(category.getPid());
        if(baseCategory==null)return;
        getTemplate().save(category);
    }

    /**
     * 根据pid获取BaseCategory
     * @param pid 组件类型pid
     * @return 返回此组件类型
     */
    public BaseCategory getBaseCategoryByPid(String pid)
    {
        return getTemplate().findOne(query(where("pid").is(pid)),BaseCategory.class);
    }

    /**
     * 根据类型获取BaseCategory
     * @param category 类型
     * @return 返回此类型信息
     */
    public BaseCategory getBaseCategoryByCategory(String category)
    {
        return getTemplate().findOne(query(where("category").is(category)),BaseCategory.class);
    }

	/**
	 * 删除组件类型集合
	 * @param categorys 组件类型集合
	 */
    public void removeBaseCategoryByCategorys(List<String> categorys)
    {
        getTemplate().remove(query(where("category").nin(categorys)),BaseCategory.class);
    }

    /**
     * 删除组件类型信息
     * @param pid 组件类型pid
     */
    public void deleteBaseCategoryByPid(String pid) throws Exception {
        BaseCategory baseCategory=getBaseCategoryByPid(pid);
        if(pid.startsWith("SYS"))
            throw new Exception("SYS级不能删除");
        if(baseCategory!=null){
            getTemplate().remove(query(where("pid").is(pid)),BaseCategory.class);
            deletBaseCategoryChild(baseCategory.getId());
        }
    }

	/**
	 * 删除组件类型子节点信息
	 * @param parentId 组件类型父id
	 */
    private void deletBaseCategoryChild(ObjectId parentId)
    {
        List<BaseCategory> child = getTemplate().find(query(where("parent.$id").is(parentId)), BaseCategory.class);
        getTemplate().remove(query(where("parent.$id").is(parentId)), BaseCategory.class);
        for (BaseCategory c:child)
        {
            deletBaseCategoryChild(c.getId());
        }
    }

	public boolean isChanged()
	{
		//TODO 执行数据库配置查询. 或者临时查看当前配置
		return true;
	}

	/**
	 * 返回可创建的类型集合
	 * @return 返回可创建的类型集合
	 */
	public List<BaseCategory> getBaseCategoryIsCreate(){
		return getTemplate().find(query(where("create").is(true)), BaseCategory.class);
	}

}