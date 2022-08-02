package com.github.houbb.resubmit.test.config;

import com.github.houbb.common.cache.api.service.ICommonCacheService;
import com.github.houbb.resubmit.spring.annotation.EnableResubmit;
import com.github.houbb.resubmit.test.cache.MyDefineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@ComponentScan("com.github.houbb.resubmit.test.service")
@Configuration
@EnableResubmit(cache = "myDefineCache")
public class SpringDefineConfig {

    @Bean("myDefineCache")
    public ICommonCacheService myDefineCache() {
        return new MyDefineCache();
    }

}
