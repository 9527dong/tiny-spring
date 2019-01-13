package factory;
public interface BeanFactoryAware extends Aware {

	void setBeanFactory(BeanFactory beanFactory) throws Exception;

}