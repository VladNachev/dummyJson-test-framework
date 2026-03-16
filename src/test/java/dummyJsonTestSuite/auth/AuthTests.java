package dummyJsonTestSuite.auth;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTests {

    @Test
    public void loginShouldReturnAccessToken() {
        String accessToken = TokenProvider.getAccessToken();

        Assert.assertNotNull(accessToken, "Access token should be returned.");
        Assert.assertFalse(accessToken.isBlank(), "Access token should not be blank.");
    }
}
