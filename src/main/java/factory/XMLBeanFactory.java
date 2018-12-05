package factory;

import reader.XMLBeanDefinitionReader;
import resource.ClassPathResource;

import java.lang.reflect.InvocationTargetException;

public class XMLBeanFactory extends AbstractBeanFactory {

    private XMLBeanDefinitionReader xmlBeanDefinitionReader = new XMLBeanDefinitionReader(this);
    public XMLBeanFactory(ClassPathResource classPathResource) {
        super();
        xmlBeanDefinitionReader.registerBeanDefinition(classPathResource);
    }

    public void prepareInstanceBeans(){
        beanDefinitionMap.forEach((beanName,beandefinition)-> {
            try {
                beandefinition.getBean(this);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}
