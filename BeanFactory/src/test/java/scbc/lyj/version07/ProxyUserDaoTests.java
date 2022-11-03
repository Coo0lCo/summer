package scbc.lyj.version07;

import org.junit.Test;
import scbc.lyj.beans.utils.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/6/1
 */
public class ProxyUserDaoTests {
    @Test
    public void test(){
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "小傅哥");
            hashMap.put("10002", "八杯水");
            hashMap.put("10003", "阿毛");
            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        PUserDao userDao =
                (PUserDao) Proxy.
                        newProxyInstance(ClassUtils.getDefaultClassLoader(),
                                new Class[]{PUserDao.class}, handler);
        userDao.printData("10001");
    }

}
