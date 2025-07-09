package com.petStore.Helper;

import java.io.File;
import java.util.Date;
import java.util.Random;

public class BaseTestHelper {
    /*
     * create folder
     */
    public static void CreateFolder(String path)  {
        //File is a class inside java.io package
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();//mkdir is used to create folder
        } else
            System.out.println("Folder already created");
    }

    /*
     * Return current time stamp
     */
    public static String Timestamp() {
        Date now = new Date();
        String Timestamp = now.toString().replace(":", "-");
        return Timestamp;
    }

    public static String generateRandomString(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) { // Change 8 to desired length
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
    return sb.toString();
    }
}
