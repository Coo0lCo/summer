package scbc.lyj.beans.factory;

import scbc.lyj.beans.context.ApplicationContext;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/30
 */
public interface ApplicationContextAware extends Aware{
    void setApplicationContextAware(ApplicationContext applicationContext) throws BeansException;
}
