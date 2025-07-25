package com.petStore.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    static File file = new File("/resources/config.properties");

    public static String propertyReader(String key) {
        String value = null;
        //Inputstream is required while loading into properties

        try {
            InputStream input = new FileInputStream(file);
            // object creation for Property class
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            //getProperty will fetch the value according to the key
             value = prop.getProperty(key);
          
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}
