package com.github.houbb.resubmit.test.service;

import com.github.houbb.resubmit.api.annotation.Resubmit;
import org.springframework.stereotype.Service;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@Service
@Resubmit(value = 5000, enable = true)
public class ClassLevelService {

    @Resubmit(enable = false)
    public void queryInfoNoLimit(final String id) {
        System.out.println("queryInfoNoLimit: " + id);
    }

    public void queryInfoLimit(final String id) {
        System.out.println("queryInfoLimit: " + id);
    }

}
