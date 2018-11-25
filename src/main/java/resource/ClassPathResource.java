package resource;

import java.io.InputStream;

/**
 * 此类通过类路径获取文件的 inputStream
 */
public class ClassPathResource {
    private InputStream inputStream;
    public ClassPathResource(String fileName) {
        inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
