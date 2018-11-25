package factory;

import bean.BeanDefinition;

import java.lang.reflect.InvocationTargetException;

public interface BeanFactory {
    public void registerBeanDefinition(String className, BeanDefinition beanDefinition) ;
    public void cacheBean(String className, Object object);

    public Object getBean(String beanName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    public String getBeanType(String beanId) ;
}
