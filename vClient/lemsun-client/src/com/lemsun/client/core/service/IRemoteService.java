package com.lemsun.client.core.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lemsun.client.core.IPlateformInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 远程通信接口
 * User: 宗旭东
 * Date: 13-2-20
 * Time: 下午4:39
 */
public interface IRemoteService {




    /**
     * 获取远程服务器的返回内容
     * @param path 请求地址
     * @return 返回内容
     */
    String getAddressContext(String path);


    /**
     * 获取地区平台的信息对象
     */
    IPlateformInstance getPlateformInstance();

    /**
     * 清除连接的
     */
    void clear();

    /**
     * 获取远程服务器的对象
     * @param path 获取路径
     * @param type 类型
     * @param <T> 返回类型
     * @return 返回对象
     * @throws IOException 获取异常
     */
    <T> T getAddressContext(String path, Class<T> type);

    /**
     * 获取远程服务器的对象
     * @param path 获取路径
     * @param typeReference 类型
     * @param <T> 返回类型
     * @return 返回对象
     * @throws IOException
     */
    <T> T getAddressContext(String path, TypeReference<T> typeReference) ;

    /**
     * 获取远程服务器的数据
     * @param path 地址
     * @return 返回数据
     * @throws IOException
     */
    byte[] getAddressData(String path) ;


    /**
     * Get 方式获取远程服务器的数据流
     * @param path 地址
     * @return
     */
    InputStream getAddressStream(String path) throws IOException;

    /**
     * POST 的方式获取远程服务器的数据流
     * @param path 获取路径
     * @param params 提交参数
     * @return
     */
    InputStream getAddressStream(String path, Map<String, String> params) throws IOException;

    /**
     * 获取远程服务器的对象
     * @param path 获取路径
     * @param params 携带参数
     * @param type 数据库类型
     * @param <T> 返回类型
     * @return 返回对象
     * @throws IOException
     */
    <T> T getAddressContext(String path, Map<String, String> params, Class<T> type) ;

    /**
     * 获取远程服务器的对象
     * @param path 获取路径
     * @param params 携带参数
     * @param typeReference 类型
     * @param <T> 返回对象类型
     * @return 返回对象
     * @throws IOException
     */
    <T> T getAddressContext(String path, Map<String, String> params, TypeReference<T> typeReference) ;

    /**
     * 获取远程服务器的字符串
     * @param path 远程路径http协议
     * @param params 携带参数
     * @return 对象序列化后的字符串
     * @throws IOException
     */
    String getAddressContext(String path, Map<String, String> params)  ;



}
