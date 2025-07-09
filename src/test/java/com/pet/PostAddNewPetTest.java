package com.pet;

import com.petStore.Pojo.Pet.Category;
import com.petStore.Pojo.Pet.PostPetRequestBody;
import com.petStore.Pojo.Pet.Tags;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PostAddNewPetTest extends BaseTest {

    PostPetRequestBody postPetRequestBody ;
    Category category;
    Tags tags;

    int id;
    String name;
    @BeforeClass
    public void setUp() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

    }

    @BeforeMethod
    public void setUpReport(Method method) throws IOException {
        postPetRequestBody = new PostPetRequestBody();
        category = new Category();
        tags = new Tags();
        List<Tags> tag = new ArrayList<>();
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("https://example.com/photo1.jpg");
        String testCaseName = method.getName();
        ExtentReport.extentlog = ExtentReport.extentreport.createTest(testCaseName,method.getAnnotation(Test.class).description());
        Random rand = new Random();
        // Generate a random integer
        id = rand.nextInt();
        name = generateRandomString();
        category.setId(id);
        category.setName(name);
        tags.setId(id);
        tags.setName(name);
        tag.add(tags);
        postPetRequestBody.setId(id);
        postPetRequestBody.setName(name);
        postPetRequestBody.setCategory(category);
        postPetRequestBody.setTags(tag);
        postPetRequestBody.setPhotUrls(photoUrls);
    }

    @Test
    public void validateAddNewPetForStatusAvailable_200(){
        String statusAvailable = JsonReader.getTestData("statusAvailable");
        postPetRequestBody.setStatus(statusAvailable);

        Response resp = given()
                .header("content-type","application/json")
                .log()
                .all()
                .body(postPetRequestBody).post("/pet").then().statusCode(200).extract().response();

        System.out.println(resp.asString());
        PostPetRequestBody resp1 = resp.as(PostPetRequestBody.class);
        assertTrue(postPetRequestBody.getStatus().matches(statusAvailable));
        assertEquals(resp.statusCode(), StatusCode.SUCCESS.getCode());
        String idStr = resp.jsonPath().getString("id");
        long petId = Long.parseLong(idStr);
        System.out.println(petId);

    }



    @Test
    public void validateAPIForExtraField(){

        String extraValue = JsonReader.getTestData("extraField");
        Response resp = given()
                .header("content-type","application/json")
                .log()
                .all()
                .body(postPetRequestBody).post("/pet").then().extract().response();

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
