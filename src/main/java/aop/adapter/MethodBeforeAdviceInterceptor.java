package aop.adapter;

import aop.MethodBeforeAdvice;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;

public class MethodBeforeAdviceInterceptor implements MethodInterceptor, Advice, Serializable {
    private final MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        advice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis());
        return invocation.proceed();
    }
}
