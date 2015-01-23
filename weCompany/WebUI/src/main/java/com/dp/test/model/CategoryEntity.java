package com.dp.test.model;

/**
 * Created by dpyang on 2014/11/16.
 */
public class CategoryEntity {
    private String id;
    private String forumId;
    private String name;
    private String nameEn;
    private String forumName;
    private String ctype;
    private boolean isEnabled;

    public CategoryEntity(String id, String forumId, String name, String nameEn, String forumName, String ctype, boolean isEnabled) {
        this.id = id;
        this.forumId = forumId;
        this.name = name;
        this.nameEn = nameEn;
        this.forumName = forumName;
        this.ctype = ctype;
        this.isEnabled = isEnabled;
    }

    public String getId() {
        return id;
    }

    public String getForumId() {
        return forumId;
    }

    public String getName() {
        return name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getForumName() {
        return forumName;
    }

    public String getCtype() {
        return ctype;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
