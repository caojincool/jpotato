package com.lemsun.auth.repository;

import com.lemsun.auth.*;
import com.lemsun.core.AbstractPageRequest;
import com.lemsun.core.IAccount;
import com.lemsun.core.repository.AbstractLocalRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

/**
 * User: ssy
 * Date: 12-11-29
 * Time: 下午4:43
 * 账号管理数据库操作
 */
@Repository
public class AccountRepository extends AbstractLocalRepository {

    private final static Logger log = LoggerFactory.getLogger(AccountRepository.class);
    private NumberFormat format = new DecimalFormat("00000000");
    private GridFsOperations gridFsOperations;

    @Autowired
    public AccountRepository(MongoTemplate template, GridFsOperations gridFsOperations) {
        super(template);
        this.gridFsOperations = gridFsOperations;
    }

    /**
     * 新增数据
     */
    public void save(BaseAccount account) throws AccountException {

        if (!StringUtils.isEmpty(account.getId()) || getAccountByAccount(account.getAccount())!=null) {
            throw AccountException.ExistAccount;
        }

        account.setId(createNewId(account));

        if (log.isDebugEnabled())
            log.debug("调用保存方法{}", account.getId());

        getTemplate().insert(account);
    }

    /**
     * 更新一个账户的权限
     *
     * @param account 账户
     * @param pk 权限信息
     */
    public void updateAccountPermission(IAccount account, Set<PermissionKey> pk) {

        getTemplate().remove(query(where("account").is(account.getId())),"Account_Permission");

        for(PermissionKey k : pk) {
            HashMap<String, Object> row = new HashMap<>();
            row.put("account", account.getId());
            row.put("pk", k);
            getTemplate().insert(row, "Account_Permission");
        }
    }

    /**
     * 插入账户权限
     * @param account 账户信息
     * @param pk 权限集合
     */
    public void insertAccountPermissions(IAccount account,Set<PermissionKey> pk){
        for(PermissionKey k : pk) {
            HashMap<String, Object> row = new HashMap<>();
            row.put("account", account.getId());
            row.put("pk", k);
            getTemplate().insert(row, "Account_Permission");
        }
    }
    /**
     * 删除一个账户编码权限
     * @param accountId 一个账户编码
     */
    public void deleteAccountPermissionByAccountId(String accountId) throws AccountException {
        if (StringUtils.isEmpty(accountId))
            throw AccountException.UnAccount;

        getTemplate().remove(query(where("account").is(accountId)));
    }

    /**
     * 获取一个账户的权限信息
     *
     * @param account 账户
     * @return 权限信息
     */
    public List<PermissionKey> getAccountPermssion(IAccount account) {
        final List<PermissionKey> ks = new ArrayList<>();
        final MongoConverter converter = getTemplate().getConverter();

        getTemplate().executeQuery(query(where("account").is(account.getId())), "Account_Permssion", new DocumentCallbackHandler() {

            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {

                DBObject per = (DBObject) dbObject.get("pk");

                try {
                    PermissionKey k = converter.read(PermissionKey.class, per);
                    ks.add(k);
                } catch (Exception e) {
                    if(log.isErrorEnabled())
                    log.error("组件类型不能实例:{}, {}", per, e);
                }
            }
        });

        return ks;
    }


        /**
         * 更新一个账户信息
         */
    public void updateAccount(BaseAccount account) throws AccountException {
        if(StringUtils.isEmpty(account.getId())) throw AccountException.UnAccount;

        getTemplate().save(account);
    }

    /**
     * 更改密码
     * @param account 账户名称
     * @param newPassword 账户密码
     */
    public void changePassword(IAccount account,String newPassword) throws AccountException {
        if (getAccountByAccount(account.getAccount())==null) throw AccountException.UnAccount;

        getTemplate().updateFirst(query(where("account").is(account.getAccount())),
                update("password",newPassword),BaseAccount.class);
    }

    /**
     * 更新用户的头像
     * @param account 账号
     * @param data 头像数据
     */
    public void updateAvatarData(IAccount account, byte[] data) {
        updateAvatarData(account, new ByteArrayInputStream(data));
    }

    /**
     * 更新用户的头像
     * @param account 账号
     * @param stream 头像流
     */
    public void updateAvatarData(IAccount account, InputStream stream) {
        gridFsOperations.store(stream, getAvatarName(account));
    }

    /**
     * 保存默认头像
     * @param defaultAvatarName 默认头像名称
     * @param stream 默认头像流
     */
    public void updateAvatarDate(String defaultAvatarName,InputStream stream){
        gridFsOperations.store(stream,getAvatarName(defaultAvatarName));
    }

    /**
     * 删除账户头像
     * @param account 账户编码
     */
    public void deleteAvatarDateByAccountId(String account){
        //gridFsOperations.
    }

    /**
     * 根据账户编码删除账户信息
     * @return
     * */
    public void deleteAccountById(String id){
         getTemplate().remove(getTemplate().findOne(query(where("id").is(id)), BaseAccount.class));
    }

    /**
     * 创建一个新的人员主键
     *
     * @param account 账号对象
     * @return 主键
     */
    private String createNewId(IAccount account) {

        if (log.isDebugEnabled())
            log.debug("开始生成账号ID : {}, {}", account.getAccount(), account.getShowName());

        MongoTemplate template = getTemplate();

        int index = 0;

        BasicDBObject row = new BasicDBObject();
        row.put("id", "accountId");

        String collName = "config";
        String valueName = "value";

        //检查有没有config集合
        if (!template.collectionExists(collName)) {
            DBCollection coll = template.createCollection(collName);//创建集合
            row.put(valueName, index);
            coll.save(row);
        } else {
            DBCollection coll = template.getCollection(collName);
            DBObject tempRow = coll.findOne(row);
            if(log.isDebugEnabled())
            log.debug("值 : {}",tempRow.get(valueName));

            index = (Integer) tempRow.get(valueName);
            index++;
            tempRow.put(valueName, index);
            coll.save(tempRow);
        }

        return (account.isAdministrator() ? "D" : "A") + format.format(index);
    }

    /**
     * 通过账号名称获取账号对象
     */
    public BaseAccount getAccountByAccount(String account) throws AccountException {
        if(StringUtils.isEmpty(account)) throw AccountException.UnAccount;

        return getTemplate().findOne(query(where("account").is(account)), BaseAccount.class);
    }

    /**
     * 通过账户编码获取一个账户对象
     */
    public BaseAccount getAccountByAccountId(String accountId) throws AccountException {
        if(StringUtils.isEmpty(accountId)) throw AccountException.UnAccount;

            return getTemplate().findOne(query(where("id").is(accountId)),BaseAccount.class);
    }

    /**
     * 获取分页的账号数据
     *
     * @return 分页对象
     */
    public Page<BaseAccount> getPageData(AbstractPageRequest pageRequest) {
        Query query = pageRequest.createQuery();

        long total = getTemplate().count(query, BaseAccount.class);

        List<BaseAccount> data = getTemplate().find(query, BaseAccount.class);

        return new PageImpl<>(data, pageRequest, total);
    }


    /**
     * 当一个账号完成登录后, 更新或者记录一下用户的登录信息
     *
     * @param account 账号对象
     */
    public void updateLoginInfo(BaseAccount account) {
        getTemplate().updateFirst(query(where("pid").is(account.getId())),
                update("lastLoginTime",
                        account.getLastLoginTime()).addToSet("loginIp",
                        account.getLoginIp()).addToSet("loginCount",
                        account.getLoginCount()), BaseAccount.class);
    }

    /**
     * 获取账号头像信息
     * @param account 账号
     * @return 头像信息流
     */
    public InputStream getAvatarData(String account) {
        GridFSDBFile file = gridFsOperations.findOne(query(whereFilename().is(getAvatarName(account))));
        if(file == null) {
            file = gridFsOperations.findOne(query(whereFilename().is("avatar.default.ico")));
        }

        if(file != null) {
            return file.getInputStream();
        }
        throw new RuntimeException("没有默认头像文件(avatar.default.ico). 请管理员更新数据库里面的头像信息.");
    }

    /**
     * 根据账户获取头像名称
     */
    private String getAvatarName(IAccount account) {
        Assert.notNull(account);
        Assert.notNull(account.getId());
        return getAvatarName(account.getId());
    }

    /**
     * 根据账户名称获取头像名称
     */
    public String getAvatarName(String account) {
        return "avatar." + account + ".ico";
    }

    /**
     * 更新账户扩展信息
     */
    public void updateAccountExpand(ExpandAccount expandAccount) throws AccountException {

        //判断账户扩展信息id是否存在
       // if (expandAccount.getId()==null)
         //   throw  AccountException.UnAccountExpand;

        getTemplate().save(expandAccount);
    }

    /**
     * 插入账户扩展信息
     */
    public void insertAccountExpand(ExpandAccount expandAccount) throws AccountException {
        if (expandAccount.getId()==null)
           throw AccountException.AccountExpandByGuidRepeat;

        getTemplate().insert(expandAccount);
    }

    /**
     * 获取账户扩展信息集合
     * @param accountId 账户
     * @return 某个账户的扩展信息集合
     */
    public ExpandAccount getAccountExpand(String accountId) throws AccountException {

        if (StringUtils.isEmpty(accountId)){
            throw AccountException.UnAccount;
        }
        return  getTemplate().findOne(query(where("accountId").is(accountId)),ExpandAccount.class,"Account_Expand");
    }

    /**
     * 根据账户名称删除一个账户扩展信息
     * @param account
     */
    public void deleteAccountExpandByAccount(String account) throws AccountException {
        if (StringUtils.isEmpty(account)){
            throw AccountException.UnAccount;
        }

        getTemplate().remove(query(where("accountId").is(account)), ExpandAccount.class);
    }
    /**
     * 重置密码
     *
     * @param account 账号对象
     */
    public void resetPassword(BaseAccount account) {
        getTemplate().updateFirst(query(where("id").is(account.getId())),
                update("password", account.getPassword()),BaseAccount.class);
    }

    /**
     * 通过账号获取账号下挂角色
     * @param accountId
     * @return
     * @throws AccountException
     */
    public  List<BaseAccountRole> queryAccountByAccountId(String accountId) throws AccountException{
        if (StringUtils.isEmpty(accountId)){
            throw AccountException.UnAccount;
        }
       return  getTemplate().find(query(where("accountId").is(accountId)),BaseAccountRole.class);
    }
    public List<BaseRole> queryAccountByRoleIds(List<BaseAccountRole> accountRoles){
       List<String> roleNames= new ArrayList<String>();
        for(BaseAccountRole accountRole : accountRoles){
            roleNames.add(accountRole.getRoleId());
        }
        return  getTemplate().find(query(where("name").in(roleNames)),BaseRole.class);
    }

    public List<BaseAccount> getAccountByShowName(String showName){

        return  getTemplate().find(query(where("showName").is(showName)),BaseAccount.class);
    }
}