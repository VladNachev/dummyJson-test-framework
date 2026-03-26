package dummyjsontestsuite.tests;

import dummyjsontestsuite.auth.TokenProvider;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
    protected static final double DECIMAL_TOLERANCE = 0.001;
    protected String accessToken;

    @BeforeClass
    public void setUpAuth() {
        accessToken = TokenProvider.getAccessToken();
    }
}
