package aop;

import lombok.Data;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Data
public class CglibAopPorxy implements MethodInterceptor {
    public static CglibAopPorxy cglibAopPorxy = new CglibAopPorxy();

    public static Object getProxy(Class clazz) {
        return Enhancer.create(clazz, cglibAopPorxy);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        statr();
        methodProxy.invokeSuper(o, objects);
        end();
        return null;
    }

    private void statr() {
        System.out.println("开始执行");
    }
    private void end() {
        System.out.println("结束执行");
    }
}
