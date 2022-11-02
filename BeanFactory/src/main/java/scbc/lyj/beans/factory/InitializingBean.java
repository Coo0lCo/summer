package scbc.lyj.beans.factory;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/29
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
