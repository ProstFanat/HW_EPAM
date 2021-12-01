import com.codepine.api.testrail.TestRail;
import com.gurock.testrail.APIException;
import driver.DriverFactory;
import org.jsoup.Connection;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import resources.SlackIntegration;
import resources.TestRailIntegration;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseTest extends TestListenerAdapter {
    private List<ITestResult> testResultList = new ArrayList<>();
    public TestRailIntegration testRailIntegration;
    private List<Integer> testIdList = new ArrayList<>();
    public Integer tempTestId;

    public BaseTest(){
        testRailIntegration = new TestRailIntegration();
        testRailIntegration.createTestRailInstance();
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        testIdList.add(tempTestId);
        testResultList.add(result);
    }

    @AfterClass
    public void exit() throws Exception {
        new SlackIntegration().sendTestExecutionStatusToSlack(new SlackIntegration().generateTestReport(testResultList));
        testRailIntegration.createRun()
                .updateRun(testIdList)
                .addResults(testResultList, testIdList)
                .closeRun();
        DriverFactory.quitDriver();
    }

    public void setTempTestId(Integer tempTestId) {
        this.tempTestId = tempTestId;
    }

    public void setSuiteId(int id){
        testRailIntegration.setSuiteId(id);
    }
}
