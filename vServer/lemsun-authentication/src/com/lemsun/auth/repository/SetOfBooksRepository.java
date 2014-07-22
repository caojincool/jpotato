package com.lemsun.auth.repository;

import com.lemsun.core.*;
import com.lemsun.core.repository.AbstractLocalRepository;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 帐套管理
 * User: dp
 * Date: 13-7-24
 * Time: 上午9:16
 */
@Repository
public class SetOfBooksRepository extends AbstractLocalRepository {

    private static final String SetOfBooksAccountCollationName = "SetOfBooksAccount";
    private static final String SetOfBooksResourceCollationName = "SetOfBooksResource";

    @Autowired
    public SetOfBooksRepository(MongoTemplate template) {
        super(template);
    }

    /**
     * 新增一个帐套信息
     * @param setOfBooks 帐套信息
     * @throws Exception
     */
    public void add(SetOfBooks setOfBooks) throws Exception {
        if (getTemplate().findOne(query(where("sid").is(setOfBooks.getSid())), SetOfBooks.class) != null) {
            throw new Exception("组件编码已经存在!");
        }
        getTemplate().insert(setOfBooks);
    }

    /**
     * 保存一个帐帐套信息
     *
     * @param setOfBooks
     */
    public void save(SetOfBooks setOfBooks) throws Exception {

        if (StringUtils.isEmpty(setOfBooks.getName()))
            throw new Exception("帐套名称不能为空!");


        if(setOfBooks.getId()==null)
            throw new Exception("帐套在数据库中不存在,不能更新保存!");

        getTemplate().save(setOfBooks);
    }


    /**
     * 获取所有帐套
     *
     * @return
     */
    public List<SetOfBooks> load() {
        return getTemplate().findAll(SetOfBooks.class);
    }

    /**
     * 返回一个帐套信息
     *
     * @param id 帐套OID,
     * @return 帐套信息,如果不存在返回NULL
     */
    public SetOfBooks getSetOfBooks(ObjectId id) {
        return getTemplate().findOne(query(where("_id").is(id)), SetOfBooks.class);
    }

    /**
     * 移除一个帐套下的所有套帐组件和帐套账户
     *
     * @param id 帐套编码
     */
    public void remove(ObjectId id) {

        if (id==null) return;
        SetOfBooks sob=getSetOfBooks(id);
        removeSofResourceBySid(sob.getSid());               //移除对应组件
        removeSofAccountBySid(sob.getSid());                //移除对应账户
        getTemplate().remove(query(where("_id").is(id)), SetOfBooks.class);
    }

    /**
     * 增加一个帐套组件
     *
     * @param sobr 帐套组件
     */
    public void addSofResource(SetOfBooksResource sobr) throws Exception {

        if (StringUtils.isEmpty(sobr.getSid()))
            throw new Exception("缺少帐套编码,不能保存");

        if(StringUtils.isEmpty(sobr.getResourcePid()))
            throw new Exception("缺少组件编码,不能保存");

        if (StringUtils.isEmpty(sobr.getOperator()))
            sobr.setOperator(Host.getInstance().getCurrentAccount().getAccount());
        if (sobr.getHitchTime()==null)
            sobr.setHitchTime(new Date());

        if (getTemplate().findOne(query(where("sid").is(sobr.getSid())
                .and("resourcePid").is(sobr.getResourcePid())), SetOfBooksResource.class) != null)
            throw new Exception("该帐套下已经存在" + sobr.getResourcePid());

        getTemplate().save(sobr);
    }

    /**
     * 根据组件编码获取对应的帐套集合
     * @param resource 组件
     * @return 帐套集合信息
     * @throws Exception 如果组件为空抛出异常
     */
    public List<SetOfBooks> getSetOfBooksByResource(IResource resource) throws Exception {
        if (resource == null)
            throw new Exception("组件对象为空,不能获取帐套组件集合");


        final List<String> sids=new ArrayList<>();

        getTemplate().executeQuery(query(where("resourcePid").is(resource.getPid())),
                SetOfBooksResourceCollationName,new DocumentCallbackHandler() {
            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                sids.add((String) dbObject.get("sid"));
            }
        });

        return getTemplate().find(query(where("sid").in(sids)),SetOfBooks.class);
    }

    /**
     * 根据帐套编码移除对应的组件
     *
     * @param sid
     */
    public void removeSofResourceBySid(String sid) {
        getTemplate().remove(query(where("sid").is(sid)),
                SetOfBooksResource.class);
    }

    /**
     * 根据组件编码移除对应的组件
     *
     * @param resource 组件
     */
    public void removeSofResourceByResource(IResource resource) {
        if (StringUtils.isEmpty(resource.getPid()))
            return;

        getTemplate().remove(query(where("resourcePid").is(resource.getPid())),
                SetOfBooksResource.class);
    }

    /**
     * 移除一个帐套组件
     *
     * @param sobr 帐套组件
     */
    public void removeSofResource(SetOfBooksResource sobr) {


        getTemplate().remove(query(where("sid").is(sobr.getSid())
                .and("resourcePid").is(sobr.getResourcePid())),
                SetOfBooksResource.class);
    }



    /**
     * 添加账户
     *
     * @param soba
     */
    public void addSofAccount(SetOfBooksAccount soba) throws Exception {

        if (StringUtils.isEmpty(soba.getSid()))
            throw  new Exception("缺少帐套编码,不能保存");

        if (StringUtils.isEmpty(soba.getAccount()))
            throw  new Exception("缺少账户名称,不能保存");

        if (getTemplate().findOne(query(where("sid")
                .is(soba.getSid())
                .and("account").is(soba.getAccount())),
                SetOfBooksAccount.class) != null)
           throw new Exception("该帐套下已经存在" + soba.getAccount() + "账户");

        if (getTemplate().findOne(query(where("sid")
                .is(soba.getSid())),
                SetOfBooks.class) == null)
            throw new Exception("帐套编码不存");

        getTemplate().save(soba);
    }

    /**
     * 移除账户
     *
     * @param soba
     */
    public void removeSofAccount(SetOfBooksAccount soba) {

        getTemplate().remove(query(where("sid")
                .is(soba.getSid())
                .and("account").is(soba.getAccount())), SetOfBooksAccount.class);
    }

    /**
     * 根据帐套编码移除账户
     *
     * @param sid
     */
    public void removeSofAccountBySid(String sid) {
        getTemplate().remove(query(where("sid").is(sid)), SetOfBooksAccount.class);
    }

    /**
     * 根据账户移除账户
     *
     * @param account
     */
    public void removeSofAccountByAccount(IAccount account) {

        getTemplate().remove(query(where("account").is(account.getAccount())),
                SetOfBooksAccount.class);
    }



    /**
     * 返回某个账户下对应的帐套
     *
     * @param account
     * @return
     */
    public List<SetOfBooks> getSetOfBooksByAccount(IAccount account) {
        if (account==null) return null;

        final List<String> sids=new ArrayList<>();
        getTemplate().executeQuery(query(where("account").is(account.getAccount())),
                SetOfBooksAccountCollationName,new DocumentCallbackHandler() {
            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                sids.add((String) dbObject.get("sid"));
            }
        });
        return getTemplate().find(query(where("sid").in(sids)),SetOfBooks.class);
    }

    /**
     * 分页获取某个帐套下的组件列表
     *
     * @param request
     * @return
     */
    public Page<SetOfBooksResource> getSetOfBooksResourceBySid(IPageQuery request, String sid) {
        Query query = request.createQuery();
        query.addCriteria(new Criteria("sid").is(sid));

        long total = getTemplate().count(query, SetOfBooksResource.class);


        List<SetOfBooksResource> resources = getTemplate().find(query, SetOfBooksResource.class,SetOfBooksResourceCollationName);

        return new PageImpl<>(resources, request, total);
    }

    /**
     * 获取某个帐套下的所有组件编码集合
     *
     * @param sid 帐套编码
     * @return
     */
    public List<String> getSobResourcePids(String sid) {
        Query query=new Query();
        query.addCriteria(Criteria.where("sid").is(sid));
        query.fields().include("resourcePid");

        final List<String> data=new ArrayList<>();
        getTemplate().executeQuery(query,SetOfBooksResourceCollationName,new DocumentCallbackHandler() {
            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                data.add((String) dbObject.get("resourcePid"));
            }
        });
        return data;
    }

    /**
     * 获取某个帐套下的所有账户名称
     *
     * @param sid 帐套编码
     * @return 某个帐套下的所有账户名称
     */
    public List<String> getSobAccounts(String sid) {
        Query query=new Query();
        query.addCriteria(Criteria.where("sid").is(sid));
        query.fields().include("account");

        final List<String> data=new ArrayList<>();
        getTemplate().executeQuery(query,SetOfBooksAccountCollationName,new DocumentCallbackHandler() {
            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                data.add((String) dbObject.get("account"));
            }
        });
        return data;
    }
    /**
     * 分页获取某个帐套下的所有账户列�
     *
     * @param request
     * @return
     */
    public Page<SetOfBooksAccount> getSetOfBooksAccountBySid(IPageQuery request, String sid) {

        Query query = request.createQuery();
        query.addCriteria(new Criteria("sid").is(sid));

        long total = getTemplate().count(query, SetOfBooksAccountCollationName);


        List<SetOfBooksAccount> list = getTemplate().find(query, SetOfBooksAccount.class,SetOfBooksAccountCollationName);

        return new PageImpl<>(list, request, total);
    }
}
