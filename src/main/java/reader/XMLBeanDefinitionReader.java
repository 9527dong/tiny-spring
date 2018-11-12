package reader;

import bean.BeanDefinition;
import bean.PropertyValue;
import bean.PropertyValues;
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

    public void registerBeanDefinition(ClassPathResource classPathResource) throws DocumentException {
        inputStream = classPathResource.getInputStream();
        doRegisterBeanDefinition(reader.read(inputStream));
    }

    private void doRegisterBeanDefinition(Document document) {
        //获取根节点对象
        Element rootElement = document.getRootElement();

        System.out.println(rootElement.getName());
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

        System.out.println("bean attribute :"+beanId + " "+ beanClass);

        List<Element> propertyList = element.elements("property");
        PropertyValues propertyValues = new PropertyValues();
        propertyList.forEach(property ->{

            String name = property.attributeValue("name");
            String value = property.attributeValue("value");
            propertyValues.addPropertyValue(new PropertyValue(name, value));

        });

        beanDefinition.setPropertyValues(propertyValues);
        beanFactory.registerBeanDefinition(beanId,beanDefinition);
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
