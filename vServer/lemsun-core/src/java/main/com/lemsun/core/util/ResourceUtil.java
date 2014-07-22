package com.lemsun.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 提供对组件的基本处理类
 * User: 宗旭东
 * Date: 13-2-24
 * Time: 上午9:35
 */
public class ResourceUtil {

    private static Pattern pidReg = Pattern.compile("^[A-Z]\\d{9}$");


    public static boolean isRecourcePid(String pid) {
        return !StringUtils.isEmpty(pid)
                && pid.length() == 10
                && pidReg.matcher(pid).matches();
    }

}
