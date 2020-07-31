package com.github.houbb.resubmit.api.support;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IKeyGenerator {

    /**
     * 根据入参构建对应的 key
     * @param params 入参
     * @return 结果
     * @since 0.0.1
     */
    String gen(final Object params);

}
