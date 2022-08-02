package com.github.houbb.resubmit.core.support.proxy.cglib;

import com.github.houbb.resubmit.core.bs.ResubmitBs;
import com.github.houbb.resubmit.core.support.proxy.AbstractProxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 代理类
 * @author binbin.hou
 * date 2019/3/7
 * @since 0.0.2
 */
public class CglibProxy extends AbstractProxy implements MethodInterceptor {

    /**
     * 被代理的对象
     */
    private final Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    public CglibProxy(Object target, ResubmitBs resubmitBs) {
        super(resubmitBs);
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //1. 添加判断
        super.resubmitBs.resubmit(method, objects);

        //2. 返回结果
        return method.invoke(target, objects);
    }

    @Override
    public Object proxy() {
        Enhancer enhancer = new Enhancer();
        //目标对象类
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        //通过字节码技术创建目标对象类的子类实例作为代理
        return enhancer.create();
    }

}
