package com.lemsun.helper;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

/**
 * User: 刘晓宝
 * Date: 13-12-3
 * Time: 下午4:22
 */
public class VelocityUtils {
    private static Properties props = new Properties();
    private static VelocityEngine engine = new VelocityEngine(props);
    static {

        ClassPathResource resource = new ClassPathResource("com/lemsun/helper/config.properties");

        try {
            props.load(resource.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("没有找到数据库映射的配置文件 com/lemsun/helper/config.properties");
        }
    }
   public static <T> String evaluate(String template,T resource){
       // 取得velocity的上下文context
       VelocityContext context = new VelocityContext();
       // 把数据填入上下文
       context.put("resource", resource);
       StringWriter writer = new StringWriter();
       engine.evaluate(context, writer, "", template);
       writer.flush();
       return writer.toString();
   }
}
