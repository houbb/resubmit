package com.github.houbb.resubmit.core.api;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.resubmit.api.core.IResubmit;
import com.github.houbb.resubmit.api.core.IResubmitContext;
import com.github.houbb.resubmit.api.exception.ResubmitException;
import com.github.houbb.resubmit.api.support.ICache;
import com.github.houbb.resubmit.api.support.ITokenGenerator;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class Resubmit implements IResubmit {

    /**
     * 日志信息
     * @since 0.0.1
     */
    private static final Log LOG = LogFactory.getLog(Resubmit.class);

    @Override
    public void resubmit(IResubmitContext context) {
        final ICache cache = context.cache();
        final ITokenGenerator tokenGenerator = context.tokenGenerator();
        Object[] params = context.params();
        String paramKey = context.keyGenerator().gen(params);
        String tokenKey = tokenGenerator.gen(params);

        String fullKey = tokenKey+paramKey;
        boolean contains = cache.contains(fullKey);

        //1. 说明已经提交过一次了。
        if(contains) {
            LOG.warn("信息重复提交, key: {}", fullKey);
            throw new ResubmitException("信息重复提交!");
        }

        //2. 没有提交过
        // 正常的流程处理
        cache.put(fullKey, context.ttl());
        LOG.info("设置 cache 信息, key: {}, ttl: {}", context.ttl());
    }

}
