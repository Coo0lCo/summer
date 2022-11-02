package scbc.lyj.beans.context.event;

import scbc.lyj.beans.context.ApplicationContext;
import scbc.lyj.beans.context.ApplicationEvent;

public abstract class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }

}
