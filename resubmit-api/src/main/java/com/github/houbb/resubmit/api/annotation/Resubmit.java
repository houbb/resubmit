package com.github.houbb.resubmit.api.annotation;

import java.lang.annotation.*;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface Resubmit {

    /**
     * 存活时间毫秒
     *
     * @return 时间
     * @since 0.0.1
     */
    int value() default 8000;

    /**
     * 是否启用
     * @return 是否
     * @since 1.2.0
     */
    boolean enable() default true;

}
