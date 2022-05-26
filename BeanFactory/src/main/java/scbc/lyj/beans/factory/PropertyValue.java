package scbc.lyj.beans.factory;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
