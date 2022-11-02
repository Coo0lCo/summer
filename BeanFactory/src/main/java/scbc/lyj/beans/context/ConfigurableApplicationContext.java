package scbc.lyj.beans.context;

import scbc.lyj.beans.factory.BeansException;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh() throws BeansException;
    void registerShutdownHook();
    void close();
}
