package dummyjsontestsuite.auth;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public final class DummyJsonRestUtils {
    private DummyJsonRestUtils() {
    }

    public static void assertStatusCode(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }

    public static Response sendRequest(String baseUrl, String endpoint, String method, Map<String, String> headers, Object body) {
        switch (method.toUpperCase()) {
            case "GET":
                return given()
                        .baseUri(baseUrl)
                        .headers(headers)
                        .log().all()
                        .when()
                        .get(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();
            case "POST":
                if (body == null) {
                    return given()
                            .baseUri(baseUrl)
                            .headers(headers)
                            .log().all()
                            .when()
                            .post(endpoint)
                            .then()
                            .log().all()
                            .extract()
                            .response();
                }

                return given()
                        .baseUri(baseUrl)
                        .headers(headers)
                        .body(body)
                        .log().all()
                        .when()
                        .post(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }
}
