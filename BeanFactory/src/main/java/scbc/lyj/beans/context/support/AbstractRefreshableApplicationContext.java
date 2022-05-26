package scbc.lyj.beans.context.support;

import scbc.lyj.beans.factory.BeansException;
import scbc.lyj.beans.factory.ConfigurableListableBeanFactory;
import scbc.lyj.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory defaultListableBeanFactory;
    private DefaultListableBeanFactory creatDefaultListableBeanFactory(){
        return new DefaultListableBeanFactory();
    }
    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = creatDefaultListableBeanFactory();
        loadBeanDefinitions(defaultListableBeanFactory);
        this.defaultListableBeanFactory = defaultListableBeanFactory;
    }
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return defaultListableBeanFactory;
    }
}
