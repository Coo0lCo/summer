package scbc.lyj.beans.factory.support;

import scbc.lyj.beans.factory.DisposableBean;
import scbc.lyj.beans.factory.config.ConfigurableBeanFactory;
import scbc.lyj.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public class DefaultSingletonBeanRegistry
        implements SingletonBeanRegistry , ConfigurableBeanFactory {
    private final Map<String,Object> singletonObjects = new ConcurrentHashMap<>(256);
    private final Map<String, DisposableBean> disposableBeanMap = new ConcurrentHashMap<>(256);
    protected void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
    @Override
    public void registerDisposableBean(String beanName, DisposableBeanAdapter disposableBeanAdapter) {
        disposableBeanMap.put(beanName,disposableBeanAdapter);
    }
    @Override
    public void destroySingletons() {

    }

    @Override
    public Object getBean(String name) {
        return null;
    }

    @Override
    public Object getBean(String name, Object... args) {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return null;
    }
}
