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
        return new ReflectiveMethodInvocation(proxyFactory.getProxy(),proxyFactory.getTarget(),method,args,proxyFactory.getTarget().getClass(),proxyFactory.getMethodInterceptors()).proceed();
    }

    @Override
    public Object getProxy() {
        return Enhancer.create(proxyFactory.getTarget().getClass(), proxyFactory.getInterfaces(),this);
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Enhancer.create(proxyFactory.getTarget().getClass(), this);
    }
}
