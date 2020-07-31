package com.github.houbb.resubmit.api.support;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ICache {

    /**
     * 存放
     * @param key 入参
     * @param ttlSeconds 时间
     * @since 0.0.1
     */
    void put(final String key, final int ttlSeconds);

    /**
     * 移除
     * @param key 标识
     * @since 0.0.1
     * @return 是否包含
     */
    boolean contains(final String key);

}
