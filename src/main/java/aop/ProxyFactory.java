package aop;

import lombok.Data;
import org.aopalliance.aop.Advice;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProxyFactory{
    List<MethodBeforeAdvice> beforeAdvices = new ArrayList<>();
    List<AfterReturningAdvice> afterReturningAdvices = new ArrayList<>();
    List<Advice> aroundAdvices = new ArrayList<>();

    Object target;
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
            beforeAdvices.add((MethodBeforeAdvice)advice);
        }
        if (advice instanceof  AfterReturningAdvice){
            afterReturningAdvices.add((AfterReturningAdvice)advice);
        }
        aroundAdvices.add(advice);
    }
}
