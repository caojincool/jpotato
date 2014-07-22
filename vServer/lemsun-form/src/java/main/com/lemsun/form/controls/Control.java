package com.lemsun.form.controls;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 标注一个控件
 * User: Xudong
 * Date: 12-12-4
 * Time: 下午2:50
 */
@Scope("prototype")
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Control {

	/**
	 * 返回当前的控件名称
	 * @return 返回控件的名称
	 */
	String name();

}
