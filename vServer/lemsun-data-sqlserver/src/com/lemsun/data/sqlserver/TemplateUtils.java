package com.lemsun.data.sqlserver;

import org.apache.commons.lang3.StringUtils;

/**
 * 模板里面的辅助方法
 * User: zongxudong
 * Date: 13-8-1
 * Time: 下午2:40
 * To change this template use File | Settings | File Templates.
 */
public class TemplateUtils {

    public boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    public boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    public boolean isNull(Object obj) {
        return obj == null;
    }

    public boolean isNotNull(Object obj) {
        return  obj != null;
    }

}
