package scbc.lyj.beans.factory;

import scbc.lyj.beans.factory.config.BeanPostProcessor;

import java.util.Map;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public interface ConfigurableListableBeanFactory extends HierarchicalBeanFactory{
    void preInstantiateSingletons();
    <T> Map<String,T> getBeansOfType(Class<T> beanClazz);
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
