package dummyjsontestsuite.reporting;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentTestManager {
    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();

    private ExtentTestManager() {
    }

    public static void setTest(ExtentTest test) {
        CURRENT_TEST.set(test);
    }

    public static ExtentTest getTest() {
        return CURRENT_TEST.get();
    }

    public static void unload() {
        CURRENT_TEST.remove();
    }
}
