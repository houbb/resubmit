package com.github.houbb.resubmit.test.config;

import com.github.houbb.resubmit.spring.annotation.EnableResubmit;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@ComponentScan("com.github.houbb.resubmit.test.service")
@EnableResubmit
@Configuration
public class SpringConfig {
}
