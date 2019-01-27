package aop;

import factory.BeanFactory;
import factory.BeanFactoryAware;
import factory.FactoryBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aopalliance.aop.Advice;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProxyFactoryBean extends ProxyFactory implements FactoryBean, BeanFactoryAware {
    private boolean singleton = true;
    private BeanFactory beanFactory;
    private String afterReturningAdvice;
    private String beforeAdvice;
    private String methodInterceptor;

    @Override
    public Object getObject() {
        initialInterceptorChain();
        return super.getProxy();
    }

    private void initialInterceptorChain() {
        addAdvice((Advice) beanFactory.getBean(afterReturningAdvice));
        addAdvice((Advice) beanFactory.getBean(beforeAdvice));
        addAdvice((Advice) beanFactory.getBean(methodInterceptor));
//        interceptorNames.forEach(interceptorNames -> addAdvice((Advice) beanFactory.getBean(interceptorNames)));
    }

    @Override
    public Class<?> getObjectType() {
        return super.getClass();
    }

    @Override
    public boolean isSingleton() {
        return singleton;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
