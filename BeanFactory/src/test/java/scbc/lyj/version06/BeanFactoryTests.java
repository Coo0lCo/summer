package scbc.lyj.version06;

import cn.hutool.core.io.IoUtil;
import org.junit.Test;
import scbc.lyj.beans.core.io.DefaultResourceLoader;
import scbc.lyj.beans.core.io.Resource;
import scbc.lyj.beans.factory.support.DefaultListableBeanFactory;
import scbc.lyj.beans.factory.support.XmlBeanDefinitionReader;
import scbc.lyj.beans.test.UserService;

import java.io.IOException;
import java.io.InputStream;

/**
 * 首先测试资源能不能读到
 *
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public class BeanFactoryTests {
    private final DefaultResourceLoader resourceLoader =  new DefaultResourceLoader();
    @Test
    public void test_classPath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/main/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
    //链接的文件不好测试，原理一样
    @Test
    public void test_xml(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring-bean.xml");
        UserService userService = (UserService) beanFactory.getBean("userService");

        userService.queryUserInfo();
    }
}
