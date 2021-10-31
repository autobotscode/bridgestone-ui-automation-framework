package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

    private static final ReadPropertyFile configProperties = new ReadPropertyFile();
    Properties properties;

    public ReadPropertyFile() {
        properties = new Properties();
        InputStream configFile = ReadPropertyFile.class.getClassLoader().getResourceAsStream("config.properties");


        try {
            properties.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ReadPropertyFile getInstance() {
        return configProperties;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }


    public Object clone()
            throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }




}
