package scbc.lyj.beans.factory.config;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
