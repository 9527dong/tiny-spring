package context;

import factory.XMLBeanFactory;
import resource.ClassPathResource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    public ClassPathXmlApplicationContext(String fileName)  {
        beanFactory = new XMLBeanFactory(new ClassPathResource(fileName));
        refresh();
    }

}
