package factory;

import bean.BeanDefinition;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    Map<String, BeanDefinition> beanDefinitionMap;
    Map<String, Object> beanMap = new HashMap<>();

    public BeanFactory(){
        beanDefinitionMap = new HashMap();
    }
    public void registerBeanDefinition(String className, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(className, beanDefinition);
    }
    public void cacheBean(String className, Object object) {
        beanMap.put(className, object);
    }

    public Object getBean(String beanName) throws IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object beanInstance = beanMap.get(beanName);
        if (beanInstance == null){
            return beanDefinitionMap.get(beanName).getBean(this);
        }
        return beanInstance;
    }
    public String getBeanType(String beanId) throws IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
        return beanDefinition.getBeanClassName();
    }
}
