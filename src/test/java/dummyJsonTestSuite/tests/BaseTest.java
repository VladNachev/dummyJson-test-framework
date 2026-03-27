package dummyjsontestsuite.tests;

import dummyjsontestsuite.auth.TokenProvider;
import dummyjsontestsuite.reporting.ExtentTestListener;
import dummyjsontestsuite.reporting.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(ExtentTestListener.class)
public abstract class BaseTest {
    protected static final double DECIMAL_TOLERANCE = 0.001;
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected String accessToken;

    @BeforeClass
    public void setUpAuth() {
        logStep("Retrieving access token for authenticated test execution.");
        accessToken = TokenProvider.getAccessToken();
        logStep("Access token retrieved successfully.");
    }

    protected void logStep(String message) {
        logger.info(message);
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.INFO, message);
        }
    }
}
