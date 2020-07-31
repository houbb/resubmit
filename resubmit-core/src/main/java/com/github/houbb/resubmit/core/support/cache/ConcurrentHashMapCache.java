package com.github.houbb.resubmit.core.support.cache;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.resubmit.api.support.ICache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ConcurrentHashMapCache implements ICache {

    /**
     * 日志信息
     * @since 0.0.1
     */
    private static final Log LOG = LogFactory.getLog(ConcurrentHashMapCache.class);

    /**
     * 存储信息
     * @since 0.0.1
     */
    private static final ConcurrentHashMap<String, Long> MAP = new ConcurrentHashMap<>();

    static {
        Executors.newScheduledThreadPool(1)
            .scheduleAtFixedRate(new CleanTask(), 1, 1,
                    TimeUnit.SECONDS);
    }

    /**
     * 清理任务执行
     * @since 0.0.1
     */
    private static class CleanTask implements Runnable {
        @Override
        public void run() {
            LOG.info("[Clean] 开始清理过期数据");

            // 当前时间固定，不需要考虑删除的耗时
            // 毕竟最多相差 1s，但是和系统的时钟交互是比删除耗时多的。
            long currentMills = System.currentTimeMillis();

            for(Map.Entry<String, Long> entry : MAP.entrySet()) {
                long live = entry.getValue();
                if(currentMills >= live) {
                    final String key = entry.getKey();
                    MAP.remove(key);
                    LOG.info("[Clean] 移除 key: {}", key);
                }
            }

            LOG.info("[Clean] 结束清理过期数据");
        }
    }

    @Override
    public void put(String key, int ttlSeconds) {
        if(ttlSeconds <= 0) {
            LOG.info("[put] ttl is less than 1, just ignore.");
            return;
        }
        long time = System.currentTimeMillis();
        long liveTo = time + ttlSeconds * 1000;

        MAP.putIfAbsent(key, liveTo);
    }

    @Override
    public boolean contains(String key) {
        return MAP.containsKey(key);
    }

}
