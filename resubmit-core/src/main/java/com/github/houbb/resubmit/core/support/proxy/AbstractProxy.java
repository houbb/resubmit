package com.github.houbb.resubmit.core.support.proxy;

import com.github.houbb.heaven.support.proxy.IProxy;
import com.github.houbb.resubmit.core.bs.ResubmitBs;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public abstract class AbstractProxy implements IProxy {

    /**
     * 引导类
     */
    protected final ResubmitBs resubmitBs;

    protected AbstractProxy(ResubmitBs resubmitBs) {
        this.resubmitBs = resubmitBs;
    }

    public AbstractProxy() {
        this.resubmitBs = ResubmitBs.newInstance();
    }

}
