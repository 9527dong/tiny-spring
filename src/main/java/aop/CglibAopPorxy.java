package aop;

import lombok.Data;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Data
public class CglibAopPorxy implements MethodInterceptor, AopProxy {
    private ProxyFactory proxyFactory;

    public CglibAopPorxy(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        this.proxyFactory.getBeforeAdvices().forEach(methodBeforeAdvice -> {
            try {
                methodBeforeAdvice.before(method, args, proxyFactory.getTarget());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        Object object = methodProxy.invokeSuper(o, args);
        this.proxyFactory.getAfterReturningAdvices().forEach(afterReturningAdvice -> {
            try {
                afterReturningAdvice.afterReturning(object, method, args, proxyFactory.getTarget());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        return object;
    }

    @Override
    public Object getProxy() {
        return Enhancer.create(proxyFactory.getTarget().getClass(), this);
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Enhancer.create(proxyFactory.getTarget().getClass(), this);
    }
}
