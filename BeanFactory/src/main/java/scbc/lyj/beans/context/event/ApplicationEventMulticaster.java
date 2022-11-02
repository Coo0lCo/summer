package scbc.lyj.beans.context.event;

import scbc.lyj.beans.context.ApplicationEvent;
import scbc.lyj.beans.context.ApplicationListener;

public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> listener);
    void removeApplicationListener(ApplicationListener<?> listener);
    //广播事件
    void multicastEvent(ApplicationEvent event);
}
