package scbc.lyj.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import scbc.lyj.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object... args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        return constructor != null ? enhancer.create(constructor.getParameterTypes(),args) : enhancer.create();
    }
}
