package com.Store;

import com.petStore.core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PostPlaceOrderTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void validateCreateNewOrder_200OK(){
        Response resp = given()
                .header("content-type","application/json")
                .log()
                .all()
                .body("{\"id\":0,\"petId\":0,\"quantity\":0,\"shipDate\":\"2025-07-08T12:44:23.290Z\",\"status\":\"placed\",\"complete\":true}")
                .when()
                .post("/store/order");

        String idStr = resp.jsonPath().getString("id");
        long orderId = Long.parseLong(idStr);

        int statusCode = resp.statusCode();
        assertEquals(statusCode, StatusCode.SUCCESS.getCode());

    }
}
