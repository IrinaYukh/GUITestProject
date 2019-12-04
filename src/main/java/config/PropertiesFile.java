package config;

import java.io.*;
import java.util.Properties;

public class PropertiesFile
{
    static Properties prop = new Properties();
    static String path = System.getProperty("user.dir");


    // this method bring key value from config.properties file

    public static String getProperties(String key)
    {
        String result = null;

        try {
            InputStream input = new FileInputStream(path + "/src/config/config.properties");
            prop.load(input);
            result = prop.getProperty(key);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // this method edit value by its key or add new key/value to the config.properties file

    public static void setProperties (String key, String value)
    {
        try {
            OutputStream output = new FileOutputStream(path + "/src/config/config.properties");
            prop.setProperty(key,value);
            prop.store(output,null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
