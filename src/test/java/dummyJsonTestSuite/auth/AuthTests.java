package dummyJsonTestSuite.auth;

import dummyJsonTestSuite.enums.ValidationMessages;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTests {

    @Test
    public void loginShouldReturnAccessToken() {
        String accessToken = TokenProvider.getAccessToken();

        Assert.assertNotNull(accessToken, ValidationMessages.ACCESS_TOKEN_SHOULD_BE_RETURNED.getMessage());
        Assert.assertFalse(accessToken.isBlank(), ValidationMessages.ACCESS_TOKEN_SHOULD_NOT_BE_BLANK.getMessage());
    }
}
