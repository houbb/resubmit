package com.github.houbb.resubmit.api.core;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IResubmit {

    /**
     * 获取锁
     * @param context 上下文
     * @since 0.0.1
     * @throws com.github.houbb.resubmit.api.exception.ResubmitException 如果已经存在，则直接中断
     */
    void resubmit(final IResubmitContext context);

}
