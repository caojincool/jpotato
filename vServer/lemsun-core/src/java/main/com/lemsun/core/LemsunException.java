package com.lemsun.core;

/**
 * 全局异常.
 * 体现里面的异常有代码组成. 每个系统或者子系统都有一级代码.
 * User: Xudong
 * Date: 13-1-17
 * Time: 下午3:12
 */
public class LemsunException extends RuntimeException {

    private String code;
    private LemsunException parent = null;

    /**
     * 权限异常
     */
    public static final LemsunException AuthException = new LemsunException("权限异常", "001", null);

    /**
     * 组件异常
     */
    public static final LemsunException ResourceException = new LemsunException("组件异常", "002", null);

    /**
     * 通道通信异常
     */
    public static final LemsunException ConnectionExcepiton = new LemsunException("通道通信异常", "003", null);

    /**
     * 4代库导常
     */
    public static final LemsunException FourVipojectException=new LemsunException("4代库导常","004",null);


    /**
     * 执行数据库相关异常
     */
    public static final LemsunException DbConnectionException = new LemsunException("数据库连接异常", "005", null);

    /**
     * 分配给数据库执行的时候, 的执行异常
     */
    public static final LemsunException DbProcessException = new LemsunException("数据库执行异常", "015", null);


    public static final LemsunException PlateformException = new LemsunException("用户安装平台出现异常", "006", null);


    public static final LemsunException FormulaException = new LemsunException("公式解析执行异常", "007", null);

    /**
     * 其他异常
     */
    public static final LemsunException OtherException = new LemsunException("其他异常", "009", null);

    /**
     * 创建新的异常
     * @param msg 异常信息
     * @param code 异常代码
     */
    public LemsunException(String msg, String code, LemsunException ex) {
        super(msg, ex);
        this.code = code;
        this.parent = ex;
    }


    public LemsunException(Exception ex) {
        super(ex);
    }

    public LemsunException(String msg) {
        super(msg);
    }

    /**
     * 获取异常代码
     */
    public String getCode() {
        return parent == null ?  code : (parent.getCode() + "." + code);
    }


    @Override
    public String getMessage() {
        return "(" + getCode() + ")" + super.getMessage();
    }


    /**
     * 获取父级异常模块
     */
    public LemsunException getParent() {
        return parent;
    }

}
