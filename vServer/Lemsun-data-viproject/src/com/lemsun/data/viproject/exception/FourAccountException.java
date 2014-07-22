package com.lemsun.data.viproject.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-21
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
public class FourAccountException extends FourViprojectException {

    public FourAccountException(String msg, String code) {
        super(msg, code, FourViprojectException.fourAcount);
    }

    public FourAccountException (String msg, String code,FourAccountException ex) {
        super(msg,code,ex);
    }
}
