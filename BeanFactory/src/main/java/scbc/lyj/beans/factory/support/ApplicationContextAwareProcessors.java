package scbc.lyj.beans.factory.support;

import scbc.lyj.beans.context.ApplicationContext;
import scbc.lyj.beans.factory.ApplicationContextAware;
import scbc.lyj.beans.factory.BeansException;
import scbc.lyj.beans.factory.config.BeanPostProcessor;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/30
 */
public class ApplicationContextAwareProcessors implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessors(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware)bean).setApplicationContextAware(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
