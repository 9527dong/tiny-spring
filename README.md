# tiny-spring
本项目是一个精简版的spring，通过自己实现一遍spring来理解spring框架的精华。

## IOC计划
* [x] **最基本的容器:** 使用BeanFactory来获取bean，使用BeanDefinition来封装bean对象。
* [x] **将bean创建放入工厂：** 设置beanClass的名称，通过反射来创建bean。
* [x] **为bean注入属性：** 使用PropertyValue对象来存储bean的属性信息，使用ConstructorValue来存储构造器属性信息。
* [x] **读取xml配置来初始化bean：** 引用XMLBeanDefinitionReader来读取xml中的信息，并将xml中的信息转化为BeanDefinition和PropertyValue
* [x] **实现ApplicationContext：** 自动初始化所有bean


## AOP计划
aop的实现遵守的[aop联盟](http://aopalliance.sourceforge.net/)的规定，所以需要先引入[aopalliance.jar](https://mvnrepository.com/artifact/aopalliance/aopalliance)。
* [x] **实现简单版的jdk和cglib的动态代理：** [jdk动态代理](https://blog.csdn.net/wangdong5678999/article/details/72801623)通过InvocationHandler实现,缺点是必须需要代理的类必须实现了接口才能被jdk代理；[CGLIB](https://github.com/cglib/cglib)是一个基于ASM的字节码生成库，它允许我们在运行时对字节码进行修改和动态生成,CGLIB通过继承方式实现代理。
* [x] **通过ProxyFactoryBean来实现aop和ioc的结合。**
   1. 实现BeanFactoryAware接口，将beanFactory注入到ProxyFactoryBean中
   2. 实现FactoryBean，获取代理对象
* [ ] **实现引入：**对类的功能增强，实现原来类未实现的接口
* [ ] **实现切面功能：**即通知点和通知的结合
* [ ] **实现aop自动代理，扫描 Bean 名称**
* [ ] **接入aspectj，实现注解式配置切面**


