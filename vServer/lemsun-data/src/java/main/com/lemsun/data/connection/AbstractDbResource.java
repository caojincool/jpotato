package com.lemsun.data.connection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemsun.core.AbstractLemsunResource;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * 抽象的数据库组件对象. 只要是挂载到关系数据配置信息下的子节点. 都需要实现这个抽象类
 * User: Xudong
 * Date: 12-11-23
 * Time: 下午11:09
 */
public abstract class AbstractDbResource extends AbstractLemsunResource {

    public AbstractDbResource(String name, String category, DbConfigResource dbConfig) {
        this(name, category);
        setDbConfig(dbConfig);
    }

    protected AbstractDbResource(String name, String category) {
        super(name, category);
    }


    @DBRef()
    private DbConfigResource dbConfig;


    /**
     * 获取当前数据库资源的数据库配置
     *
     * @return
     */
    @JsonIgnore
    public DbConfigResource getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(DbConfigResource dbConfig) {
        this.dbConfig = dbConfig;
    }
}
