package com.petStore.Utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertionUtil {

    SoftAssert softAssert;

    public SoftAssertionUtil(){
        softAssert = new SoftAssert();
    }

    public void assertTrue(boolean condition, String message){
        try {
            softAssert.assertTrue(condition, message);
        } catch (AssertionError e) {
            throw new RuntimeException(e);
        }

    }


    public void assertEquals(Object actual,Object expected, String message){
        try {
            softAssert.assertEquals(actual, expected, message);
        }catch (AssertionError e){
            softAssert.fail(message);
        }

    }

    public void assertNotEquals(Object actual,Object expected, String message){
        try {
            softAssert.assertEquals(actual, expected, message);
        }catch (AssertionError e){
            softAssert.fail(message);
        }
    }
    public void assertAll(){
        softAssert.assertAll();

    }
}
