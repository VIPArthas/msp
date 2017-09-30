package com.wh.framework;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {
	/**
	 * 动作描述
	 * @return
	 * @author 徐优优
	 * @date 2016年5月12日
	 */
	String logRemark() default "";
	/**
	 * 对应模块
	 * @return
	 * @author 徐优优
	 * @date 2016年5月12日
	 */
	String logKey() default "";
	/**
	 * 方法描述
	 * @return
	 * @author 徐优优
	 * @date 2016年5月12日
	 */
	String logTag() default "";
	/**
	 * 操作等级
	 * 忽略0  基本1  普通2  重要3  极重要4
	 * @return
	 * @author 徐优优
	 * @date 2016年5月12日
	 */
	int logType() default 0;
}
