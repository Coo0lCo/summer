package scbc.lyj.beans.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author SCBC_LiYongJie
 * {@code @Date} 2022/5/23
 */
public class FileSystemResource implements Resource{
    private final File file;
    private final String path;

    public FileSystemResource(File file, String path) {
        this.file = file;
        this.path = path;
    }

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(file.toPath());
    }

    public final String getPath() {
        return path;
    }

}
