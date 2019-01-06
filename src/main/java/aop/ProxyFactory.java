package aop;

import lombok.Data;
import org.aopalliance.aop.Advice;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProxyFactory {

    List<MethodBeforeAdvice> beforeAdvices = new ArrayList<>();
    List<AfterReturningAdvice> afterReturningAdvices = new ArrayList<>();
    List<Advice> aroundAdvices = new ArrayList<>();

    Object target;
    
    public Object getProxy(){
        return new JdkDynamicAopProxy(this).getProxy();
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
