package factory;

import bean.BeanDefinition;

public interface BeanFactory {
    public void registerBeanDefinition(String className, BeanDefinition beanDefinition) ;
    public void cacheBean(String className, Object object);

    public Object getBean(String beanName);
    public String getBeanType(String beanId) ;

    Object getObjectFromFactoryBean(FactoryBean object);
}
