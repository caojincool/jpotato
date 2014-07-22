package com.lemsun.core.service;

import com.lemsun.core.Plateform;
import com.lemsun.core.PlateformException;

import java.util.List;

/**
 * 平台服务接口
 * User: Xudong
 * Date: 12-10-25
 * Time: 下午1:32
 */
public interface IPlateformService {

	/**
	 * 创建系统平台
	 * @param plateform 系统平台对象
	 */
	void create(Plateform plateform) throws PlateformException;

	/**
	 * 更新系统类型对象数据
	 * @param resource 类型对象
	 */
	void update(Plateform resource) throws Exception;

	/**
	 * 通过系统平台pid获取此系统平台对象
	 * @param pid 系统平台pid
	 * @return 系统平台对象
	 */
    @Deprecated
	Plateform get(String pid) throws PlateformException;

	/**
	 * 使用类型名称获取平台对象
	 * @param category 类型名称
	 * @return 平台对象
	 */
	Plateform getByCategory(String category);

	/**
	 * 获取所有类型数据集合
	 * @return 系统类型集合
	 */
	List<Plateform> getAllCategory();
}
