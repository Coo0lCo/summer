package scbc.lyj.beans.context.support;

import scbc.lyj.beans.context.ApplicationEvent;
import scbc.lyj.beans.context.ConfigurableApplicationContext;
import scbc.lyj.beans.core.io.DefaultResourceLoader;
import scbc.lyj.beans.factory.BeansException;
import scbc.lyj.beans.factory.ConfigurableListableBeanFactory;
import scbc.lyj.beans.factory.config.BeanFactoryPostProcessor;
import scbc.lyj.beans.factory.config.BeanPostProcessor;
import scbc.lyj.beans.factory.support.ApplicationContextAwareProcessors;

import java.util.Map;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() throws BeansException {
        //加载BeanFactory，实际上是new一个DefaultListableBeanFactory真正的核心bean工厂实现类
        refreshBeanFactory();
        //获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        /*
            这里贼巧妙运用的bean的扩展接口，也就是BeanPostProcessor，
            让bean在拿的时候，creatBean方法里回调BeanPostProcessor的方法，
            从而让Bean能感知到自己属于哪个ApplicationContext
        */
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessors(this));
        //调用BeanFactoryPostProcessors在Bean信息注册后Bean实例化前进行扩展操作
        invokeBeanFactoryPostProcessors(beanFactory);
        //在Bean实例化之前注册BeanPostProcessors
        registerBeanPostProcessors(beanFactory);
        //实例化Bean
        beanFactory.preInstantiateSingletons();
    }
    @Override
    public void registerShutdownHook() {
        //这个操作在JVM虚拟机关闭之前完成
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }
    @Override
    public void close() {

    }
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory configurableListableBeanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = configurableListableBeanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            //执行BeanFactoryPostprocessors接口实现类对Bean定义信息的扩展增强操作
            beanFactoryPostProcessor.postProcessBeanFactory(configurableListableBeanFactory);
        }
    }
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory configurableListableBeanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = configurableListableBeanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            //注册BeanPostProcessors
            configurableListableBeanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    protected abstract void refreshBeanFactory() throws BeansException;
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public void publishEvent(ApplicationEvent event) {

    }
}
