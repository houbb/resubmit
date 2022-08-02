package com.github.houbb.resubmit.core.bs.test;

import com.github.houbb.resubmit.api.exception.ResubmitException;
import com.github.houbb.resubmit.core.bs.ResubmitBs;
import com.github.houbb.resubmit.core.support.proxy.ResubmitProxy;
import com.github.houbb.resubmit.test.cache.MyDefineCache;
import com.github.houbb.resubmit.test.service.UserService;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class ResubmitProxyDefineTest {

    @Test(expected = ResubmitException.class)
    public void defineTest() {
        ResubmitBs resubmitBs = ResubmitBs.newInstance()
                .cache(new MyDefineCache());

        UserService service = ResubmitProxy.getProxy(new UserService(), resubmitBs);

        service.queryInfo("1");
        service.queryInfo("1");
    }

}
