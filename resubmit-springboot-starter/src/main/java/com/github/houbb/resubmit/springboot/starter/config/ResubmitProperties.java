package com.github.houbb.resubmit.springboot.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * 这里后期可以引入更多的参数
 * @author binbin.hou
 * @since 0.0.3
 */
@ConfigurationProperties(prefix = "resubmit")
public class ResubmitProperties {

    /**
     * 是否启用
     * @since 0.0.3
     */
    private String enable;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "ResubmitProperties{" +
                "enable='" + enable + '\'' +
                '}';
    }

}
