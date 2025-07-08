package com.Store;

import com.petStore.core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetPetInventoryByStatusTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void validateGetPetInventory(){
        Response resp = given()
                .when()
                .get("/store/inventory");

        int statusCode = resp.statusCode();
        assertEquals(statusCode, StatusCode.SUCCESS.getCode());
        System.out.println(resp.asString());

    }
}
