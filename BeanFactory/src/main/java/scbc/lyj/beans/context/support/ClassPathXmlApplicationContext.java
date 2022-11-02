package scbc.lyj.beans.context.support;

import scbc.lyj.beans.factory.support.DisposableBeanAdapter;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 *
 *      提供xml的加载路径的配置
 *      Class Path Xml Locations
 *
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    private String[] locations;
    public ClassPathXmlApplicationContext() {
    }
    public ClassPathXmlApplicationContext(String[] locations) {
        this.locations = locations;
    }
    public ClassPathXmlApplicationContext(String locations) {
        this.locations = new String[]{locations};
    }
    @Override
    protected String[] getConfigLocations() {
        return locations;
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

    @Override
    public void registerDisposableBean(String beanName, DisposableBeanAdapter disposableBeanAdapter) {

    }

    @Override
    public void destroySingletons() {

    }
}
