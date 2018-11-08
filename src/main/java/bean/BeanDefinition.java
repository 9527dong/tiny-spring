package bean;

import java.lang.reflect.Field;

public class BeanDefinition {
    private Object o;
    private String beanName;
    private PropertyValues propertyValues;

    public BeanDefinition(Object o) {
        this.o = o;
    }
    public BeanDefinition() {
    }
    public Object getBean() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class clazz = Class.forName(beanName);
        o = clazz.newInstance();
        propertyValues.getPropertyValueList().forEach(propertyValue->{
            try {
                Field field = clazz.getDeclaredField(propertyValue.getField());
                field.setAccessible(true);
                field.set(o,propertyValue.getValue());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        });

        return o;
    }

    public void setBeanClassName(String s) {
        this.beanName = s;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
