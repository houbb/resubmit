package com.github.houbb.resubmit.test.cache;

import com.github.houbb.common.cache.core.service.CommonCacheServiceMap;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class MyDefineCache extends CommonCacheServiceMap {

    // 这里只是作为演示，实际生产建议使用 redis 作为统一缓存
    @Override
    public synchronized void set(String key, String value, long expireMills) {
        System.out.println("------------- 自定义的设置实现");

        super.set(key, value, expireMills);
    }

}
