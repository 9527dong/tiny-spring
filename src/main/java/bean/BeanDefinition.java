package bean;

import factory.BeanFactory;
import factory.BeanFactoryAware;
import factory.FactoryBean;
import lombok.Data;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Data
public class BeanDefinition {
    private Object object;
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

    public BeanDefinition(Object object) {
        this.object = object;
    }

    public BeanDefinition() {
    }

    public Object getBean(BeanFactory beanFactory) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        this.beanFactory = beanFactory;
        Class clazz = Class.forName(beanClassName);
        Constructor constructor = clazz.getConstructor(getClassArray());
        object = constructor.newInstance(getObjectArray());
        propertyValues.getPropertyValueList().forEach(propertyValue -> {
            Object value;
            if (propertyValue.getRef() != null) {
                value = beanFactory.getBean(propertyValue.getRef());
            }else{
                value = propertyValue.getValue();
            }
            try {
                PropertyUtils.setProperty(object, propertyValue.getField(), value);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });

        try {
            postProcessBeanFactory(object);
        } catch (Exception ignored) {

        }
        if (object instanceof FactoryBean) {
            return this.beanFactory.getObjectFromFactoryBean((FactoryBean) object);
        }

        beanFactory.cacheBean(beanId, object);

        return object;
    }
    private static Object getPrivateStatic(Class clazz, String f) throws Exception {
        try {
            Field field = clazz.getDeclaredField(f);
            field.setAccessible(true);
            return field.get(null);
        }
        catch (NoSuchFieldException e) {
            // Throw a more helpful exception.
            throw new NoSuchFieldException(
                    "Could not find field named '" + f + "' in class '" + clazz +
                            "'.  All fields: " + Arrays.asList(clazz.getDeclaredFields()));
        }
    }
    private void postProcessBeanFactory(Object o) throws Exception {
        if (o instanceof BeanFactoryAware){
            ((BeanFactoryAware) o).setBeanFactory(beanFactory);
        }
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
