package com.lemsun.core;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 一个标准的统一组件模型. 在模型处理前期. 为了减少对数据库的访问压力. 可以只获取这个模型. 然后进行具体的操作.
 * User: Xudong
 * Date: 12-10-25
 * Time: 下午3:48
 */
@Document(collection = "ResourceComponent")
public class LemsunResource extends AbstractLemsunResource implements ILoadChild<IResource> {
    public static final String TYPE = "resource";

    /**
     * 说明:这里增加默认构造函数
     * 因为前台传入json对象用jsonmapper直接隐射
     */
    public LemsunResource(){

    }

    @PersistenceConstructor
    public LemsunResource(String name, String category) {
        super(name, category);
    }

    @Transient
    private List<IResource> child;

    @Override
    public List<IResource> getChild() {
        return child;
    }

    @Override
    public void setChild(List<IResource> child) {
        this.child = child;
    }

    @Override
    public void loadChild(Collection collection) {
        Assert.notNull(collection);
        List<IResource> temp = new ArrayList<>(collection.size());
        for (Object c : collection)
            if (c instanceof IResource)
                temp.add((IResource) c);
        setChild(temp);
    }
}
