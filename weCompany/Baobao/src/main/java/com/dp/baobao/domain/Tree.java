package com.dp.baobao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by dpyang on 2014/11/22.
 */
public class Tree {
    private String id;
    private String code;
    private String name;
    private Tree parent;
    private List<Tree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }

    @JsonIgnore
    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        if (children!=null)
            for (Tree t:children) t.setParent(this);

        this.children = children;
    }
}
