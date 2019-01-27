package aop;

import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Data
public class ReflectiveMethodInvocation implements MethodInvocation {

    protected final Object proxy;

    protected final Object target;

    /**
     * 要调用的方法
     */
    protected final Method method;

    /**
     * 方法参数
     */
    protected Object[] arguments;

    /**
     *
     */
    private final Class targetClass;

    private Map<String, Object> userAttributes;

    /**
     * 拦截器的list
     */
    protected final List interceptorsAndDynamicMethodMatchers;


    private int currentInterceptorIndex = -1;

    public ReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class targetClass, List interceptorsAndDynamicMethodMatchers) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.targetClass = targetClass;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    @Override
    public Object proceed() throws Throwable {
        //没有拦截器，直接调用方法
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            method.setAccessible(true);

            return method.invoke(target, arguments);
        }
        Object interceptorOrInterceptionAdvice =
                this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        return ((MethodInterceptor) interceptorOrInterceptionAdvice).invoke(this);
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return this.method;
    }
}
