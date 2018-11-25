package bean;

import factory.BeanFactory;
import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
//        List classList = constructorValues.getConstructorValueList().stream().map(ConstructorValue::getRef).map(refName -> {
//            try {
//                return beanFactory.getBeanType(refName);
//            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//            return "";
//        }).map(className -> {
//
//            try {
//                return Class.forName(className);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            return Collections.EMPTY_LIST;
//        }).collect(Collectors.toList());

//        if (isHasDuplicateData(classList)) {
//            System.out.println("循环引用");
//        }
//        return (Class[]) classList.toArray();
        return constructorValues.getConstructorValueList().stream().map(ConstructorValue::getRef).map(refName-> beanFactory.getBeanType(refName)).map(className -> {

            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return Collections.EMPTY_LIST;
        }).collect(Collectors.toList()).toArray(new Class[constructorValues.getConstructorValueList().size()]);
    }

    private boolean isHasDuplicateData(List list) {
        HashSet hashSet = new HashSet<>(list);

        return list.size() != hashSet.size();
    }

    private Object[] getObjectArray(){
        return constructorValues.getConstructorValueList().stream().map(ConstructorValue::getRef).map(refName -> {
            try {
                return beanFactory.getBean(refName);
            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList()).toArray();

    }
}
