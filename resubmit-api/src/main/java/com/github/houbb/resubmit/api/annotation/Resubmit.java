package com.github.houbb.resubmit.api.annotation;

import java.lang.annotation.*;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Resubmit {

    /**
     * 存活时间毫秒
     *
     * @return 时间
     * @since 0.0.1
     */
    int value() default 60000;

}
