package com.github.houbb.resubmit.api.support;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ITokenGenerator {

    /**
     * 根据入参构建对应的 token
     * @param params 从请求入参中获取信息
     * @return 结果
     * @since 0.0.1
     */
    String gen(final Object[] params);

}
