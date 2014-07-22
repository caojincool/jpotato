package com.lemsun.web.model.view;

import com.lemsun.core.IAccount;
import com.lemsun.core.IResource;
import com.lemsun.form.WpfPageResource;


/**
 * User: 刘晓宝
 * Date: 13-11-20
 * Time: 上午11:43
 */
public class WpfskinItemView extends ResourceBase{

    private boolean showToolbar;
    private boolean synchronismWindowSize;
    private int openMode;
    private int showLocation;
    private WpfPageResource wpfPageResource=(WpfPageResource)getResource();
    public WpfskinItemView(IResource resource, IAccount account) {
        super(resource, account);
    }

    public boolean isShowToolbar() {
        return wpfPageResource.isShowToolbar();
    }

    public boolean isSynchronismWindowSize() {
        return wpfPageResource.isSynchronismWindowSize();
    }

    public int getOpenMode() {
        return wpfPageResource.getOpenMode();
    }

    public int getShowLocation() {
        return wpfPageResource.getShowLocation();
    }

}
