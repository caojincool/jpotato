package com.lemsun.web.manager.controller.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 属性文件读取和修改工具类
 * User: 刘晓宝
 * Date: 13-9-16
 * Time: 上午9:19
 */
public class PropsUtils {
    public static Logger  logger = LoggerFactory.getLogger(PropsUtils.class);
    public static final String filePath=(new File(PropsUtils.class.getResource("/").getPath())).getParent()+"/config.properties";

    //根据key读取value
    public static String readValue(String key) {
        Properties props = new Properties();
        try {
            InputStream in = new FileInputStream(filePath);
            props.load(in);
            String value = props.getProperty (key);

            if(logger.isInfoEnabled())
            logger.info("从" + filePath + "中读取" + key + "=" + value);
            return value;
        } catch (Exception e) {
            if(logger.isErrorEnabled())
            logger.error("读取"+key+"的属性值出错！",e);
            e.printStackTrace();
            return null;
        }
    }

    //读取properties的全部信息
    public static void readProperties() {
        Properties props = new Properties();
        try {
            //InputStream in = PropsUtils.class.getResourceAsStream(filePath);
             InputStream in = new BufferedInputStream (new FileInputStream(filePath));
            props.load(in);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String Property = props.getProperty (key);
                System.out.println(key+Property);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //写入properties信息
    public static void writeProperties(String[] keys,String[] values) {
        Properties prop = new Properties();
        try {
            logger.info("开始设置"+filePath+"中文件中读取"+keys+"="+values);
            InputStream fis = new FileInputStream(filePath);
            prop.load(fis);
            OutputStream fos = new FileOutputStream(filePath);
            int i=0;
            prop.clear();
            for(String key:keys){
                System.err.print("开始设置" + key + "' =" + values[i]);
                prop.setProperty(key, values[i]);

                i++;
            }
            prop.store(fos, "Update time'" +new Date());
            if(logger.isInfoEnabled())
            logger.info("结束设置"+filePath+"中文件中读取"+keys+"="+values);
        } catch (IOException e) {
            if(logger.isErrorEnabled())
            logger.error("设置"+keys+"的属性值出错！",e);
             e.printStackTrace();
        }
    }


}
