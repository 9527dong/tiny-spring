import entity.HelloWorldService;
import factory.BeanFactory;
import factory.XMLBeanFactory;
import resource.ClassPathResource;

public class SpringTest {

    /**
     * 通过读取xml文件来进行bean的读取
     */
    @org.junit.Test
    public void test3() throws Exception {
        // 1.初始化beanfactory
        BeanFactory beanFactory = new XMLBeanFactory(new ClassPathResource("/Users/wangdong/IdeaProjects/tiny-spring/src/main/resources/beanFactoryTest.xml"));

        // 5.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("myTestBean");
        helloWorldService.helloWorld();
    }
}
