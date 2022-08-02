/*
 * Copyright (c)  2019. houbinbin Inc.
 * async All rights reserved.
 */

package com.github.houbb.resubmit.core.support.proxy;

import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.resubmit.core.bs.ResubmitBs;
import com.github.houbb.resubmit.core.support.proxy.cglib.CglibProxy;
import com.github.houbb.resubmit.core.support.proxy.dynamic.DynamicProxy;
import com.github.houbb.resubmit.core.support.proxy.none.NoneProxy;

import java.lang.reflect.Proxy;

/**
 * <p> 代理信息 </p>
 *
 * <pre> Created: 2019/3/8 10:38 AM  </pre>
 * <pre> Project: async  </pre>
 *
 * @author houbinbin
 * @since 0.0.1
 */
public final class ResubmitProxy {

    private ResubmitProxy(){}

    /**
     * 获取对象代理
     * @param <T> 泛型
     * @param object 对象代理
     * @return 代理信息
     * @since 0.0.1
     */
    @SuppressWarnings("all")
    public static <T> T getProxy(final T object) {
        final ResubmitBs resubmitBs = ResubmitBs.newInstance();

        return getProxy(object, resubmitBs);
    }

    /**
     * 获取对象代理
     * @param <T> 泛型
     * @param object 对象代理
     * @return 代理信息
     * @since 0.0.1
     */
    @SuppressWarnings("all")
    public static <T> T getProxy(final T object, final ResubmitBs resubmitBs) {
        if(ObjectUtil.isNull(object)) {
            return (T) new NoneProxy(object).proxy();
        }

        final Class clazz = object.getClass();

        // 如果targetClass本身是个接口或者targetClass是JDK Proxy生成的,则使用JDK动态代理。
        // 参考 spring 的 AOP 判断
        if (clazz.isInterface() || Proxy.isProxyClass(clazz)) {
            return (T) new DynamicProxy(object, resubmitBs).proxy();
        }

        return (T) new CglibProxy(object, resubmitBs).proxy();
    }

}
