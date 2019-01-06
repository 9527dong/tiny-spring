package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicAopProxy implements InvocationHandler {
    private ProxyFactory proxyFactory;

    public JdkDynamicAopProxy(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.proxyFactory.getBeforeAdvices().forEach(methodBeforeAdvice -> {
            try {
                methodBeforeAdvice.before(method,args,proxyFactory.getTarget());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        Object object =  method.invoke(proxyFactory.getTarget(),args);
        this.proxyFactory.getAfterReturningAdvices().forEach(afterReturningAdvice -> {
            try {
                afterReturningAdvice.afterReturning(object,method,args,proxyFactory.getTarget());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        return object;
    }

    Object getProxy() {
        return Proxy.newProxyInstance(proxyFactory.getTarget().getClass().getClassLoader(),proxyFactory.getTarget().getClass().getInterfaces(),this);

    }
}