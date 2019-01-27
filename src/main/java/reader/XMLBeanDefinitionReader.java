package reader;

import bean.*;
import factory.BeanFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import resource.ClassPathResource;

import java.io.InputStream;
import java.util.List;

/**
 * 负责把通过xml读过来的资源封装成bean后注册到beanFactory
 */
public class XMLBeanDefinitionReader {
    private final BeanFactory beanFactory;
    private InputStream inputStream;

    private SAXReader reader = new SAXReader();

    public XMLBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void registerBeanDefinition(ClassPathResource classPathResource)  {
        inputStream = classPathResource.getInputStream();
        try {
            doRegisterBeanDefinition(reader.read(inputStream));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void doRegisterBeanDefinition(Document document) {
        //获取根节点对象
        Element rootElement = document.getRootElement();

        if (rootElement.getName().equals("beans")){
            List<Element> elementList = rootElement.elements("bean");

            if(elementList != null ){
                elementList.forEach(this::parseBean);
            }

        }
    }

    private void parseBean(Element element) {
        String beanId = element.attributeValue("id");
        String beanClass = element.attributeValue("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(beanClass);
        beanDefinition.setBeanId(beanId);


        List<Element> elements = element.elements();
        PropertyValues propertyValues = new PropertyValues();
        ConstructorValues constructorValues = new ConstructorValues();

        elements.forEach(subElement ->{
            String subElementName = subElement.getName();
            if (subElementName.equals("property")){
                propertyValues.addPropertyValue(parsePropertyElement(subElement));

            }
            if (subElementName.equals("constructor-arg")){
                constructorValues.addConstructorValue(parseConstructorArgElement(subElement));
            }
        });

        beanDefinition.setPropertyValues(propertyValues);
        beanDefinition.setConstructorValues(constructorValues);
        beanFactory.registerBeanDefinition(beanId,beanDefinition);
    }

    private ConstructorValue parseConstructorArgElement(Element subElement) {
        String index = subElement.attributeValue("index");
        String ref = subElement.attributeValue("ref");
        String value = subElement.attributeValue("value");
        String name = subElement.attributeValue("name");

        return new ConstructorValue(index,ref,value,name);
    }

    private PropertyValue parsePropertyElement(Element subElement) {
        String name = subElement.attributeValue("name");
        String value = subElement.attributeValue("value");
        String ref = subElement.attributeValue("ref");
        return new PropertyValue(name, value,ref);
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
