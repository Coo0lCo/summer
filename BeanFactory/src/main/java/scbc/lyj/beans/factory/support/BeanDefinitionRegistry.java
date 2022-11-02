package scbc.lyj.beans.factory.support;


import scbc.lyj.beans.factory.config.BeanDefinition;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    Boolean containsBeanDefinition(String beanName);
}
