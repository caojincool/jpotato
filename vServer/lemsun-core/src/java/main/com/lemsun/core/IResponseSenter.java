package com.lemsun.core;

/**
 * 发送回复信息接口
 * User: 宗旭东
 * Date: 13-2-27
 * Time: 下午4:44
 */
public interface IResponseSenter {

    void sent(IResponseCommand command) throws Exception;

}
