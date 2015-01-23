package com.dp.baobao.domain;

import org.apache.ibatis.type.Alias;

/**
 * 公司实体对象
 * Created by dpyang on 2015/1/23.
 */
@Alias("Company")
public class Company {
    private String id;
    private String code;
    private String name;
    private String shortName;
    private String remark;
    private String keyWord;
    private String url;
    private String phone;
    private String selPhone;
    private String servicePhone;
    private String address;
    private String postCode;
    private String email;
    private String nameEn;
    private String shortNameEn;
    private String remarkEn;
    private String addressEn;
    private String keyWordEn;

    public Company(){

    }

    public Company(String id, String code, String name, String shortName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.shortName = shortName;
    }

    public Company(String id, String code, String name, String shortName,String nameEn,String shortNameEn){
        this(id,code,name,shortName);
        this.nameEn=nameEn;
        this.shortNameEn=shortNameEn;
    }

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSelPhone() {
        return selPhone;
    }

    public void setSelPhone(String selPhone) {
        this.selPhone = selPhone;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postcode) {
        this.postCode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getShortNameEn() {
        return shortNameEn;
    }

    public void setShortNameEn(String shortNameEn) {
        this.shortNameEn = shortNameEn;
    }

    public String getRemarkEn() {
        return remarkEn;
    }

    public void setRemarkEn(String remarkEn) {
        this.remarkEn = remarkEn;
    }

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    public String getKeyWordEn() {
        return keyWordEn;
    }

    public void setKeyWordEn(String keyWordEn) {
        this.keyWordEn = keyWordEn;
    }
}
