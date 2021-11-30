import com.gurock.testrail.APIException;
import driver.DriverFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import resources.SlackIntegration;
import resources.TestRailIntegration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseTest extends TestListenerAdapter {
    private List<ITestResult> testResultList = new ArrayList<>();

    @BeforeClass
    public void setup(){
        DriverFactory.initDriver(System.getProperty("browser"));
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        testResultList.add(result);
        //new TestRailIntegration().postTestResult(result);
    }

    @AfterClass
    public void exit() throws Exception {
        new SlackIntegration().sendTestExecutionStatusToSlack(new SlackIntegration().generateTestReport(testResultList));
        DriverFactory.quitDriver();
    }
}
