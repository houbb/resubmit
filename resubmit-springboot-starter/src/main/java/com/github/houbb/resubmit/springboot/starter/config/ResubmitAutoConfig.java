package com.github.houbb.resubmit.springboot.starter.config;

import com.github.houbb.resubmit.spring.annotation.EnableResubmit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 防止重复提交自动配置
 * @author binbin.hou
 * @since 0.0.3
 */
@Configuration
@EnableConfigurationProperties(ResubmitProperties.class)
@ConditionalOnClass(EnableResubmit.class)
@EnableResubmit
public class ResubmitAutoConfig {

    private final ResubmitProperties resubmitProperties;

    public ResubmitAutoConfig(ResubmitProperties resubmitProperties) {
        this.resubmitProperties = resubmitProperties;
    }

}
