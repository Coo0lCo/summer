package scbc.lyj.beans.factory.support;

import scbc.lyj.beans.factory.BeanFactory;
import scbc.lyj.beans.factory.BeansException;
import scbc.lyj.beans.factory.config.BeanDefinition;
import scbc.lyj.beans.factory.config.ConfigurableBeanFactory;

import java.util.Objects;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) {
        Object object = getSingleton(name);
        return Objects.isNull(object) ? creatBean(name,getBeanDefinition(name)) : object;
    }
    @Override
    public Object getBean(String name, Object... args) {
        Object object = getSingleton(name);
        return Objects.isNull(object) ? creatBean(name,getBeanDefinition(name),args) : object;
    }
    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return null;
    }

    protected abstract Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
