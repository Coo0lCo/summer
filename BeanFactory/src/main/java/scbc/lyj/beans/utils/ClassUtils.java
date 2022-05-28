package scbc.lyj.beans.utils;


/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        Thread currentThread = Thread.currentThread();

        return Thread.currentThread().getContextClassLoader();
    }

}
