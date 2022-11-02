package scbc.lyj.beans.context;

import java.util.EventObject;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/6/6
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
