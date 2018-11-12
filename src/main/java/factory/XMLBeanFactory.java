package factory;

import org.dom4j.DocumentException;
import reader.XMLBeanDefinitionReader;
import resource.ClassPathResource;

public class XMLBeanFactory extends BeanFactory {

    private XMLBeanDefinitionReader xmlBeanDefinitionReader = new XMLBeanDefinitionReader(this);
    public XMLBeanFactory(ClassPathResource classPathResource) throws DocumentException {
        xmlBeanDefinitionReader.registerBeanDefinition(classPathResource);
    }
}
