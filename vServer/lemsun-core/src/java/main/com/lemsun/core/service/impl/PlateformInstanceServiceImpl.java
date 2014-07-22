package com.lemsun.core.service.impl;

import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.PlateformException;
import com.lemsun.core.PlateformInstance;
import com.lemsun.core.repository.PlateFormInstanceRepository;
import com.lemsun.core.service.IPlateformInstanceService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 实现对系统平台实例的服务
 * User: gm
 * Date: 12-12-1
 * Time: 上午9:11
 */
@Service
public class PlateformInstanceServiceImpl implements IPlateformInstanceService {

	private PlateFormInstanceRepository repository;


	@Autowired
	public PlateformInstanceServiceImpl(PlateFormInstanceRepository repository)
	{
		this.repository = repository;
	}

	/**
	 * 创建实例对象
	 * @param plateformInstance 要创建的示例对象
	 */
	@Override
	public void create(PlateformInstance plateformInstance)
	{
		repository.create(plateformInstance);
	}

	/**
	 * 更新系统实例对象数据
	 * @param resource 要更新的系统实例对象
	 */
	@Override
	public void update(PlateformInstance resource) throws Exception {
		if(resource.getId() == null)
			throw new Exception("更新不能没有主键");

		repository.update(resource);
	}

	/**
	 * 根据实例pid删除单条系统实例
	 * @param pid 系统实例pid
	 */
	@Override
	public void delete(String pid) throws Exception {
		if(StringUtils.isEmpty(pid))
			throw new Exception("pid为空，无法删除!");
		repository.delete(pid);
	}

	/**
	 * 根据pid获取此系统实例对象
	 * @param pid  系统实例pid
	 * @return 满足条件的此系统实例对象
	 */
	@Override
	public PlateformInstance get(String pid)
	{
		return repository.get(pid);
	}

    @Override
    public PlateformInstance getSystemInstance() {
        //TODO 获取系统实例模型. 先从数据库获取实例对象. 如果没有就需要创建一个唯一的实例对象. 并且实例的IP, 注册等信息. 需要获取

        PlateformInstance instance = new PlateformInstance();

        instance.setId("P000000000");
        instance.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        instance.setName("服务系统");
        return instance;
    }

    /**
	 * 获取所有系统实例对象
	 * @return 所有实例对象集合
	 */
	@Override
	public List<PlateformInstance> getAllInstance()
	{
		return repository.getAllInstance();
	}

	/**
	 * 系统实例分页
	 * @param request 页面信息
	 * @return 返回组件
	 */
	@Override
	public Page<PlateformInstance> getPagging(AbstractPageRequest request)
	{
		return repository.getPagging(request);
	}

    @Override
    public String getPlateformToken(String code) {
        return "";
    }

    @Override
    public PlateformInstance getPlatefrmByToken(String token) {
        return repository.getByToken(token);
    }

    @Override
    public PlateformInstance plateformLogin(String code, String password) throws PlateformException {
        PlateformInstance instance = get(code);

        if(instance == null)
            throw PlateformException.NotPlateformException;

        String key = DigestUtils.md5Hex(instance.getTxKey());

        if(!StringUtils.equalsIgnoreCase(password, key))
        {
            throw PlateformException.KeyException;
        }

        instance.setToken(UUID.randomUUID().toString().replaceAll("-", ""));

        try {
            repository.updateLogin(instance);
        } catch (Exception e) {

        }

        return instance;
    }
}
