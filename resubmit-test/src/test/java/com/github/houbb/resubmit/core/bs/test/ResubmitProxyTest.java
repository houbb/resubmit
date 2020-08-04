package com.github.houbb.resubmit.core.bs.test;

import com.github.houbb.heaven.util.util.DateUtil;
import com.github.houbb.resubmit.api.exception.ResubmitException;
import com.github.houbb.resubmit.core.support.proxy.ResubmitProxy;
import com.github.houbb.resubmit.test.service.UserService;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class ResubmitProxyTest {


    @Test(expected = ResubmitException.class)
    public void errorTest() {
        UserService service = ResubmitProxy.getProxy(new UserService());
        service.queryInfo("1");
        service.queryInfo("1");
    }

    @Test
    public void untilTtlTest() {
        UserService service = ResubmitProxy.getProxy(new UserService());
        service.queryInfo("1");
        DateUtil.sleep(TimeUnit.SECONDS, 6);
        service.queryInfo("1");
    }

    @Test
    public void differParamTest() {
        UserService service = ResubmitProxy.getProxy(new UserService());
        service.queryInfo("1");
        service.queryInfo("2");
    }

}
