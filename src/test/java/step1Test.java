import bean.BeanDefinition;
import bean.PropertyValue;
import bean.PropertyValues;
import entity.HelloWorldService;
import factory.AutowireCapableBeanFactory;
import factory.BeanFactory;
import org.junit.Test;

public class step1Test {

    /**
     * tag2测试
     */
    @Test
    public void test2() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        // 1.初始化beanfactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        // 2.bean定义
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("entity.HelloWorldService");

        // 3.设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("text", "Hello World!"));
        beanDefinition.setPropertyValues(propertyValues);

        // 4.生成bean
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 5.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
