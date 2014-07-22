package com.lemsun.formula;

import com.lemsun.core.formula.ISoft;
import org.apache.commons.lang3.StringUtils;

/**
 * User: 刘晓宝
 * Date: 14-3-12
 * Time: 上午9:57
 */
public class Soft implements ISoft {
    private String name;
    private int soft;
    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoft() {
        return soft;
    }

    public void setSoft(int soft) {
        this.soft = soft;
    }



    public String getRef() {
        if(StringUtils.isNotEmpty(ref)){
            return ref.toUpperCase();
        }
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
    @Override
    public String toString() {
        if(StringUtils.isNotEmpty(getRef())){
            return "["+getRef()+"."+name+"] "+soft ;
        }
        return "["+name+"] "+soft ;
    }
}
