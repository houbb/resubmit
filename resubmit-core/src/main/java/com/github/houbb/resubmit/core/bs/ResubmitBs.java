package com.github.houbb.resubmit.core.bs;

import com.github.houbb.common.cache.api.service.ICommonCacheService;
import com.github.houbb.common.cache.core.service.CommonCacheServiceMap;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.resubmit.api.core.IResubmit;
import com.github.houbb.resubmit.api.core.IResubmitContext;
import com.github.houbb.resubmit.api.support.IKeyGenerator;
import com.github.houbb.resubmit.api.support.ITokenGenerator;
import com.github.houbb.resubmit.core.api.Resubmit;
import com.github.houbb.resubmit.core.api.ResubmitContext;
import com.github.houbb.resubmit.core.support.key.KeyGenerator;
import com.github.houbb.resubmit.core.support.token.HttpServletRequestTokenGenerator;

import java.lang.reflect.Method;

/**
 * 引导类
 * @author binbin.hou
 * @since 0.0.1
 */
public class ResubmitBs {

    private ResubmitBs(){}

    /**
     * 实现类
     * @since 0.0.1
     */
    private final IResubmit resubmit = new Resubmit();

    /**
     * key 生成实现策略
     * @since 0.0.1
     */
    private IKeyGenerator keyGenerator = new KeyGenerator();

    /**
     * 密匙生成策略
     * @since 0.0.1
     */
    private ITokenGenerator tokenGenerator = new HttpServletRequestTokenGenerator();

    /**
     * 缓存类信息
     *
     * 默认基于本地 map, 实际生产建议使用基于 redis 的实现。
     * @since 0.0.1
     */
    private ICommonCacheService cache = new CommonCacheServiceMap();

    /**
     * 新建对象实例
     * @return 实现
     * @since 0.0.1
     */
    public static ResubmitBs newInstance() {
        return new ResubmitBs();
    }

    public IKeyGenerator keyGenerator() {
        return keyGenerator;
    }

    public ResubmitBs keyGenerator(IKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
        return this;
    }

    public ITokenGenerator tokenGenerator() {
        return tokenGenerator;
    }

    public ResubmitBs tokenGenerator(ITokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
        return this;
    }

    public ICommonCacheService cache() {
        return cache;
    }

    public ResubmitBs cache(ICommonCacheService cache) {
        this.cache = cache;
        return this;
    }

    /**
     * 重提交判断
     * @param expireMills 过期时间
     * @param method 方法
     * @param params 参数
     * @since 0.0.1
     */
    public void resubmit(final long expireMills,
                         final Method method,
                         final Object[] params) {
        //0. param check
        ArgUtil.notNull(method, "method");

        //1. 构建上下文
        IResubmitContext context = ResubmitContext.newInstance()
                .cache(cache)
                .keyGenerator(keyGenerator)
                .tokenGenerator(tokenGenerator)
                .expireMills(expireMills)
                .method(method)
                .params(params)
                ;

        //2. 执行结果
        this.resubmit.resubmit(context);
    }

    /**
     * 重复提交验证
     *
     * @param method 方法
     * @param args   入参
     * @since 0.0.1
     */
    public void resubmit(final Method method,
                         final Object[] args) {
        // 获取注解信息
        //1. 方法上的优先级较高，如果存在一个，则直接忽略类上的。避免混乱。
        com.github.houbb.resubmit.api.annotation.Resubmit annotationResubmit = getAnnotationResubmit(method);
        if(annotationResubmit != null) {
            if(!annotationResubmit.enable()) {
                return;
            }

            long actualExpireMills = annotationResubmit.value();
            this.resubmit(actualExpireMills, method, args);
        }
    }

    private com.github.houbb.resubmit.api.annotation.Resubmit getAnnotationResubmit(final Method method) {
        // 获取注解信息
        //1. 方法上的优先级较高，如果存在一个，则直接忽略类上的。避免混乱。
        com.github.houbb.resubmit.api.annotation.Resubmit methodResubmit = method.getAnnotation(com.github.houbb.resubmit.api.annotation.Resubmit.class);
        if(methodResubmit != null) {
            return methodResubmit;
        }

        //2. 类上的信息
        final Class<?> clazz = method.getDeclaringClass();
        com.github.houbb.resubmit.api.annotation.Resubmit classResubmit = clazz.getAnnotation(com.github.houbb.resubmit.api.annotation.Resubmit.class);
        return classResubmit;
    }



}
