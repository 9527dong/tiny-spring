package aopAdvice;

import aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class PerformerAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("THAT WAS GREAT");
    }
}
