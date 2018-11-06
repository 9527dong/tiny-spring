package bean;

public class BeanDefinition {
    private Object o;
    private String beanName;

    public BeanDefinition(Object o) {
        this.o = o;
    }
    public BeanDefinition() {
    }
    public Object getBean() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class classType = Class.forName(beanName);
        o = classType.newInstance();
        return o;
    }

    public void setBeanClassName(String s) {
        this.beanName = s;
    }
}
