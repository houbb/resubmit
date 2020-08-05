package com.github.houbb.resubmit.core.bs.test2.springboot.starter;

import com.github.houbb.resubmit.api.exception.ResubmitException;
import com.github.houbb.resubmit.test2.service.ResubmitApplication;
import com.github.houbb.resubmit.test2.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author binbin.hou
 * @since 0.0.2
 */
@ContextConfiguration(classes = ResubmitApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ResubmitSpringBootStarterTest {

    @Autowired
    private UserService service;

    @Test(expected = ResubmitException.class)
    public void queryTest() {
        service.queryInfo("1");
        service.queryInfo("1");
    }

}
