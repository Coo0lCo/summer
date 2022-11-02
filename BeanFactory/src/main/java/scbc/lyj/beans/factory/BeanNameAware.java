package scbc.lyj.beans.factory;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/30
 */
public interface BeanNameAware extends Aware{
    void setBeanName(String beanName);
}
