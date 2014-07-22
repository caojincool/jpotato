package com.lemsun.web.model.view;

import com.lemsun.core.IAccount;
import com.lemsun.data.lmstable.Table5GroupResource;


/**
 * User: 刘晓宝
 * Date: 13-11-20
 * Time: 上午11:43
 */
public class Table5GroupItemView extends ResourceBase{
    public Table5GroupItemView(Table5GroupResource resource,IAccount account){
        super(resource,account);
    }

    public String getCode() {
        return ((Table5GroupResource)getResource()).getCode();
    }

    public int getTableCategory() {
        return ((Table5GroupResource)getResource()).getTableCategory();
    }
}
