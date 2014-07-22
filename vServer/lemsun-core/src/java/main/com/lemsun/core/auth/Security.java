package com.lemsun.core.auth;

/**
 * User: 宗旭东
 * Date: 13-6-3
 * Time: 上午11:13
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
public @interface Security {

    /**
     * 检查的节点
     * @return
     */
    String value() default "";

    /**
     * 是否忽略检查
     * @return
     */
    boolean ignore() default false;

}
