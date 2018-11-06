import bean.BeanDefinition;
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

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("entity.HelloWorldService");
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
