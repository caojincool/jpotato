package com.lemsun.core.service.impl;

import com.lemsun.core.Plateform;
import com.lemsun.core.PlateformException;
import com.lemsun.core.repository.PlateFormRepository;
import com.lemsun.core.service.IPlateformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现对系统平台的服务
 * User: Xudong
 * Date: 12-10-25
 * Time: 下午1:33
 */
@Service
public class PlateformServiceImpl implements IPlateformService {

	private PlateFormRepository repository;


	@Autowired
	public PlateformServiceImpl(PlateFormRepository repository)
	{
		this.repository = repository;
	}

	/**
	 * 创建系统平台
	 * @param plateform 系统平台对象
	 */
	@Override
	public void create(Plateform plateform) throws PlateformException {

        repository.create(plateform);
	}

	/**
	 * 更新组件资源
	 * @param resource 组件
	 * @throws Exception 更新异常
	 */
	@Override
	public void update(Plateform resource) throws Exception {
		if(resource.getId() == null)
			throw new Exception("更新不能没有主键");

		repository.update(resource);
	}

	/**
	 * 通过系统平台pid获取此系统平台对象
	 * @param pid 系统平台pid
	 * @return 系统平台对象
	 */
	@Override
    @Deprecated
	public Plateform get(String pid) throws PlateformException {
		return repository.get(pid);
	}

	/**
	 * 使用类型名称获取平台对象
	 * @param category 类型名称
	 * @return 平台对象
	 */
	@Override
	public Plateform getByCategory(String category) {
		return repository.getByCategory(category.toUpperCase());
	}

	/**
	 * 获取所有类型数据集合
	 * @return 系统类型集合
	 */
	@Override
	public List<Plateform> getAllCategory()
	{
		return repository.getAllCategory();
	}
}
