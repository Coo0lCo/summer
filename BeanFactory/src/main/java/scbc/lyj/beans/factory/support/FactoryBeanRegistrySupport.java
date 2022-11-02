package scbc.lyj.beans.factory.support;

import scbc.lyj.beans.factory.BeansException;
import scbc.lyj.beans.factory.FactoryBean;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/30
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{
    private final Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<>(256);
    //从缓存里去拿factoryBeanObject
    protected Object getCacheObjectFactoryBean(String beanName){
        Object object = factoryBeanObjectCache.get(beanName);
        return Objects.isNull(object) ? null : object;
    }
    //先判断这个FactoryBean是不是单例的，可以吧FactoryBean当成一个特殊Bean就行
    protected Object getObjectFromFactoryBean(FactoryBean<?> factoryBean,String beanName){
        if (factoryBean.isSingleton()){
            //是单例的就去缓存拿来看看
            Object object = factoryBeanObjectCache.get(beanName);
            if (object == null){
                //如果首次拿，没有缓存，就先存入缓存，再返回一个object
                object = doGetObjectFromFactoryBean(factoryBean,beanName);
                factoryBeanObjectCache.put(beanName,Objects.isNull(object) ? null : object);
            }
            return Objects.isNull(object) ? null : object;
        }else {
            //非单例的就直接再拿一次
            return doGetObjectFromFactoryBean(factoryBean,beanName);
        }
    }
    //包装了一下，就是factoryBean.getObject();
    private Object doGetObjectFromFactoryBean(FactoryBean<?> factoryBean,final String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }

}

