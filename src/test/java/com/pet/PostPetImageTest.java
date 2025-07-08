package com.pet;

import com.petStore.core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * Unit test for simple App.
 */
public class PostPetImageTest {

    Map<String, String> formParam;
    File fileToUpload;

    @BeforeClass
    public void setUp(){
        fileToUpload = new File("/Users/rohini/Downloads/helloPet.txt");
        formParam = new HashMap<>();
        formParam.put("additionalMetadata","pet");
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @DataProvider(name = "positiveFileUploadScenarios")
    public Object[][] provideUploadData() {
        return new Object[][]{
                {true},    // Metadata present
                {false},   // metaData is empty

        };
    }

    @Test(dataProvider = "positiveFileUploadScenarios")
        public void uploadPetImageSuccess_200_OK(boolean includeMetadata){

        RequestSpecification request = given()
                .pathParam("petId",12566)
                .multiPart(fileToUpload);

            if (includeMetadata) {
                request.multiPart("additionalMetadata", "Cute dog image");
            }

            Response res = request
                    .when()
                    .post("/pet/{petId}/uploadImage")
                    .then()
                    .statusCode(200)
                    .extract().response();

            System.out.println("metadata=" + includeMetadata);
            System.out.println(res.asString());

            assertThat(res.jsonPath().getInt("code"), is(StatusCode.SUCCESS.getCode()));
            assertThat(res.asString(), containsString("uploaded"));
        }

    @DataProvider(name = "negativeScenarios")
    public Object[][] negativeRequestForFileAndMetaData() {
        return new Object[][]{
                {true},    // Metadata present
                {false},   // metaData is empty

        };
    }

    @Test(dataProvider = "negativeScenarios")
    public void validateUploadPetImageForEmptyFileUploadFieldWithMetaData_error_4xx(boolean metadata){

        RequestSpecification request = given()
                .pathParam("petID",12345)
                .header("Content-Type","multipart/form-data");
        if(metadata){
            request.multiPart("additionalMetadata", "Cute dog image");
        }

        Response res = request.log().all()
                .when()
                .post("/pet/{petID}/uploadImage")
                .then()
                .extract().response();

        System.out.println(res.asString());
        if(res.asString().contains("HTTP ERROR 500")){
            assertThat(res.getBody().asString(), containsString("text/html"));
            System.out.println("Non-JSON response received:");
        } else if (res.statusCode()==400) {
            assertEquals(res.jsonPath().get("type"),("unknown"));
        } else {
            fail("Expected JSON response but got something else.");
        }

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
