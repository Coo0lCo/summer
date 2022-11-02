package scbc.lyj.beans.factory.support;

import scbc.lyj.beans.factory.BeansException;
import scbc.lyj.beans.factory.ConfigurableListableBeanFactory;
import scbc.lyj.beans.factory.config.BeanDefinition;
import scbc.lyj.beans.factory.config.BeanPostProcessor;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private final Map<String,BeanPostProcessor> beanPostProcessorMap = new ConcurrentHashMap<>(256);
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (Objects.isNull(beanDefinition))
            throw new BeansException("该bean没有找到，可能尚未注册");
        return beanDefinition;
    }
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }
    @Override
    public Boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }
    @Override
    public void preInstantiateSingletons() {
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> beanClazz) {
        return null;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        return null;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        return null;
    }

}
