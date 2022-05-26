package scbc.lyj.beans.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        //必须检查location的前缀，如果带类路径就直接认为是ClassPath的Resource
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        try {
            //然后默认为url链接，如果报异常，连接不到，就认为是一个系统文件路径
            URL url = new URL(location);
            return new UrlResource(url);
        }catch (MalformedURLException e){
            return new FileSystemResource(location);
        }
    }
}
