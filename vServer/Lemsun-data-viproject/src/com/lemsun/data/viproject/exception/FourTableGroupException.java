package com.lemsun.data.viproject.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-21
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */
public class FourTableGroupException extends FourViprojectException {
    public FourTableGroupException(String msg, String code, FourTableGroupException ex) {
        super(msg, code, ex);
    }

    public FourTableGroupException(String msg, String code) {
        super(msg, code,FourViprojectException.fourTableGroup);
    }
}
