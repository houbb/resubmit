package com.github.houbb.resubmit.api.annotation;

import com.github.houbb.resubmit.api.support.ICache;
import com.github.houbb.resubmit.api.support.IKeyGenerator;
import com.github.houbb.resubmit.api.support.ITokenGenerator;

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
     * 缓存实现策略
     * @return 实现
     * @since 0.0.1
     */
    Class<? extends ICache> cache() default ICache.class;

    /**
     * key 生成策略
     * @return 生成策略
     * @since 0.0.1
     */
    Class<? extends IKeyGenerator> keyGenerator() default IKeyGenerator.class;

    /**
     * 密匙生成策略
     * @return 生成策略
     * @since 0.0.1
     */
    Class<? extends ITokenGenerator> tokenGenerator() default ITokenGenerator.class;

    /**
     * 存活时间
     *
     * 单位：秒
     * @return 时间
     * @since 0.0.1
     */
    int ttl() default 60;

}
