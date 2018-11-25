package factory;

import bean.BeanDefinition;
import exception.BeanCurrentlyInCreationException;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AbstractBeanFactory implements BeanFactory {
    Map<String, BeanDefinition> beanDefinitionMap;
    Map<String, Object> beanMap = new HashMap<>();
    private final Set<String> singletonsCurrentlyInCreation =
            Collections.newSetFromMap(new ConcurrentHashMap<>(16));


    public AbstractBeanFactory(){
        beanDefinitionMap = new HashMap();
    }
    public void registerBeanDefinition(String className, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(className, beanDefinition);
    }
    public void cacheBean(String className, Object object) {
        beanMap.put(className, object);
    }

    public Object getBean(String beanName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object beanInstance = beanMap.get(beanName);
        if (beanInstance == null){
            if (singletonsCurrentlyInCreation.contains(beanName)){
                throw new BeanCurrentlyInCreationException(beanName);
            }
            singletonsCurrentlyInCreation.add(beanName);

            Object bean = beanDefinitionMap.get(beanName).getBean(this);

            singletonsCurrentlyInCreation.remove(beanName);
            return bean;
        }
        return beanInstance;
    }
    public String getBeanType(String beanId){
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
        return beanDefinition.getBeanClassName();
    }


}
