import aop.ProxyFactory;
import aopAdvice.PerformerAfterAdvice;
import aopAdvice.PerformerAroundAdvice;
import aopAdvice.PerformerBeforeAdvice;
import aopEntity.Performer;
import aopEntity.Singer;
import context.ApplicationContext;
import context.ClassPathXmlApplicationContext;
import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

public class SpringAopTest {

    @Test
    public void test2() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-idol.xml");

        Performer performer = (Performer) ctx.getBean("bo");
        performer.perform();
        System.out.println("-----------------------------");
        Performer performer2 = (Performer) ctx.getBean("william");
        performer2.perform();
    }

    /**
     * 测试简单的jdk代理和cglib代理
     */
    @org.junit.Test
    public void test() {
        ProxyFactory proxyFactory = new ProxyFactory();     // 创建代理工厂
        proxyFactory.setTarget(new Singer("aaa","bbb"));         // 射入目标类对象
        proxyFactory.addAdvice(new PerformerBeforeAdvice()); // 添加前置增强
        proxyFactory.addAdvice(new PerformerAfterAdvice());  // 添加后置增强

        Performer singer = (Performer) proxyFactory.getProxy(); // 从代理工厂中获取代理
        singer.perform();
    }

    @org.junit.Test
    public void test3() {
        ProxyFactory proxyFactory = new ProxyFactory();     // 创建代理工厂
        proxyFactory.setTarget(new Singer("aaa","bbb"));         // 射入目标类对象
        proxyFactory.addAdvice(new PerformerAroundAdvice()); // 添加前置增强

        Performer singer = (Performer) proxyFactory.getProxy(); // 从代理工厂中获取代理
        singer.perform();
    }

    @org.junit.Test
    public void testCglib() {
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(Singer.class);
        enhancer.setStrategy(new DefaultGeneratorStrategy() {
            protected byte[] transform(byte[] b) {
                System.out.println("transform");
                return new byte[]{1, 2, 3};
            }
        });
        Object object = enhancer.create();
        System.out.println(object instanceof Singer);
//        enhancer.setSuperclass(TargetObject.class);
//        enhancer.setCallback(new TargetInterceptor());
//        TargetObject targetObject2=(TargetObject)enhancer.create();
//        System.out.println(targetObject2);
//        System.out.println(targetObject2.method1("mmm1"));
//        System.out.println(targetObject2.method2(100));
//        System.out.println(targetObject2.method3(200));

    }
}
