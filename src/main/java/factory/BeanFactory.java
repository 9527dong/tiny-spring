package factory;

import bean.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    Map<String, BeanDefinition> beanMap;

    public BeanFactory(){
        beanMap = new HashMap();
    }
    public void registerBeanDefinition(String helloWorldService, BeanDefinition beanDefinition) {
        beanMap.put(helloWorldService, beanDefinition);
    }

    public Object getBean(String beanName) {
        return beanMap.get(beanName).getBean();
    }
}
