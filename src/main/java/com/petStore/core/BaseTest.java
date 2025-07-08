package com.petStore.core;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    File file = new File("/src/main/java/com/petStore/Utils/config.properties");

    @BeforeClass
    public void readPropertyRead(){

        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
