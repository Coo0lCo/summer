package scbc.lyj.beans.core.io;

import cn.hutool.core.lang.Assert;
import scbc.lyj.beans.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public class ClassPathResource implements Resource{
    private final String path;
    private ClassLoader classLoader;
    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }
    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (Objects.isNull(inputStream))
            throw new FileNotFoundException(this.path+" cannot be opened because it does not exist");
        return inputStream;
    }
}
