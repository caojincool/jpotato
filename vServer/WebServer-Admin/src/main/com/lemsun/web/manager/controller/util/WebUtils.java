package com.lemsun.web.manager.controller.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * User: 刘晓宝
 * Date: 13-12-5
 * Time: 下午2:24
 */
public class WebUtils {
    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static  String getSuffix(String fileName){
        int fIndex = fileName.lastIndexOf(".");
        String suffix = fileName.substring(fIndex);
        return suffix;
    }

    /**
     * 获取web项目绝对根路径
     * @param request
     * @return
     */
    public static String getWebAppPath(HttpServletRequest request){
        String webAppPath = request.getServletContext().getRealPath("/");
        return webAppPath;
    }

    /**
     * 验收目录是否存在 ，不存在创建， 支持多级创建
     * @param userWebAppPath
     */
    public static void checkImageDir(String userWebAppPath) {
        File file = new File(userWebAppPath);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    /**
     * 获取HttpSession
     * @param request
     * @return
     */
    public static String getSessionId(HttpServletRequest request) {
        return request.getSession().getId();
    }
}
