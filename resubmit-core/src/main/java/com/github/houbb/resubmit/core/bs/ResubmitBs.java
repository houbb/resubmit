package com.github.houbb.resubmit.core.bs;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.resubmit.api.core.IResubmit;
import com.github.houbb.resubmit.api.core.IResubmitContext;
import com.github.houbb.resubmit.api.support.ICache;
import com.github.houbb.resubmit.api.support.IKeyGenerator;
import com.github.houbb.resubmit.api.support.ITokenGenerator;
import com.github.houbb.resubmit.core.api.Resubmit;
import com.github.houbb.resubmit.core.api.ResubmitContext;
import com.github.houbb.resubmit.core.support.cache.ConcurrentHashMapCache;
import com.github.houbb.resubmit.core.support.key.KeyGeneratorFastJson;
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
    private final IResubmit resubmit = Instances.singleton(Resubmit.class);

    /**
     * key 生成实现策略
     * @since 0.0.1
     */
    private Class<? extends IKeyGenerator> keyGenerator;

    /**
     * 密匙生成策略
     * @since 0.0.1
     */
    private Class<? extends ITokenGenerator> tokenGenerator;

    /**
     * 缓存类信息
     * @since 0.0.1
     */
    private Class<? extends ICache> cache;

    /**
     * 存活时间
     * @since 0.0.1
     */
    private int ttl = 60;

    /**
     * 方法信息
     * @since 0.0.1
     */
    private Method method;

    /**
     * 参数信息
     *
     * @since 0.0.1
     */
    private Object[] params;

    /**
     * 新建对象实例
     * @return 实现
     * @since 0.0.1
     */
    public static ResubmitBs newInstance() {
        return new ResubmitBs();
    }

    public Class<? extends IKeyGenerator> keyGenerator() {
        return keyGenerator;
    }

    public ResubmitBs keyGenerator(Class<? extends IKeyGenerator> keyGenerator) {
        this.keyGenerator = keyGenerator;
        return this;
    }

    public Class<? extends ITokenGenerator> tokenGenerator() {
        return tokenGenerator;
    }

    public ResubmitBs tokenGenerator(Class<? extends ITokenGenerator> tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
        return this;
    }

    public Class<? extends ICache> cache() {
        return cache;
    }

    public ResubmitBs cache(Class<? extends ICache> cache) {
        this.cache = cache;
        return this;
    }

    public int ttl() {
        return ttl;
    }

    public ResubmitBs ttl(int ttl) {
        this.ttl = ttl;
        return this;
    }

    public Method method() {
        return method;
    }

    public ResubmitBs method(Method method) {
        this.method = method;
        return this;
    }

    public Object[] params() {
        return params;
    }

    public ResubmitBs params(Object[] params) {
        this.params = params;
        return this;
    }

    /**
     * 重提交判断
     * @since 0.0.1
     */
    public void resubmit() {
        //0. param check
        ArgUtil.notNull(method, "method");

        //1. 默认值
        fillDefault();

        //2. 构建实例
        final ICache cacheInstance = Instances.singleton(cache);
        final IKeyGenerator keyInstance = ClassUtil.newInstance(keyGenerator);
        final ITokenGenerator tokenInstance = ClassUtil.newInstance(tokenGenerator);

        //3. 构建上下文
        IResubmitContext context = ResubmitContext.newInstance()
                .cache(cacheInstance)
                .keyGenerator(keyInstance)
                .tokenGenerator(tokenInstance)
                .method(method)
                .params(params)
                .ttl(ttl)
                ;

        //4. 执行结果
        this.resubmit.resubmit(context);
    }

    /**
     * 填充默认值
     * @since 0.0.1
     */
    private void fillDefault() {
        if(cache == null
            || ICache.class.equals(cache)) {
            cache = ConcurrentHashMapCache.class;
        }

        if(ObjectUtil.isNull(keyGenerator)
            || IKeyGenerator.class.equals(keyGenerator)) {
            keyGenerator = KeyGeneratorFastJson.class;
        }

        if(ObjectUtil.isNull(tokenGenerator)
            || ITokenGenerator.class.equals(tokenGenerator)) {
            tokenGenerator = HttpServletRequestTokenGenerator.class;
        }
    }

}
