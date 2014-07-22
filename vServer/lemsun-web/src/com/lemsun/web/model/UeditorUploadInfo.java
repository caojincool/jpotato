package com.lemsun.web.model;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * UEditor 上传文件的返回上传文件信息
 *
 * User: 宗旭东
 * Date: 13-3-29
 * Time: 上午10:19
 */
public class UeditorUploadInfo {

    private String original = "";
    private String url = "";
    private String title = "";
    private String state = "SUCCESS";

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
        if(StringUtils.isNotEmpty(original)) {
            title = original.substring(0, original.lastIndexOf("."));
        }
    }

    public String getUrl() throws UnsupportedEncodingException {
        return URLDecoder.decode(url,"UTF-8");
    }

    public void setUrl(String url) {
        try {
            this.url= URLEncoder.encode(url,"UTF-8");
        } catch (Exception ignored) {

        }
    }

    public void setOrgUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }


    public void setError(String error) {
        state = error;
    }


    public boolean isSuccess() {
        return StringUtils.equals(state, "SUCCESS");
    }
}
