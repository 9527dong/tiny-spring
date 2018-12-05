package context;

import bean.BeanDefinition;
import factory.BeanFactory;
import factory.XMLBeanFactory;

public class AbstractApplicationContext implements ApplicationContext {

    XMLBeanFactory beanFactory;

    public void setBeanFactory(XMLBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void registerBeanDefinition(String className, BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(className,beanDefinition);
    }

    @Override
    public void cacheBean(String className, Object object) {
        beanFactory.cacheBean(className,object);
    }

    @Override
    public Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    @Override
    public String getBeanType(String beanId) {
        return beanFactory.getBeanType(beanId);
    }

    public void refresh() {
        beanFactory.prepareInstanceBeans();
    }
}
