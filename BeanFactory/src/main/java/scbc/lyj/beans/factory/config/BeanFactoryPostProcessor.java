package scbc.lyj.beans.factory.config;

import scbc.lyj.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 *      BeanFactoryPostProcessor在BeanFactory初始化之后
 *      Bean实例化之前，对Bean的定义信息增强，
 *      在后续的Bean实例化之前完成
 *      实现该接口进行增强逻辑
 */
public interface BeanFactoryPostProcessor {
    /**
     * 增强业务逻辑接口
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory);

}
