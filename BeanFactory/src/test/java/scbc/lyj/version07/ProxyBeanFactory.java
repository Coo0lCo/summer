package scbc.lyj.version07;

import scbc.lyj.beans.factory.FactoryBean;
import scbc.lyj.beans.utils.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyBeanFactory implements FactoryBean<UserDao> {
    @Override
    public UserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "小傅哥");
            hashMap.put("10002", "八杯水");
            hashMap.put("10003", "阿毛");
            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (UserDao) Proxy.
                newProxyInstance(ClassUtils.getDefaultClassLoader(),
                        new Class[]{UserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
