package scbc.lyj.beans.factory.support;

import scbc.lyj.beans.core.io.Resource;
import scbc.lyj.beans.core.io.ResourceLoader;
import scbc.lyj.beans.factory.BeansException;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public interface BeanDefinitionReader{
    //获取bean注册接口实现
    BeanDefinitionRegistry getRegistry();
    //获取资源加载器接口实现
    ResourceLoader getResourceLoader();
    //三种加载Bean的方式
    void loadBeanDefinitions(Resource resource) throws BeansException;
    void loadBeanDefinitions(Resource... resources) throws BeansException;
    void loadBeanDefinitions(String location) throws BeansException;
    void loadBeanDefinitions(String... location) throws BeansException;
}
