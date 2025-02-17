package api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiConfigReader {
    private static Properties properties;


    private ApiConfigReader(){

    }
    static {
        try{
            String path = "src/main/resources/api.properties";
            FileInputStream fileInputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        }catch (IOException e){
            throw new RuntimeException("File not found");
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key).trim();
    }

}
