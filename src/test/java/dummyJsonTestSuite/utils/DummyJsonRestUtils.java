package dummyjsontestsuite.utils;

import dummyjsontestsuite.reporting.ExtentTestManager;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

public final class DummyJsonRestUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DummyJsonRestUtils.class);

    private DummyJsonRestUtils() {
    }

    public static void assertStatusCode(Response response, int statusCode) {
        String message = String.format("Validating response status code. Expected: %d, Actual: %d", statusCode, response.getStatusCode());
        LOGGER.info(message);
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.INFO, message);
        }
        response.then().statusCode(statusCode);
    }

    public static Response sendRequest(String baseUrl, String endpoint, String method, Map<String, String> headers, Object body) {
        String requestMessage = String.format("Sending %s request to %s%s", method.toUpperCase(), baseUrl, endpoint);
        LOGGER.info(requestMessage);
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.INFO, requestMessage);
        }

        RequestSpecification request = given()
                .baseUri(baseUrl)
                .headers(headers)
                .log().all();

        if (body != null) {
            request.body(body);
        }

        Response response;

        switch (method.toUpperCase()) {
            case "GET":
                response = request
                        .when()
                        .get(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();
                break;
            case "POST":
                response = request
                        .when()
                        .post(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();
                break;
            case "PUT":
                response = request
                        .when()
                        .put(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();
                break;
            case "DELETE":
                response = request
                        .when()
                        .delete(endpoint)
                        .then()
                        .log().all()
                        .extract()
                        .response();
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        String responseMessage = String.format(
                "Received response. Status: %d, Endpoint: %s",
                response.getStatusCode(),
                endpoint
        );
        LOGGER.info(responseMessage);
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.INFO, responseMessage);
        }

        return response;
    }
}
