package bean;

import factory.BeanFactory;
import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
public class BeanDefinition {
    private Object o;
    private String beanClassName;
    private String beanId;
    private PropertyValues propertyValues;
    private ConstructorValues constructorValues;
    private BeanFactory beanFactory;

    private List<String> dependOnClassList = new ArrayList<>();

    public ConstructorValues getConstructorValues() {
        return constructorValues;
    }

    public void setConstructorValues(ConstructorValues constructorValues) {
        this.constructorValues = constructorValues;
    }

    public BeanDefinition(Object o) {
        this.o = o;
    }

    public BeanDefinition() {
    }

    public Object getBean(BeanFactory beanFactory) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        this.beanFactory = beanFactory;
        Class clazz = Class.forName(beanClassName);
        Constructor constructor = clazz.getConstructor(getClassArray());
        o = constructor.newInstance(getObjectArray());
        propertyValues.getPropertyValueList().forEach(propertyValue -> {
            try {
                Field field = clazz.getDeclaredField(propertyValue.getField());
                field.setAccessible(true);
                field.set(o, propertyValue.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

        });

        beanFactory.cacheBean(beanId, o);

        return o;
    }

    private Class[] getClassArray() {
        List<Class> classes = new ArrayList<>();
        constructorValues.getConstructorValueList().forEach(constructorValue -> {
            if (constructorValue.getRef() != null){
                String className = beanFactory.getBeanType(constructorValue.getRef());
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else if (constructorValue.getValue() != null){
                classes.add(String.class);
            }
        });
        return classes.toArray(new Class[constructorValues.getConstructorValueList().size()]);
//        return constructorValues.getConstructorValueList().stream().map(constructorValue -> {
//
//        }).map(refName-> beanFactory.getBeanType(refName)).map(className -> {
//
//            try {
//                return Class.forName(className);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            return Collections.EMPTY_LIST;
//        }).collect(Collectors.toList()).toArray(new Class[constructorValues.getConstructorValueList().size()]);
    }

    private boolean isHasDuplicateData(List list) {
        HashSet hashSet = new HashSet<>(list);

        return list.size() != hashSet.size();
    }

    private Object[] getObjectArray(){
        List<Object> objects = new ArrayList<>();
        constructorValues.getConstructorValueList().forEach(constructorValue -> {
            if (constructorValue.getRef() != null){

                    objects.add(beanFactory.getBean(constructorValue.getRef()));

            }else if (constructorValue.getValue() != null){
                objects.add(constructorValue.getValue());
            }
        });
        return objects.toArray();
    }
}
