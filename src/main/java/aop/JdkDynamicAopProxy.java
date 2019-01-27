package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicAopProxy implements InvocationHandler,AopProxy {
    private ProxyFactory proxyFactory;

    JdkDynamicAopProxy(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return new ReflectiveMethodInvocation(proxyFactory.getTarget(),proxyFactory.getTarget(),method,args,proxyFactory.getTarget().getClass(),proxyFactory.getMethodInterceptors()).proceed();
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(proxyFactory.getTarget().getClass().getClassLoader(),proxyFactory.getTarget().getClass().getInterfaces(),this);

    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader,proxyFactory.getTarget().getClass().getInterfaces(),this);
    }
}