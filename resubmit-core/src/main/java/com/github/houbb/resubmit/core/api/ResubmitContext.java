package com.github.houbb.resubmit.core.api;

import com.github.houbb.common.cache.api.service.ICommonCacheService;
import com.github.houbb.resubmit.api.core.IResubmitContext;
import com.github.houbb.resubmit.api.support.IKeyGenerator;
import com.github.houbb.resubmit.api.support.ITokenGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class ResubmitContext implements IResubmitContext {

    /**
     * 请求参数
     * @since 0.0.1
     */
    private Object[] params;

    /**
     * 方法信息
     * @since 0.0.1
     */
    private Method method;

    /**
     * 存活时间
     * @since 0.0.1
     */
    private long expireMills;

    /**
     * key 生成结果
     * @since 0.0.1
     */
    private IKeyGenerator keyGenerator;

    /**
     * 密匙生成策略
     * @since 0.0.1
     */
    private ITokenGenerator tokenGenerator;

    /**
     * 缓存信息
     * @since 0.0.1
     */
    private ICommonCacheService cache;

    public static ResubmitContext newInstance() {
        return new ResubmitContext();
    }

    @Override
    public Method method() {
        return method;
    }

    public ResubmitContext method(Method method) {
        this.method = method;
        return this;
    }

    @Override
    public Object[] params() {
        return params;
    }

    public ResubmitContext params(Object[] params) {
        this.params = params;
        return this;
    }

    @Override
    public long expireMills() {
        return expireMills;
    }

    public ResubmitContext expireMills(long expireMills) {
        this.expireMills = expireMills;
        return this;
    }

    @Override
    public IKeyGenerator keyGenerator() {
        return keyGenerator;
    }

    public ResubmitContext keyGenerator(IKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
        return this;
    }

    @Override
    public ITokenGenerator tokenGenerator() {
        return tokenGenerator;
    }

    public ResubmitContext tokenGenerator(ITokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
        return this;
    }

    @Override
    public ICommonCacheService cache() {
        return cache;
    }

    public ResubmitContext cache(ICommonCacheService cache) {
        this.cache = cache;
        return this;
    }

    @Override
    public String toString() {
        return "ResubmitContext{" +
                "params=" + Arrays.toString(params) +
                ", method=" + method +
                ", expireMills=" + expireMills +
                ", keyGenerator=" + keyGenerator +
                ", tokenGenerator=" + tokenGenerator +
                ", cache=" + cache +
                '}';
    }

}
