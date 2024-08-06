package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        ExtentReportUtil.initReport();
    }

    @AfterClass
    public void tearDown() {
        ExtentReportUtil.flushReport();
    }
}
