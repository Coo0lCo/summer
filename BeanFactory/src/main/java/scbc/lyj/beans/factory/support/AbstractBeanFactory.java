package scbc.lyj.beans.factory.support;

import scbc.lyj.beans.factory.BeansException;
import scbc.lyj.beans.factory.FactoryBean;
import scbc.lyj.beans.factory.config.BeanDefinition;
import scbc.lyj.beans.factory.config.ConfigurableBeanFactory;

import java.util.Objects;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport
        implements ConfigurableBeanFactory {
    @Override
    public Object getBean(String name) {
        Object object = getSingleton(name);
        object = Objects.isNull(object) ? doGetBean(name, null) : object;
        System.out.println(object.getClass().getName());
        return object;
    }
    @Override
    public Object getBean(String name, Object... args) {
        Object object = getSingleton(name);
        return Objects.isNull(object) ? doGetBean(name,args) : object;
    }
    protected <T> T doGetBean(final String name,final Object[] args){
        Object sharedInstance = getSingleton(name);
        if (!Objects.isNull(sharedInstance))
            return (T) getObjectForBeanInstance(sharedInstance,name);

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = creatBean(name,beanDefinition,args);
        return (T) getObjectForBeanInstance(bean,name);
    }
    private Object getObjectForBeanInstance(Object beanInstance , String beanName){
        //首先判断这个Bean是不是一个实现了FactoryBean接口的Bean，如果不是直接返回
        if (!(beanInstance instanceof FactoryBean))return beanInstance;
        //如果是，才执行逻辑
        Object object = getCacheObjectFactoryBean(beanName);
        if (Objects.isNull(object)){
            FactoryBean<?> factoryBean = (FactoryBean<?>)  beanInstance;
            object = getObjectFromFactoryBean(factoryBean,beanName);
        }
        return object;
    }
    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return null;
    }
    protected abstract Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
