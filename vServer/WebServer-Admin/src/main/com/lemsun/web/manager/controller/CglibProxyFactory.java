package com.lemsun.web.manager.controller;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * User: 刘晓宝
 * Date: 13-12-26
 * Time: 上午11:25
 */
public class CglibProxyFactory  implements MethodInterceptor {
    private Object delegate;
    public Object bind(Object delegate){
        this.delegate=delegate;
        Enhancer hancer=new Enhancer();
        hancer.setCallback(this);
        hancer.setSuperclass(delegate.getClass());
        return hancer.create();
    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        System.err.println("执行invoke2");
        Object result=null;
        result= proxy.invoke(this.delegate,args);
        System.err.println("执行invoke结束1");
        return result;
    }
    public static void  main(String[] args){
        IOffer offer=(IOffer)new CglibProxyFactory().bind(new OfferImp());
        offer.afterOffer();
        offer.postOffer();
    }
}
