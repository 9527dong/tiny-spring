package aop;

import lombok.Data;
import org.aopalliance.aop.Advice;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProxyConfig {
    List<MethodBeforeAdvice> beforeAdvices = new ArrayList<>();
    List<AfterReturningAdvice> afterReturningAdvices = new ArrayList<>();
    List<Advice> aroundAdvices = new ArrayList<>();

    Object target;
}
