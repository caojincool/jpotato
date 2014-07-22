package com.lemsun.data.viproject.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-21
 * Time: 下午4:48
 * To change this template use File | Settings | File Templates.
 */
public class FourIndexException extends FourViprojectException {
    public FourIndexException(String msg, String code, FourIndexException ex) {
        super(msg, code, ex);
    }

    public FourIndexException(String msg, String code) {
        super(msg, code,FourViprojectException.fourIndex);
    }
}
