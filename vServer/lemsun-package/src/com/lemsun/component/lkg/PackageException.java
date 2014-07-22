package com.lemsun.component.lkg;

import com.lemsun.core.LemsunException;

/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-6-17
 * Time: 下午4:58
 */
public class PackageException extends LemsunException {

    public static PackageException PackageNameNull = new PackageException("组件包名不能为空!", "001", LemsunException.OtherException);
    public static PackageException PackageLidNull = new PackageException("组件包lid不能为空!", "003", LemsunException.OtherException);

    public static PackageException PackageExist = new PackageException("组件包名已经存在,不能保存", "002", LemsunException.OtherException);

    /**
     * 创建新的异常
     *
     * @param msg  异常信息
     * @param code 异常代码
     */
    public PackageException(String msg, String code, LemsunException ex) {
        super(msg, code, ex);
    }
}
