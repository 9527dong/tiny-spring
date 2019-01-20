import aop.ProxyFactory;
import aopAdvice.PerformerAfterAdvice;
import aopAdvice.PerformerBeforeAdvice;
import aopEntity.Apology;
import aopEntity.Performer;
import aopEntity.Singer;
import context.ApplicationContext;
import context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class SpringAopTest {
    /**
     * 测试aspectj
     */
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

    /**
     * 测试ProxyFactoryBean
     */
    @org.junit.Test
    public void test3() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("ProxyFactoryBean.xml");

        Performer performer = (Performer) ctx.getBean("proxyFactoryBean");

        performer.perform();
    }
    /**
     * 测试DelegatingIntroductionInterceptor
     */
    @org.junit.Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("DelegatingIntroductionInterceptor.xml");
        Singer performer = (Singer) context.getBean("proxyFactoryBean");
        performer.perform();
        Apology apology = (Apology) context.getBean("proxyFactoryBean");
        apology.saySorry("haha");

    }
}
