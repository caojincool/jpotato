package com.lemsun.websocket;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

/**
 * 通道配置
 * User: xudong
 * Date: 13-11-29
 * Time: 下午2:24
 */
public class ChannelConfig implements ServerApplicationConfig {
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {

        Set<ServerEndpointConfig> result = new HashSet<>();

        return result;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> classes) {

        Set<Class<?>> result = new HashSet<>();

        for(Class<?> c : classes) {
            if(c.getPackage().getName().startsWith("com.lemsun.websocket"))
            {
                result.add(c);
            }
        }


        return result;

    }
}
