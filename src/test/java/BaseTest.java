import driver.DriverFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import resources.SlackIntegration;

public class BaseTest extends TestListenerAdapter {

    @BeforeClass
    public void setup(){
        DriverFactory.initDriver(System.getProperty("browser"));
    }

    @AfterMethod
    public void afterMethod(ITestResult result)
    {
        try
        {
            if(result.getStatus() == ITestResult.SUCCESS)
            {
                new SlackIntegration().sendTestExecutionStatusToSlack("Test: " + result.getName() + " - passed");
            }

            else if(result.getStatus() == ITestResult.FAILURE)
            {
                new SlackIntegration().sendTestExecutionStatusToSlack("Test: " + result.getName() + " - Failed");
            }

            else if(result.getStatus() == ITestResult.SKIP ){
                new SlackIntegration().sendTestExecutionStatusToSlack("Test: " + result.getName() + " - Skipped");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @AfterClass
    public void exit(){
        DriverFactory.quitDriver();
    }
}
