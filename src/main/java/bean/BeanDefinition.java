package bean;

public class BeanDefinition {
    private final Object o;

    public BeanDefinition(Object o) {
        this.o = o;
    }
    public Object getBean(){
        return o;
    }
}
