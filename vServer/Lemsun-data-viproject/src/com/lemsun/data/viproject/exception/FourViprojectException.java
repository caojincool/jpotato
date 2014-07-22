package com.lemsun.data.viproject.exception;

import com.lemsun.core.LemsunException;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-21
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class FourViprojectException extends LemsunException {

    /**
     * 4代帐套错误
     */
    public static final  FourViprojectException fourAcount=new FourViprojectException("4代帐套错误","001");

    /**
     * 4代目录错误
     */
    public static final FourViprojectException fourIndex=new FourViprojectException("4代目录错误","002");

    /**
     * 4代表组错误
     */
    public static final FourViprojectException fourTableGroup=new FourViprojectException("4代表组错误","003");

    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     * @param code 异常代码
     */
    public FourViprojectException(String msg, String code, FourViprojectException ex) {
        super(msg, code, ex);
    }

    public FourViprojectException(String msg,String code){
        super(msg,code,LemsunException.FourVipojectException);
    }
}
