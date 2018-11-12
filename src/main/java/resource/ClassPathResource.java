package resource;

import bean.BeanDefinition;
import bean.PropertyValues;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 此类通过类路径获取文件的 inputStream
 */
public class ClassPathResource {
    private InputStream inputStream;
    private PropertyValues propertyValues = new PropertyValues();
    private BeanDefinition beanDefinition = new BeanDefinition();

    public ClassPathResource(String fileName) throws FileNotFoundException {
        inputStream = new FileInputStream(fileName);
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public void setBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinition = beanDefinition;
    }

}
