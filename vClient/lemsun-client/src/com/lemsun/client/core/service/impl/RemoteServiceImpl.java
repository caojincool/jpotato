package com.lemsun.client.core.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lemsun.client.core.Host;
import com.lemsun.client.core.IPlateformInstance;
import com.lemsun.client.core.IResponseEntity;
import com.lemsun.client.core.LemsunException;
import com.lemsun.client.core.jackson.JsonObjectMapper;
import com.lemsun.client.core.model.PlateformInstance;
import com.lemsun.client.core.service.IRemoteService;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 远程服务器通信接口实现
 * User: 宗旭东
 * Date: 13-2-20
 * Time: 下午4:40
 */
@Service
public class RemoteServiceImpl implements IRemoteService {

    private HttpClient client;
    private String baseAddress;
    private static Logger log = LoggerFactory.getLogger(RemoteServiceImpl.class);
    private JsonObjectMapper objectMapper;
    private Host host;

    @Autowired
    public RemoteServiceImpl(@Qualifier("host") Host host,
                             JsonObjectMapper objectMapper) {
        client = new SystemDefaultHttpClient();
        this.objectMapper = objectMapper;
        this.host = host;

        baseAddress = host.getHttpAddress() + "interface/" + host.getCode() + "/";
    }

    /**
     * 初始化远程使用的测试
     */
    @PostConstruct
    public void init() throws IOException {
        host.setPlateform(getPlateformInstance());
        baseAddress = host.getHttpAddress() + "interface/" + host.getPlateform().getToken() + "/";
        if(log.isDebugEnabled())
            log.debug("完成初始化 , URI:{}", baseAddress);
    }

    /**
     * 获取请求地址
     * @param address 请求地址
     * @return
     */
    protected String getUrl(String address) {

        return baseAddress + address;
    }

    /**
     * 获取远程服务器的对象
     * @param path 获取路径
     * @param type 类型
     * @param <T> 返回类型
     * @return 返回对象
     * @throws java.io.IOException 获取异常
     */
    @Override
    public <T> T getAddressContext(String path, Class<T> type) {
        String context = getAddressContext(path);
        try {
            return objectMapper.readValue(context, type);
        } catch (IOException e) {
            throw new LemsunException("数据类型转换异常", e);
        }
    }


    @Override
    public <T> T getAddressContext(String path, Map<String, String> params, Class<T> type) {
        String context = getAddressContext(path, params);
        try {
            return objectMapper.readValue(context, type);
        } catch (IOException e) {
            throw new LemsunException("数据类型转换异常", e);
        }
    }

    /**
     * 获取远程服务器的对象
     * @param path 获取路径
     * @param typeReference 类型
     * @param <T> 返回类型
     * @return 返回对象
     * @throws java.io.IOException
     */
    @Override
    public <T> T getAddressContext(String path, TypeReference<T> typeReference) {
        String context = getAddressContext(path);

        if(StringUtils.isEmpty(context)) return null;

        try {
            return objectMapper.readValue(context, typeReference);
        } catch (IOException e) {
            throw new LemsunException("数据类型转换异常", e);
        }
    }


    @Override
    public <T> T getAddressContext(String path, Map<String, String> params, TypeReference<T> typeReference) {
        String context = getAddressContext(path, params);

        if(StringUtils.isEmpty(context)) return null;

        try {
            return objectMapper.readValue(context, typeReference);
        } catch (IOException e) {
            throw new LemsunException("数据类型转换异常", e);
        }
    }


    /**
     * 获取远程服务器的返回内容
     * @param path 请求地址
     * @return 返回内容
     */
    @Override
    public String getAddressContext(String path) {


        HttpEntity entity = doRequest(path);

        String context = null;
        try {
            context = EntityUtils.toString(entity, Charsets.UTF_8);
        } catch (IOException e) {

        }

        if(log.isDebugEnabled())
            log.debug("获取了返回内容, 长度 :{}", entity.getContentLength());

        return context;
    }


    @Override
    public String getAddressContext(String path, Map<String, String> params) {
        HttpEntity entity = doPostRequest(path, params);

        String context = null;
        try {
            context = EntityUtils.toString(entity, Charsets.UTF_8);
        } catch (IOException e) {

        }

        if(log.isDebugEnabled())
            log.debug("获取了返回内容, 长度 :{}", entity.getContentLength());

        return context;
    }


    /**
     * 获取远程服务器的数据
     * @param path 地址
     * @return 返回数据
     * @throws java.io.IOException
     */
    @Override
    public byte[] getAddressData(String path) {

        HttpEntity entity = doRequest(path);

        try {

            return EntityUtils.toByteArray(entity);
        } catch (IOException e) {
            throw new LemsunException("数据转换异常");
        }
    }

    @Override
    public InputStream getAddressStream(String path) throws IOException {
        HttpEntity entity = doRequest(path);
        return entity.getContent();

    }

    @Override
    public InputStream getAddressStream(String path, Map<String, String> params) throws IOException {

        HttpEntity entity = doPostRequest(path, params);
        return entity.getContent();
    }

    /**
     *
     * @param path
     * @return
     * @throws java.io.IOException
     */
    private HttpEntity doRequest(String path) {

        String url = getUrl(path);

        HttpGet httpget = new HttpGet(url);

        if(log.isDebugEnabled())
            log.debug("开始一个服务器 GET 请求 url:{}", httpget.getURI());

        HttpResponse response = null;
        try {
            response = client.execute(httpget);
        } catch (IOException e) {
            throw new LemsunException("服务器请求异常", e);
        }

        HttpEntity entity = response.getEntity();
        if(entity == null) {
            throw new LemsunException("服务器没有返回内容");
        }
        if(log.isDebugEnabled())
            log.debug("获取了返回内容, 长度 :{}", entity.getContentLength());

        return entity;
    }

    /**
     *
     * @param path
     * @param params
     * @return
     * @throws IOException
     */
    private HttpEntity doPostRequest(String path, Map<String, String> params) {
        String url = getUrl(path);

        HttpPost httpPost=new HttpPost(url);

        List<NameValuePair> naps = new ArrayList<>();
        for(String key:params.keySet()) {
            naps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(naps,"utf-8"));
            if(log.isDebugEnabled())
                log.debug("开始一个服务器 POST 请求 url:{}", httpPost.getURI());

            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if(entity == null) {
                throw new IOException("服务器没有返回内容");
            }


            if(log.isDebugEnabled())
                log.debug("获取了返回内容, 长度 :{}", entity.getContentLength());

            return entity;
        } catch (Exception e) {
            throw new LemsunException("服务器执行异常", e);
        }

    }

    /**
     * 获取地区平台的信息对象
     */
    @Override
    public IPlateformInstance getPlateformInstance() {

        if(host.getPlateform() != null) {
            return host.getPlateform();
        }

        String context = getAddressContext(host.getKey());

        IResponseEntity<PlateformInstance> re = null;
        try {
            re = objectMapper.readValue(context, new TypeReference<IResponseEntity<IPlateformInstance>>() {});

            if(!re.isSuccess()) {
                throw new IOException(re.getMessage());
            }

            return re.getEntity();
        } catch (IOException e) {
            throw new LemsunException("获取平台信息异常", e);
        }

    }

    @Override
    @PreDestroy
    public void clear() {
        HttpClientUtils.closeQuietly(client);
    }
}
