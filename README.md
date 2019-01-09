# tiny-spring

## AOP计划
* [x] 实现jdk和cglib的动态代理。
* [x] 通过ProxyFactoryBean来实现aop和ioc的结合。
   1. 实现BeanFactoryAware接口，将beanFactory注入到ProxyFactoryBean中
   2. 实现FactoryBean，获取代理对象
* [ ] 实现引入，对类的功能增强，实现原来类未实现的接口
* [ ] 实现切面功能：即通知点和通知的结合
* [ ] 实现aop自动代理，扫描 Bean 名称
* [ ] 接入aspectj，实现注解式配置切面
