package aopEntity;

import aop.DelegatingIntroductionInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return super.invoke(invocation);
    }
}
