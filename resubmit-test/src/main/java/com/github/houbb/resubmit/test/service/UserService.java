package com.github.houbb.resubmit.test.service;

import com.github.houbb.resubmit.api.annotation.Resubmit;
import org.springframework.stereotype.Service;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@Service
public class UserService {

    @Resubmit(5000)
    public void queryInfo(final String id) {
        System.out.println("query info: " + id);
    }

}
