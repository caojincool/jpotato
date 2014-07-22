package com.lemsun.client.core;

import com.lemsun.client.core.jackson.JsonObjectMapper;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.service.IResourceService;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

/**
 * 使用的工具静态类
 * User: 宗旭东
 * Date: 13-3-18
 * Time: 上午11:11
 */
public final class Utils {

    private static Pattern pattern = java.util.regex.Pattern.compile("[s|S|c|C]\\d{9}");
    private static NumberFormat format = new DecimalFormat("000000000");

    /**
     * 判断组件编码是否合法
     * @param pid id 组件编码
     * @return true 表示 ID 格式跟组件差不多
     */
    public static boolean isResourceId(String pid) {

        if(StringUtils.isEmpty(pid) ||
                pid.length() > 10 ||
                pid.length() < 2 ) {
            return false;
        }

        return pattern.matcher(pid).matches();
    }

    /**
     * 补全组件编码
     * @param name 组件末尾编码
     *             如:23
     * @param isSystem 是否系统组件
     *             如:true 系统组件 false 自定义组件
     * @return 补全的组件编码
     * 如:C000000023
     */
    public static String parsePid(String name, boolean isSystem) {

        if(isResourceId(name)) {
            return name.toUpperCase();
        }
        else {
            try{
                int index =  Math.abs(Integer.parseInt(name));

                return (isSystem ? "S" : "C") + format.format(index);
            }catch (Exception ex){
                return "#";
            }
        }
    }

    /**
     * 根据组件编码返回简单的组件对象
     * 注意:组件编码如果是末尾编码如:123,会先检查自定义组件返回,
     * 如果自定义组件没有,就查找系统组件.如果都没有null
     * @param pid 组件编码
     * @return 组件简单对象
     */
    public static LemsunResource getLemsunResource(String pid) {
        IResourceService serivce = Host.getApplicationContext().getBean(IResourceService.class);
        LemsunResource resource = serivce.getLemsunResource(pid);

        if(resource == null) {
            return serivce.getLemsunResource(pid);
        }
        else {
            return resource;
        }
    }

    /**
     * 根据组件简单对象返回该组件的链接地址
     * 包括wpf组件,web组件,表格组件 的不同地址.
     * 如果空组件返回 # 号
     * @param resource 简单组件对象
     * @return 组件对应的链接字符串如:
     * web组件->C000000001
     * wpf组件->ilemsun://command/resource=C000001062&token=&sfdsd=slskdj&action=open
     */
    public static String getHref(LemsunResource resource) {

        if(resource == null) return "#";

        String cate = resource.getCategory();

        //这里是临时返回打开wpf组件的内容
        if(StringUtils.equalsIgnoreCase(cate, "WEBSKIN")) {
            return resource.getPid();
        }else if(StringUtils.equalsIgnoreCase(cate,"WPFSKIN")){
            return "ilemsun://command/resource="+resource.getPid()+"&token=&action=open";
        }else if(StringUtils.equalsIgnoreCase(cate,"DBTABEL")){
            return "ilemsun://command/resource="+resource.getPid()+"&token=&action=open";
        }else{
            return "#";
        }
    }

    /**
     * 根据类名@Qualifier("webPageResourceService")返回对象
     * @param className  @Qualifier("webPageResourceService")
     * @return 容器里面的对象
     */
    public static Object getBean(String className) {
        return Host.getApplicationContext().getBean(className);
    }

    /**
     * 根据类路径返回该对象
     * @param cls 服务接口的绝对路径
     * @return 服务接口对象
     */
    public static Object getBeanByType(Class cls) {
        return Host.getApplicationContext().getBean(cls);
    }


    public static String toJson(Object obj) {
        try {
            return Host.getApplicationContext().getBean(JsonObjectMapper.class).writeValueAsString(obj);
        } catch (IOException e) {
            return "{error:true}";
        }
    }


}
