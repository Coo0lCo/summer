package scbc.lyj.beans.factory.config;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
