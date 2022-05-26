package scbc.lyj.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import scbc.lyj.beans.factory.BeansException;
import scbc.lyj.beans.factory.PropertyValue;
import scbc.lyj.beans.factory.PropertyValues;
import scbc.lyj.beans.factory.config.AutowireCapableBeanFactory;
import scbc.lyj.beans.factory.config.BeanDefinition;
import scbc.lyj.beans.factory.config.BeanPostProcessor;
import scbc.lyj.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        Object bean;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            //属性填充 / 依赖注入
            applyPropertyValue(beanName,bean,beanDefinition);
            //
            bean = initializeBean(beanName,bean,beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args){
        Class<?> clazz = beanDefinition.getBeanClass();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        //显然我们发现这咋过滤方式相当简易，还未对类型做比对，这样传入参数如果类型对不上号就完蛋！后期版本填坑！
        Constructor<?> constructorToUse = Arrays.stream(constructors)
                //只做了数量比对来找到有参构造器，显然不太行
                .filter(constructor -> (args != null && constructor.getParameterTypes().length == args.length))
                .collect(Collectors.toList()).get(0);

        return instantiationStrategy.instantiate(beanDefinition,beanName,constructorToUse,args);
    }

    protected void applyPropertyValue(String beanName,Object bean,BeanDefinition beanDefinition){
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            PropertyValue[] propertyArray = propertyValues.getPropertyValues();
            Arrays.stream(propertyArray).forEach(propertyValue -> {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    //如果要注入的属性是Bean，就得先去构造Bean实例（这里简化处理了），不考虑循坏依赖的问题
                    value = getBean(beanReference.getBeanName());
                }
                //这里可以基于反射实现，也可以直接用工具包来填充进去
                BeanUtil.setFieldValue(bean,name,value);
            });
        }catch (Exception e){
            throw new BeansException("Error setting propertyValues :"+beanName);
        }
    }
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);
        invokeInitMethods(beanName,wrappedBean,beanDefinition);
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
        return wrappedBean;
    }
    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

    }
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        return null;
    }
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        return null;
    }
}

