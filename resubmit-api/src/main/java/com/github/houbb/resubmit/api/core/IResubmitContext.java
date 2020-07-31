package com.github.houbb.resubmit.api.core;

import com.github.houbb.resubmit.api.support.ICache;
import com.github.houbb.resubmit.api.support.IKeyGenerator;
import com.github.houbb.resubmit.api.support.ITokenGenerator;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IResubmitContext {

    /**
     * 请求参数构建的 key
     * @return 结果
     * @since 0.0.1
     */
    Object[] params();

    /**
     * 锁的存活时间
     * @return 存活时间
     * @since 0.0.1
     */
    int ttl();

    /**
     * key 生成策略
     * @return 结果
     * @since 0.0.1
     */
    IKeyGenerator keyGenerator();

    /**
     * 密匙的生成策略
     * @return 策略
     * @since 0.0.1
     */
    ITokenGenerator tokenGenerator();

    /**
     * 缓存
     * @since 0.0.1
     * @return 缓存实现
     */
    ICache cache();

}
