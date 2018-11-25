import context.ApplicationContext;
import context.ClassPathXmlApplicationContext;
import entity.HelloWorldA;

public class SpringApplicationContextTest {

    /**
     * 通过读取xml文件来进行bean的读取
     */
    @org.junit.Test
    public void test3() throws Exception {
        // 1.初始化beanfactory

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanFactoryTest.xml");

        // 5.获取bean
        HelloWorldA helloWorldService = (HelloWorldA) applicationContext.getBean("helloWorldA");
        System.out.println(helloWorldService);
    }
}
