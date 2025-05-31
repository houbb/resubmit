package com.github.houbb.resubmit.core.api;

import com.github.houbb.common.cache.api.service.ICommonCacheService;
import com.github.houbb.common.cache.core.constant.CommonCacheConst;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.resubmit.api.core.IResubmit;
import com.github.houbb.resubmit.api.core.IResubmitContext;
import com.github.houbb.resubmit.api.exception.ResubmitException;
import com.github.houbb.resubmit.api.support.ITokenGenerator;

import java.lang.reflect.Method;

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
        final ICommonCacheService cache = context.cache();
        final ITokenGenerator tokenGenerator = context.tokenGenerator();
        Object[] params = context.params();
        Method method = context.method();
        String paramKey = context.keyGenerator().gen(method, params);
        String tokenKey = tokenGenerator.gen(params);

        String fullKey = tokenKey+paramKey;
        long expireMills = context.expireMills();

        String sexNx = cache.set(fullKey, "1", CommonCacheConst.NX, CommonCacheConst.PX, (int) expireMills);
        if(!CommonCacheConst.OK.equalsIgnoreCase(sexNx)) {
            LOG.debug("[Resubmit] 信息重复提交, key: {}", fullKey);
            throw new ResubmitException("信息重复提交!");
        }

        LOG.debug("[Resubmit] 设置 cache 信息, key: {}, ttl: {}", fullKey, expireMills);
    }

}
