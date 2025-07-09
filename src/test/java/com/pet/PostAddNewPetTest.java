package com.pet;

import com.petStore.Utils.ExtentReport;
import com.petStore.Utils.JsonReader;
import com.petStore.Utils.SoftAssertionUtil;
import com.petStore.core.BaseTest;
import com.petStore.core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.IMethodInstance;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PostAddNewPetTest extends BaseTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void validateAddNewPet(){
        Response resp = given()
                .header("content-type","application/json")
                .log()
                .all()
                .body("{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}").when()
                .post("/pet").then().extract().response();

        System.out.println(resp.asString());
        int statusCode = resp.statusCode();
        assertEquals(statusCode, StatusCode.SUCCESS.getCode());
        String idStr = resp.jsonPath().getString("id");
        long petId = Long.parseLong(idStr);
        System.out.println(petId);

    }

    @BeforeMethod
    public void setUpReport(Method method) throws IOException {
        String testCaseName = method.getName();
        ExtentReport.extentlog = ExtentReport.extentreport.createTest(testCaseName,method.getAnnotation(Test.class).description());
    }

    @Test
    public void validateAPIForExtraField(){

        String extraValue = JsonReader.getTestData("extraField");
        Response resp = given()
                .header("content-type","application/json")
                .log()
                .all()
                .body("{\"id\":0,\"extraField\":extraValue,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}").when()
                .post("/pet").then().extract().response();

        System.out.println(resp.asString());
        int statusCode = resp.statusCode();
        assertEquals(statusCode, StatusCode.BAD_REQUEST.getCode());
        SoftAssert s = new SoftAssert();
        s.assertTrue(statusCode==200,"this is wrong, debug it");
        SoftAssertionUtil s1 = new SoftAssertionUtil();
        s1.assertTrue(statusCode==400,"11this is wrong, debug it");
       // s.assertAll();
        s1.assertAll();

    }
}
