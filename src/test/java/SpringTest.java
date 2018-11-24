import entity.HelloWorldA;
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
        BeanFactory beanFactory = new XMLBeanFactory(new ClassPathResource("beanFactoryTest.xml"));

        // 5.获取bean
        HelloWorldA helloWorldService = (HelloWorldA) beanFactory.getBean("helloWorldA");
        System.out.println(helloWorldService);
    }

//    @org.junit.Test
//    public void test4() throws Exception {
//        Class clazz = HelloWorldA.class;
//        Constructor constructor = clazz.getConstructor(new Class[]{HelloWorldB.class});
//        HelloWorldA helloWorldA = (HelloWorldA)constructor.newInstance(new HelloWorldB());
//
//        System.out.println(helloWorldA);
//
//    }
}
