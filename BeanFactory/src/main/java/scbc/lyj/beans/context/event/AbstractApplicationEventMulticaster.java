package scbc.lyj.beans.context.event;

import scbc.lyj.beans.context.ApplicationEvent;
import scbc.lyj.beans.context.ApplicationListener;
import scbc.lyj.beans.factory.BeanFactory;
import scbc.lyj.beans.factory.BeanFactoryAware;
import scbc.lyj.beans.factory.BeansException;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/6/6
 *      广播事件
 *          广播抽象实现通用工具方法
 */
public abstract class AbstractApplicationEventMulticaster
        implements ApplicationEventMulticaster , BeanFactoryAware {

    private final Set<ApplicationListener<ApplicationEvent>> applicationListeners =
            new LinkedHashSet<>();
    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

//    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
//        LinkedList<ApplicationListener> listeners =
//                new LinkedList<>();
//
//    }
//
//    protected boolean supportEvent(ApplicationListener<ApplicationEvent> applicationListener,
//                                   ApplicationEvent applicationEvent){
//
//    }
    @Override
    public void multicastEvent(ApplicationEvent event) {

    }

    /**
     * 感知BeanFactory
     * @param beanFactory 当前监听器属于哪个BeanFactory
     * @throws BeansException bean定义异常
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
