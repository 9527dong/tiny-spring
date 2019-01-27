package aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 引入的实现
 */
public class DelegatingIntroductionInterceptor implements MethodInterceptor {


    private Object delegate;
    private List<Class> supportInterfaces = new ArrayList<>();
    private List<Method> supportMethods = new ArrayList<>();


    public DelegatingIntroductionInterceptor(Object delegate) {
        init(delegate);
    }

    public DelegatingIntroductionInterceptor() {
        init(this);
    }

    private void init(Object delegate) {
        this.delegate = delegate;
        supportInterfaces = Arrays.asList(delegate.getClass().getInterfaces());
        supportMethods = Arrays.asList(delegate.getClass().getMethods());

    }

    public Object getDelegate() {
        return delegate;
    }

    public void setDelegate(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (supportInterfaces.contains(invocation.getMethod().getDeclaringClass())){
            Method method =  invocation.getMethod();
            method.setAccessible(true);

            return method.invoke(delegate, invocation.getArguments());
        }
        return invocation.proceed();
    }
}
