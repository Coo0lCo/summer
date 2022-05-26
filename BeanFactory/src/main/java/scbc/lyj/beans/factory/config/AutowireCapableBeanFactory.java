package scbc.lyj.beans.factory.config;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public interface AutowireCapableBeanFactory {
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName);
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName);
}
