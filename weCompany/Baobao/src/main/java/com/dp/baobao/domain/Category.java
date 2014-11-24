package com.dp.baobao.domain;

import java.util.UUID;

/**
 * Created by dpyang on 2014/10/7.
 */
public class Category {
    private UUID id;
    private int orderId;
    private String name;
    private String nameEn;
    private String content;
    private String contentEn;
    private String keyWord;
    private String keyWordEn;
    private String description;
    private String descriptionEn;
    private boolean isEnabled;
    private CategoryType categoryType;
    private UUID forumId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWordEn() {
        return keyWordEn;
    }

    public void setKeyWordEn(String keyWordEn) {
        this.keyWordEn = keyWordEn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public UUID getForumId() {
        return forumId;
    }

    public void setForumId(UUID forumId) {
        this.forumId = forumId;
    }

    public enum CategoryType {
        ARTICLE,
        ARTICLELIST,
        PRODUCTLIST
    }
}

