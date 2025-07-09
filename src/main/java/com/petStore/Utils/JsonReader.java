package com.petStore.Utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;

public class JsonReader {

    public static String getTestData(String key){
       String value = getJsonData().get(key).toString();
        return value;
    }

    public static JSONObject getJsonData() {

        try {
            // pass the path of testdata.json file
            File file = new File("/resources/test.json");
            //read the file into string
            String json = readFileToString(file, "UTF-8");
            //parse the string into object
            Object obj = new JSONParser().parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
