package com.lemsun.data.tables;

import com.lemsun.core.BaseCategory;
import com.lemsun.data.connection.AbstractDbResource;
import com.lemsun.data.connection.DbConfigResource;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

/**
 * 表资源集合
 * User: Xudong
 * Date: 12-11-23
 * Time: 下午1:08
 */
public class TableGroupResource extends AbstractDbResource {

    public static final String TYPE = "tablegroup";

    /**
     * 五代版本
     */
    public static final int V = 5;
    /**
     * 四代版本
     */
    public static final int VI = 4;

    /**
     * 创建四代表
     *
     * @param name     表名称
     * @param code     编码
     * @param resource 表组件
     */
    public TableGroupResource(String name, String code, DbConfigResource resource) {
        super(name, BaseCategory.DBTABEL_GROUP_4.getCategory(), resource);
        this.code = code;
    }

    /**
     * 创建四代或五代表组件
     *
     * @param name     表名称
     * @param code     编码
     * @param version  版本号 四代用V表示, 五代用VI表示
     * @param resource 表组件
     */
    public TableGroupResource(String name, String code, int version, DbConfigResource resource) {
        super(name, code, resource);
        setVersion(version);
        if (version == V) {
            setCategory(BaseCategory.DBTABEL_GROUP_5.getCategory());
        } else if (version == VI) {
            setCategory(BaseCategory.DBTABEL_GROUP_4.getCategory());
        }
    }


    @PersistenceConstructor
    protected TableGroupResource(String name, String category) {
        super(name, category);
    }

    private String code;

    /**
     * @return 编码
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private int version;

    @Transient
    private List<TableResource> tables;
    private String key;
    private int tableCategory;
    private String nameFormat;

    /**
     * 表格的类型
     */
    public int getTableCategory() {
        return tableCategory;
    }

    /**
     * 表格的类型
     */
    public void setTableCategory(int tableCategory) {
        this.tableCategory = tableCategory;
    }

    /**
     * 表格集合主键
     */
    public String getKey() {
        return key;
    }


    /**
     * 表格集合主键
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * @return 返回表集合
     */
    public List<TableResource> getTables() {
        return tables;
    }


    /**
     * @param tables 表集合
     */
    public void setTables(List<TableResource> tables) {
        this.tables = tables;
    }

    /**
     * 获取名称格式化的格式
     */
    public String getNameFormat() {
        return nameFormat;
    }

    /**
     * 设置名称格式
     *
     * @param nameFormat 名称格式
     */
    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }

    /**
     * @return 返回版本号
     */
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    private Date updateTime;

    /**
     * 更新时间
     *
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
