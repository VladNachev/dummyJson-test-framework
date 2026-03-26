package dummyjsontestsuite.auth;

import dummyjsontestsuite.config.DummyJsonConfig;
import dummyjsontestsuite.enums.ValidationMessages;
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
                DummyJsonConfig.LOGIN_ENDPOINT,
                DummyJsonConfig.POST,
                DummyJsonConfig.jsonHeaders(),
                requestBody
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        String accessToken = response.jsonPath().getString("accessToken");
        Assert.assertNotNull(accessToken, ValidationMessages.LOGIN_RESPONSE_SHOULD_CONTAIN_ACCESS_TOKEN.getMessage());
        Assert.assertFalse(accessToken.isBlank(), ValidationMessages.ACCESS_TOKEN_SHOULD_NOT_BE_BLANK.getMessage());
        return accessToken;
    }
}
