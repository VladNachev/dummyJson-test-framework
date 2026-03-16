package API_Playground;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestUtils {
    private RestUtils() {
    }

    public static void assertStatusCode(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }

    public static Response sendRequest(String baseUrl, String endpoint, String method, Map<String, String> headers, String body) {
        switch (method.toUpperCase()) {
            case "GET":
                return given()
                        .baseUri(baseUrl)
                        .headers(headers)
                        .when()
                        .body(body)
                        .get(endpoint);
            case "POST":
                return given()
                        .baseUri(baseUrl)
                        .headers(headers)
                        .when()
                        .body(body)
                        .post(endpoint);
            case "PUT":
                return given()
                        .baseUri(baseUrl)
                        .headers(headers)
                        .when()
                        .body(body)
                        .put(endpoint);
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }
}
