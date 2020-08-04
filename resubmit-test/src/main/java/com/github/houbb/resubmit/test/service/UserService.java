package com.github.houbb.resubmit.test.service;

import com.github.houbb.resubmit.api.annotation.Resubmit;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class UserService {

    @Resubmit(ttl = 5)
    public void queryInfo(final String id) {
        System.out.println("query info: " + id);
    }

}
