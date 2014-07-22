package com.lemsun.core;

/**
 * User: 宗旭东
 * Date: 13-3-4
 * Time: 上午10:41
 */
public class PlateformException extends LemsunException {

    public static final PlateformException NotPlateformException = new PlateformException("不存在的平台实例, 或许是平台没有注册", "001");

    public static final PlateformException KeyException = new PlateformException("通信秘钥异常", "002");

    public static final PlateformException NotPlateformPid=new PlateformException("平台编码为空,查询条件数据库不予查询!","003");


    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     * @param code 异常代码
     */
    public PlateformException(String msg, String code) {
        super(msg, code, LemsunException.PlateformException);
    }
}
