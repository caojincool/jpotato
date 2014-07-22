package com.lemsun.data.lmstable;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.LemsunResource;
import com.lemsun.data.connection.AbstractDbResource;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.ldbc.ITableGroupResource;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;

import java.util.Calendar;
import java.util.List;

/**
 * 五代表组
 * User: 宗旭东
 * Date: 13-6-5
 * Time: 上午10:05
 */
public class Table5GroupResource extends AbstractDbResource implements ITableGroupResource {

    public static final String TYPE = "tablegroup";

    @Transient
    private List<Table5Resource> tables;

    private String code;
    private String key;
    private int tableCategory;
    private int lastIndex = 1;
    private String codeFormat;

    /**
     * 创建五代表组
     * @param name 组件名称
     * @param code 组件编码
     * @param dbConfig 数据库配置
     */
    public Table5GroupResource(String name, String code, DbConfigResource dbConfig) {
        super(name, BaseCategory.DBTABEL_GROUP_5.getCategory(), dbConfig);
        this.code = code;
    }

    public Table5GroupResource(LemsunResource lr,String code,DbConfigResource dbConfig){
        super(lr.getName(),lr.getCategory(),dbConfig);
        this.setId(lr.getId());
        this.setPid(lr.getPid());
        this.code=code;
    }

    public Table5GroupResource(LemsunResource resource) {
        super(resource.getName(), resource.getCategory());
        setId(resource.getId());
        setPid(resource.getPid());
        setRemark(resource.getRemark());
        setStrParams(resource.getStrParams());
        setCreateUser(resource.getCreateUser());
        setUpdateTime(resource.getUpdateTime());
        setSystem(resource.isSystem());
        setBusinessCode(resource.getBusinessCode());
    }
    /**
     * 创建一个空白的表组对象
     */
    public Table5GroupResource(String name)
    {
        super(name, BaseCategory.DBTABEL_GROUP_5.getCategory(), null);
    }

    /**
     * 默认构造器
     */
    public Table5GroupResource()
    {
        super(null, BaseCategory.DBTABEL_GROUP_5.getCategory(), null);
    }

    /**
     * 获取当前子表格
     */
    public List<Table5Resource> getTables() {
        return tables;
    }

    /**
     * 设置子表格
     */
    public void setTables(List<Table5Resource> tables) {
        this.tables = tables;

        if(tables != null)
        {
            for(Table5Resource table : tables)
            {
                table.setParent(this);
                table.setDbConfig(getDbConfig());
                table.setCate(getTableCategory());
            }
        }

    }

    /**
     * 获取编码
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取编码创建的格式. %N 表示年份, %Y 表示月, %R 表示日
     */

    public String getCodeFormat() {
        return codeFormat;
    }
    /**
     * 设置表格物理的名称生成代码
     */
    public void setCodeFormat(String codeFormat) {

        this.codeFormat = codeFormat;
    }

    /**
     * 获取表格类型
     */
    public int getTableCategory() {
        return tableCategory;
    }

    /**
     * 设置表格类型
     */
    public void setTableCategory(int tableCategory) {
        this.tableCategory = tableCategory;
    }


    /**
     * 获取最后一次的索引
     */
    public int getLastIndex() {
        return lastIndex;
    }

    @Override
    public void setDbConfig(DbConfigResource dbConfig) {
        super.setDbConfig(dbConfig);
       // setParentPid(dbConfig.getPid());
    }


}
