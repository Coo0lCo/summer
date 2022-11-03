package scbc.lyj.beans.utils;

import cn.hutool.core.lang.Assert;
import scbc.lyj.beans.factory.BeansException;

import java.lang.reflect.Field;

/**
 * @author Li Yong Jie
 * data: 2022/11/3
 * time: 15:27
 *          通过反射给obj对象内成员变量名字为name的属性注入value
 */
public class DefaultBeanUtils {

    public static void setFieldValue(
             Object obj, String name, Object value)throws BeansException {
        Class<?> clazz = obj.getClass();
        Field declaredField = null;
        try {
            declaredField = clazz.getDeclaredField(name);
            declaredField.setAccessible(true);  //开启访问权限
            declaredField.set(obj,value);   //注入属性
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new BeansException("Error setting propertyValues :"+ name);
        }
    }
}
