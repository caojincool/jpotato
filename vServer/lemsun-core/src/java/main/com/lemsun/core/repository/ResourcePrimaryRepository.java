package com.lemsun.core.repository;

import com.lemsun.core.*;
import com.lemsun.core.service.INavigationService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 组件的主键数据操作层
 * User: Xudong
 * Date: 12-10-20
 * Time: 下午4:45
 */
@Repository
public final class ResourcePrimaryRepository extends AbstractLocalRepository {

    private NumberFormat format = new DecimalFormat("000000000");
    private static final Logger log = LoggerFactory.getLogger(ResourcePrimaryRepository.class);

    @Autowired
    public ResourcePrimaryRepository(MongoTemplate template) {
        super(template);
    }

    /**
     * 对给定的键值产生一个序列
     *
     * @param key 建名
     * @return 序列值
     */
    public synchronized int generatorIndex(String key) {

        int index = 0;
        MongoTemplate template = getTemplate();
        BasicDBObject row = new BasicDBObject();
        row.put("id", key);

        String collName = "config";
        String valueName = "value";

        if (!template.collectionExists(collName)) {
            DBCollection coll = template.createCollection(collName);
            row.put(valueName, index);
            coll.save(row);
        } else {
            DBCollection coll = template.getCollection(collName);
            DBObject tempRow = coll.findOne(row);
            index = (Integer) tempRow.get(valueName);
            index++;
            //BasicDBObject value = new BasicDBObject();
            //row.put(valueName, index);
            //coll.update(row, value);
            tempRow.put(valueName, index);
            coll.save(tempRow);
        }
        return index;
    }

    /**
     * 产生一个给平台的模型主键
     *
     * @param instance 平台实例
     * @return 序列
     */
    public synchronized String generatorPlateform(PlateformInstance instance) {

        int index = generatorIndex("plateformId");

        return "P" + format.format(index);
    }

    /**
     * 产生一个给账号的主键
     *
     * @param account 账号对象
     * @return 序列
     */
    public synchronized String generatorAccount(IAccount account) {
        int index = generatorIndex("accountId");
        return "A" + format.format(index);
    }

    /**
     * 产生组件ID
     */
    public synchronized String generator(IResource resource) {
        if (log.isDebugEnabled())
            log.debug("开始生成组件 : {}, {}", resource.getName(), resource.getCategory());
        int index = generatorIndex("resourceId");
        return (resource.isSystem() ? "S" : "C") + format.format(index);
    }


    public synchronized String generatorNavigate(Navigation nav) {
        if (log.isDebugEnabled())
            log.debug("开始创建导航 : {}, {}", nav.getName(), nav.getCategory());
        int index = generatorIndex("resourceId");
        return "NAV" + format.format(index);
    }

    /**
     * 产生组件类别编码
     */
    public synchronized String generatorCategory(ICategory category) {

        if (log.isDebugEnabled())
            log.debug("开始生成组件类别编码 : {}, {}", category.getName(), category.getCategory());
        int index = generatorIndex("resourceId");
        return "C" + format.format(index);
    }

    /**
     * 产生组件包ID
     */
    public synchronized String generatorComponentPackage() {
        if (log.isDebugEnabled())
            log.debug("开始生成组件包编码");
        int index = generatorIndex("componentPackageId");
        return "L" + format.format(index);
    }
    /**
     * 产生函数ID
     */
    public synchronized String generatorFunctionStatement() {
        if (log.isDebugEnabled())
            log.debug("开始生成函数编码");
        int index = generatorIndex("functionStatementId");
        return "P" + format.format(index);
    }
    /**
     * 产生任务日志Pid
     */
    public synchronized String generatorTaskLog() {
        if (log.isDebugEnabled())
            log.debug("开始生成任务日志编码");
        int index = generatorIndex("taskLogId");
        return "L" + format.format(index);
    }
}
