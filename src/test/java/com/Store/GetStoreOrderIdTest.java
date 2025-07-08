package com.Store;

import com.petStore.core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * Unit test for simple App.
 */
public class GetStoreOrderIdTest {

    Map<String, String> formParam;
    File fileToUpload;

    @BeforeClass
    public void setUp(){
        fileToUpload = new File("/Users/rohini/Downloads/helloPet.txt");
        formParam = new HashMap<>();
        formParam.put("additionalMetadata","pet");
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }


    @Test
    public void validateGetPurchaseDetailsOrderByID(){

        Response res = given()
                .pathParam("orderId",1234)
                .when()
                .get("/store/order/{orderId}")
                .then()
                .extract().response();

        int statusCode = res.statusCode();
        assertEquals(statusCode,StatusCode.SUCCESS.getCode());

    }

    @Test
    public void schemaValidation(){

        Response res = given()
                .formParams(formParam)
                .multiPart(fileToUpload)
                .when()
                .post("/pet/uploadImage")
                .then()
                .extract().response();

        int statusCode = res.statusCode();
        assertEquals(statusCode,400);
        assertThat(res.jsonPath().get("code"),equalTo(400));
    }
}
