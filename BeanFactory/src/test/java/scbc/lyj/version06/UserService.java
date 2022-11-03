package scbc.lyj.version06;

import scbc.lyj.beans.test.UserDao;
import scbc.lyj.version07.PUserDao;

import java.util.Arrays;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public class UserService {
    private String id;
    private PUserDao pUserDao;

    public void queryUserInfo() {
        System.out.println("id:"+id);
        pUserDao.printData("10001");
        System.out.println(Arrays.toString(UserDao.hashMap.keySet().toArray()));
    }

}
