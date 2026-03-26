package dummyjsontestsuite.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public final class DummyJsonRestUtils {
    private DummyJsonRestUtils() {
    }

    public static void assertStatusCode(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }

    public static Response sendRequest(String baseUrl, String endpoint, String method, Map<String, String> headers, Object body) {
        RequestSpecification request = given()
                .baseUri(baseUrl)
                .headers(headers)
                .log().all();

        if (body != null) {
            request.body(body);
        }

        switch (method.toUpperCase()) {
            case "GET":
                return request
                        .when()
                        .get(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();
            case "POST":
                return request
                        .when()
                        .post(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();
            case "PUT":
                return request
                        .when()
                        .put(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();
            case "DELETE":
                return request
                        .when()
                        .delete(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }
}
