package aop;

import java.lang.reflect.Method;

/**
 * aop前置增强接口
 */
public interface MethodBeforeAdvice extends Advice{
    void before(Method method, Object[] args, Object target) throws Throwable;
}
