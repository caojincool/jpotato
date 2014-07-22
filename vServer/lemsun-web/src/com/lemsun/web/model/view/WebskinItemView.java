package com.lemsun.web.model.view;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResource;
import com.lemsun.form.WebPageResource;

/**
 * User: 刘晓宝
 * Date: 13-11-20
 * Time: 上午11:43
 */
public class WebskinItemView extends ResourceBase{
    private WebPageResource webPageResource=(WebPageResource)getResource();
    public WebskinItemView(IResource resource, IAccount account) {
        super(resource, account);
    }
    public String getContextType() {
        return webPageResource.getContextType();
    }

    public boolean isCache() {
        return webPageResource.isCache();
    }

    public int getCacheTime() {
        return webPageResource.getCacheTime();
    }

    public boolean isPage() {
        return webPageResource.isPage();
    }
}
