package webapp;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final File PROPS = new File("C:\\projects\\basejava\\config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private Properties props = new Properties();
    private File storageDir;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Properties getProps() {
        return props;
    }

    public  String getUrl() {
        return props.getProperty("db.url");
    }

    public String getUser() {
        return props.getProperty("db.user");
    }

    public String getPassword() {
        return props.getProperty("db.password");
    }
}
