package api;

import entities.Order;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class EndPoints {
    private final String baseUrl = "https://petstore.swagger.io";
    private final RequestSpecification requestSpec;
    EncoderConfig encoderConfig = EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false);

    private Response response;

    public EndPoints() {
        RestAssured.baseURI = baseUrl;
        requestSpec = given().urlEncodingEnabled(false)
                .contentType("application/json")
                .config(RestAssuredConfig.config().encoderConfig(encoderConfig))
                .accept("application/json")
                .log().all();
    }

    public Response getResponse() {
        return response;
    }

    public void authenticateUser(String accessToken) {
        requestSpec.header("Authorization", "Bearer " + accessToken);
    }


    public Response postOrder(Order order) {
        return given().spec(requestSpec).
                body(order).
                log().all().
                post(RequestPaths.postOrder()).
                then().
                log().all().
                extract().response();
    }

    public Response getOrder(String path, Object orderId) {
        return given().spec(requestSpec).
                log().all().
                get(path, orderId).
                then().
                log().all().
                extract().response();
    }

    public List<Integer> searchForPet(String path, String searchCriteria) {
        return given().spec(requestSpec).
                log().all().
                queryParam("status", searchCriteria).
                get(path).
                then().
                log().all().
                extract().response().path("findAll{it.id<100L}.id");

    }

    public Response deleteOrder(String deleteOrderPath, Object orderId) {
        return given().spec(requestSpec).
                log().all().
                pathParam("orderId", orderId).
                delete(deleteOrderPath).
                then().
                log().all().
                extract().response();
    }
}

