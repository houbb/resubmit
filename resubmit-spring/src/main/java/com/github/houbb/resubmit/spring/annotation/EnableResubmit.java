package com.github.houbb.resubmit.spring.annotation;

import com.github.houbb.resubmit.spring.config.ResubmitAopConfig;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用注解
 * @author binbin.hou
 * @since 0.0.2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ResubmitAopConfig.class)
@EnableAspectJAutoProxy
public @interface EnableResubmit {
}
