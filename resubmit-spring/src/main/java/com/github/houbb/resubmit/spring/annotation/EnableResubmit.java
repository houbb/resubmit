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

    /**
     * 缓存实现策略 bean 名称
     * @return 实现
     * @since 0.0.1
     */
    String cache() default "resubmitCache";

    /**
     * key 生成策略 bean 名称
     * @return 生成策略
     * @since 0.0.1
     */
    String keyGenerator() default "resubmitKeyGenerator";

    /**
     * 密匙生成策略 bean 名称
     * @return 生成策略
     * @since 0.0.1
     */
    String tokenGenerator() default "resubmitTokenGenerator";

}
