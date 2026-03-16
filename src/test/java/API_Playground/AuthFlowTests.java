package API_Playground;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthFlowTests {

    @Test
    public void loginShouldReturnAccessToken() {
        String accessToken = loginAndGetAccessToken();

        Assert.assertNotNull(accessToken, "Access token should be returned.");
        Assert.assertFalse(accessToken.isBlank(), "Access token should not be blank.");
    }

    @Test
    public void updateProductShouldSucceedForAuthenticatedUser() {
        String productId = "10000000-0000-0000-0000-000000000003";
        String requestBody = """
                {
                  "name": "Mechanical Keyboard X",
                  "price": 50.99,
                  "category": "electronics",
                  "stock": 8
                }
                """;

        Response response = RestUtils.sendRequest(
                ApiPlaygroundConfig.BASE_URL,
                "/products/" + productId,
                "PUT",
                ApiPlaygroundConfig.authorizedJsonHeaders(loginAndGetAccessToken()),
                requestBody);

        RestUtils.assertStatusCode(response, 200);
        Assert.assertFalse(response.asString().isBlank(), "Update response should not be empty.");
    }

    private String loginAndGetAccessToken() {
        String requestBody = """
                {
                  "email": "%s",
                  "password": "%s"
                }
                """.formatted(
                ApiPlaygroundConfig.STANDARD_USER_EMAIL,
                ApiPlaygroundConfig.STANDARD_USER_PASSWORD
        );

        Response response = RestUtils.sendRequest(
                ApiPlaygroundConfig.BASE_URL,
                "/auth/login",
                "POST",
                ApiPlaygroundConfig.jsonHeaders(),
                requestBody);

        RestUtils.assertStatusCode(response, 200);

        String accessToken = response.jsonPath().getString("access_token");
        Assert.assertNotNull(accessToken, "Login response should contain access_token.");
        return accessToken;
    }
}
