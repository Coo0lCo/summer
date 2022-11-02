package scbc.lyj.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import scbc.lyj.beans.factory.*;
import scbc.lyj.beans.factory.config.AutowireCapableBeanFactory;
import scbc.lyj.beans.factory.config.BeanDefinition;
import scbc.lyj.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
    //关联Aware感知容器接口，具体的子类实现不管，在instanceof判断就行
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
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);
        if (beanDefinition.isSingleton())
            addSingleton(beanName,bean);
        return bean;
    }
    protected void registerDisposableBeanIfNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
        //如果是单例的Bean则不执行销毁工作，会存储在内存中
        if (beanDefinition.isSingleton())return;

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName()))
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
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
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        //首先来看看Bean是否实现了BeanFactory感知接口,然后。。。。
        if (bean instanceof BeanFactoryAware)
            ((BeanFactoryAware)bean).setBeanFactory(this);

        if (bean instanceof BeanClassLoaderAware)
            ((BeanClassLoaderAware)bean).setBeanClassLoader(null);

        if (bean instanceof BeanNameAware)
            ((BeanNameAware)bean).setBeanName(beanName);

        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);
        invokeInitMethods(beanName,wrappedBean,beanDefinition);
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
        return wrappedBean;
    }
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        //如果实现了InitializingBean接口就直接调用afterPropertiesSet
        if (bean instanceof InitializingBean)
            ((InitializingBean)bean).afterPropertiesSet();
        //如果没实现接口，但是指定了init方法Class Name 就直接反射invoke init method
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method initMethod =  beanDefinition.getBeanClass().getMethod(initMethodName);
            initMethod.invoke(bean);
        }
    }
}

