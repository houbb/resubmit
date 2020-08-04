package com.github.houbb.resubmit.spring.aop;

import com.github.houbb.aop.spring.util.SpringAopUtil;
import com.github.houbb.resubmit.api.annotation.Resubmit;
import com.github.houbb.resubmit.core.support.proxy.ResubmitProxy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author binbin.hou
 * @since 0.0.2
 */
@Aspect
@Component
public class ResubmitAspect {

    @Pointcut("@annotation(com.github.houbb.resubmit.api.annotation.Resubmit)")
    public void resubmitPointcut() {
    }

    /**
     * 执行核心方法
     *
     * 相当于 MethodInterceptor
     * @param point 切点
     * @return 结果
     * @throws Throwable 异常信息
     * @since 0.0.2
     */
    @Around("resubmitPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method method = SpringAopUtil.getCurrentMethod(point);

        if(method.isAnnotationPresent(Resubmit.class)) {
            // 执行代理操作
            Object[] args = point.getArgs();
            ResubmitProxy.resubmit(method, args);
        }

        // 正常方法调用
        return point.proceed();
    }
}
