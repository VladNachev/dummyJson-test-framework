package dummyjsontestsuite.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {
    private final ExtentReports extentReports = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extentReports.createTest(
                result.getTestClass().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName()
        );
        ExtentTestManager.setTest(extentTest);
        extentTest.info("Test started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test passed.");
        ExtentTestManager.unload();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = ExtentTestManager.getTest();
        if (extentTest != null) {
            extentTest.fail(result.getThrowable());
        }
        ExtentTestManager.unload();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = ExtentTestManager.getTest();
        if (extentTest != null) {
            extentTest.skip(result.getThrowable());
        }
        ExtentTestManager.unload();
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
