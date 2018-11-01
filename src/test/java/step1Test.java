import bean.BeanDefinition;
import entity.HelloWorldService;
import factory.BeanFactory;
import org.junit.Test;

public class step1Test {

    @Test
    public void test1(){
        // 1.初始化beanfactory
        BeanFactory beanFactory = newBeanFactory();

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition(new HelloWorldService());
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();

    }
}
