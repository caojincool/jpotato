package com.dp.baobao.domain;

import org.apache.ibatis.type.Alias;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by dpyang on 2014/10/7.
 */
@Alias("Article")
public class Article {
    private String id;
    private String name;
    private String nameEn;
    private String content;
    private String contentEn;
    private String keyWord;
    private String keyWordEn;
    private String description;
    private String descriptionEn;
    private boolean isEnabled;
    private boolean isFirst;
    private int viewCount;
    private Date createDate;
    private Date updateDate;
    private Category category;
    private String sellUrl;        //购买链接
    private String images;        //主图,以|作为分割
    private String code;     //自定义编码


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSellUrl() {
        return sellUrl;
    }

    public void setSellUrl(String sellUrl) {
        this.sellUrl = sellUrl;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
