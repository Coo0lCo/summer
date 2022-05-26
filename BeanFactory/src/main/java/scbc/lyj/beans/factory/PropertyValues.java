package scbc.lyj.beans.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/22
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public PropertyValue[] getPropertyValues(){
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        return propertyValueList
                .stream()
                .filter(propertyValue -> propertyValue.getName().equals(propertyName))
                .collect(Collectors.toList()).get(0);
    }

    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

}
