package dummyJsonTestSuite.auth;

import io.restassured.response.Response;
import org.testng.Assert;

public final class TokenProvider {
    private TokenProvider() {
    }

    public static String getAccessToken() {
        String requestBody = """
                {
                  "username": "%s",
                  "password": "%s"
                }
                """.formatted(
                DummyJsonConfig.USERNAME,
                DummyJsonConfig.PASSWORD
        );

        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                "/auth/login",
                "POST",
                DummyJsonConfig.jsonHeaders(),
                requestBody);

        DummyJsonRestUtils.assertStatusCode(response, 200);

        String accessToken = response.jsonPath().getString("accessToken");
        Assert.assertNotNull(accessToken, "Login response should contain accessToken.");
        Assert.assertFalse(accessToken.isBlank(), "Access token should not be blank.");
        return accessToken;
    }
}
