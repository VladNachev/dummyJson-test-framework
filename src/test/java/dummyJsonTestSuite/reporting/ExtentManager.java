package dummyjsontestsuite.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ExtentManager {
    private static final String REPORT_DIRECTORY = "target/extent-reports";
    private static final String REPORT_FILE = "dummyjson-suite-report.html";
    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            Path reportDirectory = Paths.get(REPORT_DIRECTORY);
            try {
                Files.createDirectories(reportDirectory);
            } catch (Exception e) {
                throw new IllegalStateException("Unable to create ExtentReports directory.", e);
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportDirectory.resolve(REPORT_FILE).toString());
            sparkReporter.config().setDocumentTitle("DummyJSON Automation Report");
            sparkReporter.config().setReportName("DummyJSON Test Execution");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Framework", "TestNG + Rest Assured");
            extentReports.setSystemInfo("Project", "be_automation");
        }

        return extentReports;
    }
}
