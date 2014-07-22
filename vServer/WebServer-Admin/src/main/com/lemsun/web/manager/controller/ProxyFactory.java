package com.lemsun.web.manager.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * User: 刘晓宝
 * Date: 13-12-26
 * Time: 上午11:07
 */
public class ProxyFactory  implements InvocationHandler {
    private Object delegate;
    public Object bind(Object delegate){
        this.delegate=delegate;
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("执行invoke");
        Object result=null;
        result= method.invoke(delegate,args);
        System.err.println("执行invoke结束");
        return  result;
    }
    public static void  main(String[] args){
        IOffer offer=(IOffer)new ProxyFactory().bind(new OfferImp());
        offer.afterOffer();
        offer.postOffer();
    }
}
