import com.gurock.testrail.APIException;
import driver.DriverFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.businessObjects.SignInBO;
import pageObjects.businessObjects.TrainingListBO;
import resources.ConfProperties;
import resources.SlackIntegration;
import resources.TestRailIntegration;

import java.io.IOException;
import java.util.List;

//@Listeners(com.gurock.testrail.Listener.class)
public class TrainingListPageTest extends BaseTest{

    private TrainingListBO trainingListBO;

    @BeforeMethod
    private void setUp(){
        DriverFactory.initDriver(System.getProperty("browser"));
        trainingListBO = new TrainingListBO();
        new SignInBO().openSignInPage()
                .loginValid(ConfProperties.getProperty("LOGIN_MAIL"), ConfProperties.getProperty("LOGIN_PASS"))
                .openTrainingListPage();
    }

    @AfterMethod
    private void afterTest(ITestResult testResult) throws APIException, IOException, NoSuchMethodException {
        new BaseTest().afterMethod(testResult);
        DriverFactory.quitDriver();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Skills’.")
    public void testTrainingsSearchBySkills(){
        trainingListBO.clearAllFilters()
                .activateCheckboxFilterBySkill(ConfProperties.getProperty("SKILL_JAVA"))
                .verifySearchWithBySkillWordOnly(ConfProperties.getProperty("SKILL_JAVA"))

                .deactivateCheckboxFilterBySkill(ConfProperties.getProperty("SKILL_JAVA"))
                .activateCheckboxFilterBySkill(ConfProperties.getProperty("SKILL_RUBY"))
                .verifyInfoMessageDisplayed();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Locations’.")
    public void testTrainingsSearchByLocations(){
        trainingListBO.clearAllFilters()
                .activateCheckboxFilterByCity(ConfProperties.getProperty("COUNTRY_UKRAINE"), ConfProperties.getProperty("CITY_LVIV"))
                .verifySearchWithCountryAndMultiLocation(ConfProperties.getProperty("COUNTRY_UKRAINE"));
    }

    @Test
    public void testTest() throws Exception {
        new SlackIntegration().sendTestExecutionStatusToSlack("test");
    }
}
