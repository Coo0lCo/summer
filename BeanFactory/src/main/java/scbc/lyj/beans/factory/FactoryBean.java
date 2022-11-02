package scbc.lyj.beans.factory;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/30
 */
public interface FactoryBean <T>{
    T getObject() throws Exception;
    Class<?> getObjectType();
    boolean isSingleton();
}
