import aop.ProxyFactory;
import aopAdvice.PerformerAfterAdvice;
import aopAdvice.PerformerBeforeAdvice;
import aopEntity.Performer;
import aopEntity.Singer;
import context.ApplicationContext;
import context.ClassPathXmlApplicationContext;
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

    @org.junit.Test
    public void test() {
        ProxyFactory proxyFactory = new ProxyFactory();     // 创建代理工厂
        proxyFactory.setTarget(new Singer("aaa","bbb"));         // 射入目标类对象
        proxyFactory.addAdvice(new PerformerBeforeAdvice()); // 添加前置增强
        proxyFactory.addAdvice(new PerformerAfterAdvice());  // 添加后置增强

        Performer singer = (Performer) proxyFactory.getProxy(); // 从代理工厂中获取代理
        singer.perform();
    }
}
