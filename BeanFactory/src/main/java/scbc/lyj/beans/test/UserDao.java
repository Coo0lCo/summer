package scbc.lyj.beans.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public class UserDao {
    public final static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "uzi");
        hashMap.put("10002", "viper");
        hashMap.put("10003", "meiko");
    }

    public String queryUserName(String id) {
        return hashMap.get(id);
    }

}
