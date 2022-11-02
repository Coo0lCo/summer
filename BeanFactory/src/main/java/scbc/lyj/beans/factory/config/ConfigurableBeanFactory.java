package scbc.lyj.beans.factory.config;

import scbc.lyj.beans.factory.BeanFactory;
import scbc.lyj.beans.factory.support.DisposableBeanAdapter;

public interface ConfigurableBeanFactory extends BeanFactory {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    void registerDisposableBean(String beanName, DisposableBeanAdapter disposableBeanAdapter);
    void destroySingletons();
}
