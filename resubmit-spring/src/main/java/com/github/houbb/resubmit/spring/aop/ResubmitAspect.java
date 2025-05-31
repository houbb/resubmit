package com.github.houbb.resubmit.spring.aop;

import com.github.houbb.aop.spring.util.SpringAopUtil;
import com.github.houbb.resubmit.api.annotation.Resubmit;
import com.github.houbb.resubmit.core.bs.ResubmitBs;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author binbin.hou
 * @since 0.0.2
 */
@Aspect
@Component
public class ResubmitAspect {

    @Autowired
    @Qualifier("resubmitBs")
    private ResubmitBs resubmitBs;

    @Pointcut("@annotation(com.github.houbb.resubmit.api.annotation.Resubmit)")
    public void methodMyPointcut() {
    }

    /**
     * 指定注解的类
     */
    @Pointcut("@within(com.github.houbb.resubmit.api.annotation.Resubmit)")
    public void classMyPointcut() {
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
    @Around("methodMyPointcut() || classMyPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method method = SpringAopUtil.getCurrentMethod(point);

        // 执行代理操作
        Object[] args = point.getArgs();
        resubmitBs.resubmit(method, args);

        // 正常方法调用
        return point.proceed();
    }

}
