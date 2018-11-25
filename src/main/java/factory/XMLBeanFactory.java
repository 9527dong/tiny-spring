package factory;

import org.dom4j.DocumentException;
import reader.XMLBeanDefinitionReader;
import resource.ClassPathResource;

import java.lang.reflect.InvocationTargetException;

public class XMLBeanFactory extends AbstractBeanFactory {

    private XMLBeanDefinitionReader xmlBeanDefinitionReader = new XMLBeanDefinitionReader(this);
    public XMLBeanFactory(ClassPathResource classPathResource) throws DocumentException {
        super();
        xmlBeanDefinitionReader.registerBeanDefinition(classPathResource);
    }

    public void prepareInstanceBeans(){
        beanDefinitionMap.forEach((beanName,beandefinition)->{
            try {
                beandefinition.getBean(this);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}
