package aopAdvice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class PerformerAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        start(method);
        methodInvocation.proceed();
        end(method);
        return null;
    }

    private void start(Method method) {
        System.out.println(method.getName()+" 开始运行");
    }

    private void end(Method method) {
        System.out.println(method.getName()+" 结束运行");
    }
}
