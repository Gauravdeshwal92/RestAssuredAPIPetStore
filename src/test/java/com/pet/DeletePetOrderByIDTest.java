package com.pet;

import com.petStore.core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DeletePetOrderByIDTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void validateDeletePetById(){

        Response resp1 = given()
                .header("content-type","application/json")
                .log()
                .all()
                .body("{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}").when()
                .post("/pet").then().extract().response();

        String idStr = resp1.jsonPath().getString("id");
        long petId = Long.parseLong(idStr);

        Response resp = given()
                .log().all()
                .pathParam("petId",petId)
                .when()
                .delete("/pet/{petId}");

        int statusCode = resp.statusCode();
        assertEquals(statusCode, StatusCode.SUCCESS.getCode());

    }
}
