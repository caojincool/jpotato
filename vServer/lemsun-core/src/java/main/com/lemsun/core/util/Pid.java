package com.lemsun.core.util;

import java.lang.annotation.*;

/**
 * 对传入的Pid 进行检查, 如果不是传入的标准编码, 将进行业务编码转换. 并转换成对应的编码
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Pid {
}
