package scbc.lyj.beans.factory;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/30
 */
public interface BeanClassLoaderAware extends Aware{
    void setBeanClassLoader(ClassLoader classLoader);
}
