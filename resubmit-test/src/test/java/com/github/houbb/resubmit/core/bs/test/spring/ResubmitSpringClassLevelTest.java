package com.github.houbb.resubmit.core.bs.test.spring;

import com.github.houbb.resubmit.api.exception.ResubmitException;
import com.github.houbb.resubmit.test.config.SpringConfig;
import com.github.houbb.resubmit.test.service.ClassLevelService;
import com.github.houbb.resubmit.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author binbin.hou
 * @since 0.0.2
 */
@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ResubmitSpringClassLevelTest {

    @Autowired
    private ClassLevelService classLevelService;

    @Test(expected = ResubmitException.class)
    public void queryInfoLimitTest() {
        classLevelService.queryInfoLimit("1");
        classLevelService.queryInfoLimit("1");
    }

    @Test
    public void queryInfoNoLimitTest() {
        classLevelService.queryInfoNoLimit("1");
        classLevelService.queryInfoNoLimit("1");
    }


}
