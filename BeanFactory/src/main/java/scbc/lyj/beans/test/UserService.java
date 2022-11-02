package scbc.lyj.beans.test;

import java.util.Arrays;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public class UserService {
    private String id;
    private UserDao userDao;
    public void queryUserInfo() {
        System.out.println("id:"+id);
        System.out.println(userDao.queryUserName("10001"));
        System.out.println(Arrays.toString(UserDao.hashMap.keySet().toArray()));
    }

}
