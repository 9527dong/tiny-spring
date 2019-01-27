package aop;

import aop.adapter.AfterReturningAdviceInterceptor;
import aop.adapter.MethodBeforeAdviceInterceptor;
import lombok.Data;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProxyFactory{
//    List<MethodBeforeAdvice> beforeAdvices = new ArrayList<>();
//    List<AfterReturningAdvice> afterReturningAdvices = new ArrayList<>();
//    List<Advice> aroundAdvices = new ArrayList<>();

    List<MethodInterceptor> methodInterceptors = new ArrayList<>();
    Object target;
    Class[] interfaces;
    boolean proxyTargetClass = false;

    public Object getProxy(){
        return createAopProxy().getProxy();
    }

    /**
     * 若是代理接口，使用jdk代理，如果没有接口，通过cglib代理
     * @return
     */
    private AopProxy createAopProxy() {
        if (proxyTargetClass) {
            return new CglibAopPorxy(this);
        }
        if (target.getClass().isInterface() || target.getClass().getInterfaces().length != 0){
            return new JdkDynamicAopProxy(this);
        }
        return new CglibAopPorxy(this);
    }

    public void addAdvice(Advice advice){
        if (advice instanceof  MethodBeforeAdvice){
            methodInterceptors.add(new MethodBeforeAdviceInterceptor((MethodBeforeAdvice)advice));
        }
        if (advice instanceof  AfterReturningAdvice){
            methodInterceptors.add(new AfterReturningAdviceInterceptor((AfterReturningAdvice)advice));
        }

        if (advice instanceof  MethodInterceptor){
            methodInterceptors.add((MethodInterceptor)advice);
        }

    }
}
