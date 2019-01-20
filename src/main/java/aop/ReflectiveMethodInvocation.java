package aop;

import lombok.Data;
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

    public ReflectiveMethodInvocation(Object proxy, Object target, Method method, Class targetClass, List interceptorsAndDynamicMethodMatchers) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.targetClass = targetClass;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    @Override
    public Object proceed() throws Throwable {
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            method.setAccessible(true);

            method.invoke(target, arguments);
        }
        Object interceptorOrInterceptionAdvice =
                this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
//        if (interceptorOrInterceptionAdvice instanceof InterceptorAndDynamicMethodMatcher) {
//            // Evaluate dynamic method matcher here: static part will already have
//            // been evaluated and found to match.
//            InterceptorAndDynamicMethodMatcher dm =
//                    (InterceptorAndDynamicMethodMatcher) interceptorOrInterceptionAdvice;
//            if (dm.methodMatcher.matches(this.method, this.targetClass, this.arguments)) {
//                return dm.interceptor.invoke(this);
//            }
//            else {
//                // Dynamic matching failed.
//                // Skip this interceptor and invoke the next in the chain.
//                return proceed();
//            }
//        }
//        else {
//            // It's an interceptor, so we just invoke it: The pointcut will have
//            // been evaluated statically before this object was constructed.
//            return ((MethodInterceptor) interceptorOrInterceptionAdvice).invoke(this);
//        }
        return null;
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
