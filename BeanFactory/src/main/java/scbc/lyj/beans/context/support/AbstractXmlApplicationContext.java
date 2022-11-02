package scbc.lyj.beans.context.support;

import scbc.lyj.beans.factory.support.DefaultListableBeanFactory;
import scbc.lyj.beans.factory.support.XmlBeanDefinitionReader;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader =
                new XmlBeanDefinitionReader(beanFactory,this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }
    protected abstract String[] getConfigLocations();
}
