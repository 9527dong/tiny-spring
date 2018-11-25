package context;

import factory.XMLBeanFactory;
import org.dom4j.DocumentException;
import resource.ClassPathResource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    public ClassPathXmlApplicationContext(String fileName) throws DocumentException {
        beanFactory = new XMLBeanFactory(new ClassPathResource(fileName));
        refresh();
    }

}
