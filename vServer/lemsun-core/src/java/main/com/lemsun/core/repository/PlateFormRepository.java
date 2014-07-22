package com.lemsun.core.repository;

import com.lemsun.core.Plateform;
import com.lemsun.core.PlateformException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 系统平台数据库操作层
 * User: Xudong
 * Date: 12-10-25
 * Time: 下午1:14
 * 修改说明:因为平台操作层只是对平台数据的操作,与组件的关系是
 * 平台包含组件,一个组件属于一个平台.不是一个平台就是一个组件
 * 所以没有必要继承组件的数据库抽象类
 * 修改人:dpyng
 */
@Repository
public class PlateFormRepository extends AbstractLocalRepository {

	public static final String PlateformName = "Plateform";

	@Autowired
	public PlateFormRepository(MongoTemplate template) {
		super(template);
	}

    /**
     * 创建一个平台
     */
    public void create(Plateform plateform) throws PlateformException {
        if (plateform.getId()!=null)
            throw PlateformException.NotPlateformException;

        getTemplate().insert(plateform);
    }

    /**
     * 根据平台平台编码获取平台信息
     */
    public  Plateform get(String pid) throws PlateformException {
       if (StringUtils.isEmpty(pid))
           throw PlateformException.NotPlateformPid;

       return getTemplate().findOne(query(where("pid").is(pid)),Plateform.class);
    }

	/**
	 * 更新组件资源
	 * @param resource 组件
	 * @throws Exception 更新异常
	 */
	public void update(Plateform resource) throws Exception {
		if(resource.getId() == null)
			throw new Exception("更新不能没有主键");

		getTemplate().save(resource, PlateformName);
	}

	/**
	 * 通过类型获取系统平台对象
	 * @param category 系统类型
	 * @return 系统平台对象
	 */
	public Plateform getByCategory(String category)
	{
		return getTemplate().findOne(query(where("category").is(category)), Plateform.class);
	}

	/**
	 * 获取所有类型数据集合
	 * @return 系统类型集合
	 */
	public List<Plateform> getAllCategory()
	{
		return getTemplate().findAll(Plateform.class);
	}

}
