package com.lemsun.auth.repository;

import com.lemsun.auth.AccountManager;
import com.lemsun.auth.BaseRole;
import com.lemsun.auth.RoleException;
import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.repository.AbstractLocalRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 角色对象数据库操作层
 * User: Xudong
 * Date: 13-1-14
 * Time: 下午2:54
 */
@Repository
public class BaseRoleRepository extends AbstractLocalRepository {

    @Autowired
    public BaseRoleRepository(MongoTemplate template) {
        super(template);
    }

    /**
     * 保存一个角色对象
     * @param role 角色信息
     * @throws RoleException 可能角色已经存在
     */
    public void insert(BaseRole role) throws RoleException {

        if(role.getId() != null) {
            throw RoleException.ExistRole;
        }

        //默认当前插入时间也是当前更新时间
        Date date=new Date();
        role.setCreateTime(date);
        role.setUpdateTime(date);
        role.setCreateAccount(AccountManager.getCurrentManager().getAccount().getAccount());
        getTemplate().insert(role);
    }

    /**
     * 更新一个角色信息
     * @param role 角色信息
     */
    public void save(BaseRole role){

        role.setUpdateTime(new Date());

        getTemplate().save(role);
    }

    /**
     * 根据角色名称删除一个角色
     * @param name 角色名称
     */
    public void delete(String name) throws RoleException{
        if (StringUtils.isEmpty(name))
            throw RoleException.RoleisNull;

        getTemplate().remove(query(where("name").is(name)),BaseRole.class);
    }

    /**
     * 根据一个角色名称获取对应的角色信息
     * @param name 角色名称
     * @return 对应的角色信息
     */
    public BaseRole getRole(String name) throws RoleException{
        if (StringUtils.isEmpty(name))
            throw RoleException.RoleisNull;

        return  getTemplate().findOne(query(where("name").is(name)), BaseRole.class);
    }

    /**
     * 获取所有的角色信息
     * @return 所有的角色信息
     */
    public List<BaseRole> loadAll() {

        return getTemplate().findAll(BaseRole.class);
    }
    /**
     * 获取分页的角色数据
     *
     * @return 分页对象
     */
    public Page<BaseRole> getPageData(AbstractPageRequest pageRequest) {
        Query query = pageRequest.createQuery();
        long total = getTemplate().count(query, BaseRole.class);
        List<BaseRole> data = getTemplate().find(query, BaseRole.class);

        return new PageImpl<>(data, pageRequest, total);
    }

}
