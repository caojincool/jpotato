package com.lemsun.web.manager.controller;

/**
 * User: 刘晓宝
 * Date: 13-12-26
 * Time: 上午11:06
 */
public class OfferImp  implements  IOffer{
    @Override
    public void postOffer() {
       System.err.println("postOffer");
    }

    @Override
    public void afterOffer() {
        System.err.println("afterOffer");
    }
}
