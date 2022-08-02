package com.github.houbb.resubmit.core.support.token;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.resubmit.api.support.ITokenGenerator;
import com.github.houbb.resubmit.core.constant.ResubmitConst;

import javax.servlet.http.HttpServletRequest;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class HttpServletRequestTokenGenerator implements ITokenGenerator {

    /**
     * 日志信息
     * @since 0.0.1
     */
    private static final Log LOG = LogFactory.getLog(HttpServletRequestTokenGenerator.class);

    @Override
    public String gen(Object[] params) {
        if(ArrayUtil.isEmpty(params)) {
            LOG.warn("Param is empty, return empty");
            return StringUtil.EMPTY;
        }

        for(Object param : params) {
            if(param instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest)param;
                String requestToken = request.getHeader(ResubmitConst.TOKEN);
                LOG.warn("header {} is found in request, value is: {}",
                        ResubmitConst.TOKEN,
                        requestToken);
                return requestToken;
            }
        }
        LOG.warn("Param is not found in request, return empty");
        return StringUtil.EMPTY;
    }

}
