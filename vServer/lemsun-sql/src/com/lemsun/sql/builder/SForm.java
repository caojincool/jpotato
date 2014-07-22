package com.lemsun.sql.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询目标
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午3:22
 */
public class SForm extends DefaultSQLSegment {

    private final ArrayList<ISQLFormItem> tables = new ArrayList<>();

    public SForm() {
    }

    /**
     * 获取查询表格
     */
    public List<ISQLFormItem> getTables() {
        return new ArrayList<>(tables);
    }

    /**
     * 插入一张表格查询对象
     */
    public SForm table(ISQLFormItem item) {
        addChild(item);
        tables.add(item);
        return this;
    }

    public SForm table(String name, String alias) {
        return table(new STable(name).alias(alias));
    }

    @Override
    protected boolean removeChild(ISQLSegment item) {
        if(!super.removeChild(item)) return false;
        tables.remove((ISQLFormItem)item);
        return true;
    }

    @Override
    public void validate() throws SqlBuilderException {
        if (getTables().size() == 0)
            throw new SqlBuilderException("查询语句不能没有查询对象 Form 里面的 table 为空");

        for(ISQLFormItem item : tables) {
            item.validate();
        }
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException {
        return adapter.getFormStatement(this);
    }
}
