package aopAdvice;

import aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class PerformerBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("GREETING PERFORMER");
    }
}
