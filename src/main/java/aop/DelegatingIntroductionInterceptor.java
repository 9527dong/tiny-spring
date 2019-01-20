package aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * 引入的实现
 */
public class DelegatingIntroductionInterceptor implements MethodInterceptor {


    private Object delegate;

    public DelegatingIntroductionInterceptor(Object delegate) {
        this.delegate = delegate;
    }

    public DelegatingIntroductionInterceptor() {
        delegate = this;
    }

    public Object getDelegate() {
        return delegate;
    }

    public void setDelegate(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method =  invocation.getMethod();
        method.setAccessible(true);

        return method.invoke(delegate, invocation.getArguments());
    }
}
