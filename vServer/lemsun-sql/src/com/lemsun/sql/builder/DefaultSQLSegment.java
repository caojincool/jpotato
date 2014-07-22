package com.lemsun.sql.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 默认的数据类型转换器
 * User: 宗旭东
 * Date: 14-2-28
 * Time: 下午2:46
 */
public class DefaultSQLSegment implements ISQLSegment {


    private HashSet<ISQLSegment> child;
    private ISQLSegment parent;
    private ISQLStatement statement;


    public DefaultSQLSegment(ISQLSegment parent)
    {
        this();
        setParent(this);
    }

    public DefaultSQLSegment()
    {
        Init();
    }

    private void Init()
    {

    }
    @Override
    public ISQLStatement getStatement()
    {
        return statement == null && getParent() != null ? getParent().getStatement() : statement;
    }

    protected void setStatement(ISQLStatement statement)
    {
        this.statement = statement;
    }

    @Override
    public ISQLSegment getParent()
    {
        return parent;
    }

    @Override
    public void setParent(ISQLSegment parent)
    {
        this.parent = parent;
        statement = null;
    }
    @Override
    public List<ISQLSegment> getChild()
    {
        return child == null ? null : new ArrayList<>(child);
    }

    @Override
    public List<ISQLSegment> getAllSegments()
    {
        ArrayList<ISQLSegment> segments = new ArrayList<>();
        if (child == null) return segments;
        for(ISQLSegment s : child)
        {
            segments.add(s);
            segments.addAll(s.getAllSegments());
        }
        return segments;
    }

    /// <summary>
    /// 如果当前的片段, 在一个语句的上下文中. 那么将当前的片段在上下文中移除
    /// </summary>
    public void remove()
    {
        ISQLSegment p = getParent();
        if (p != null && p instanceof DefaultSQLSegment)
        {
            ((DefaultSQLSegment)p).removeChild(this);
            parent = null;
        }
        statement = null;
    }

    /**
     * 从当前片段中移除给定的节点, 考虑到节点可以自己删除. 父节点需要重写这个方法. 同步设置成 null
     */
    protected boolean removeChild(ISQLSegment item)
    {
        if (child == null || !child.contains(item))
        {
            return false;
        }
        item.setParent(null);
        item.remove();
        return true;
    }

    protected void addChild(ISQLSegment item)
    {
        item.remove();
        item.setParent(this);
        if (child == null)
        {
            child = new HashSet<>();
        }

        child.add(item);
    }

    @Override
    public void validate() throws SqlBuilderException {
    }

    @Override
    public String toSQL(ISQLAdapter adapter) throws SqlBuilderException
    {
        return "";
    }
    @Override
    public String toSQL() throws SqlBuilderException
    {
        return toSQL(DefaultSQLAdapter.getInstance());
    }


    @Override
    public String toString()
    {
        try {
            return toSQL();
        } catch (SqlBuilderException e) {
            e.printStackTrace();
        }

        return "ERROR";
    }

}
