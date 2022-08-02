package com.github.houbb.resubmit.spring.config;

import com.github.houbb.common.cache.api.service.ICommonCacheService;
import com.github.houbb.common.cache.core.service.CommonCacheServiceMap;
import com.github.houbb.resubmit.api.support.IKeyGenerator;
import com.github.houbb.resubmit.api.support.ITokenGenerator;
import com.github.houbb.resubmit.core.bs.ResubmitBs;
import com.github.houbb.resubmit.core.support.key.KeyGenerator;
import com.github.houbb.resubmit.core.support.token.HttpServletRequestTokenGenerator;
import com.github.houbb.resubmit.spring.annotation.EnableResubmit;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * aop 配置
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@Configuration
@ComponentScan(basePackages = "com.github.houbb.resubmit.spring")
public class ResubmitAopConfig  implements ImportAware, BeanFactoryPostProcessor {

    @Bean("resubmitCache")
    public ICommonCacheService commonCacheService() {
        return new CommonCacheServiceMap();
    }

    @Bean("resubmitKeyGenerator")
    public IKeyGenerator keyGenerator() {
        return new KeyGenerator();
    }

    @Bean("resubmitTokenGenerator")
    public ITokenGenerator tokenGenerator() {
        return new HttpServletRequestTokenGenerator();
    }

    @Bean("resubmitBs")
    public ResubmitBs resubmitBs() {
        ICommonCacheService commonCacheService = beanFactory.getBean(enableSandGlassAttributes.getString("cache"), ICommonCacheService.class);
        IKeyGenerator keyGenerator = beanFactory.getBean(enableSandGlassAttributes.getString("keyGenerator"), IKeyGenerator.class);
        ITokenGenerator tokenGenerator = beanFactory.getBean(enableSandGlassAttributes.getString("tokenGenerator"), ITokenGenerator.class);

        return ResubmitBs.newInstance()
                .cache(commonCacheService)
                .keyGenerator(keyGenerator)
                .tokenGenerator(tokenGenerator);
    }

    /**
     * 属性信息
     */
    private AnnotationAttributes enableSandGlassAttributes;

    /**
     * bean 工厂
     *
     * @since 0.0.5
     */
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        enableSandGlassAttributes = AnnotationAttributes.fromMap(
                annotationMetadata.getAnnotationAttributes(EnableResubmit.class.getName(), false));
        if (enableSandGlassAttributes == null) {
            throw new IllegalArgumentException(
                    "@EnableResubmit is not present on importing class " + annotationMetadata.getClassName());
        }
    }

}
