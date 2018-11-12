package factory;

import bean.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    Map<String, BeanDefinition> beanMap;

    public BeanFactory(){
        beanMap = new HashMap();
    }
    public void registerBeanDefinition(String className, BeanDefinition beanDefinition) {
        beanMap.put(className, beanDefinition);
    }

    public Object getBean(String beanName) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        return beanMap.get(beanName).getBean();
    }
}
