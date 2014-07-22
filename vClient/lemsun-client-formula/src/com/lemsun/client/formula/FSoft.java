package com.lemsun.client.formula;

import com.lemsun.client.core.formula.ISoft;

/**
 * 排序模型
 * Created by xudong on 14-1-21.
 */
public class FSoft implements ISoft {

    private String name;
    private String ref;
    private int soft;


    public FSoft(String name, String ref, int soft) {
        this.name = name;
        this.ref = ref;
        this.soft = soft;
    }

    public String getName() {
        return name;
    }


    public String getRef() {
        return ref;
    }


    public int getSoft() {
        return soft;
    }


}
