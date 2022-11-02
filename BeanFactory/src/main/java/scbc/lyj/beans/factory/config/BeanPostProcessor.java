package scbc.lyj.beans.factory.config;

import scbc.lyj.beans.factory.BeansException;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 *  BeanPostProcessor在BeanFactory初始化之后，
 *  在Bean实例化之后进行增强业务的逻辑
 *  分两种增强：
 *           init 之前 before增强
 *           init 之后 after增强
 */
public interface BeanPostProcessor {

    /**
     * init 之前 before增强
     * @return 增强后的Bean实例
     */
    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    /**
     * init 之后 after增强
     * @return 增强后的Bean实例
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;

}
