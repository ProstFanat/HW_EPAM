import com.gurock.testrail.APIException;
import driver.DriverFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageObjects.businessObjects.SignInBO;
import pageObjects.businessObjects.TrainingListBO;
import resources.ConfProperties;
import resources.SlackIntegration;
import resources.TestRailIntegration;

import java.io.IOException;
import java.util.List;

public class TrainingListPageTest extends BaseTest{

    private TrainingListBO trainingListBO;

    @BeforeClass
    private void setUpClass(){
        setSuiteId(Integer.parseInt(ConfProperties.getProperty("TEST_RAIL_SUIT_ID_TRAINING_LIST")));
    }

    @BeforeMethod
    private void setUp(){
        DriverFactory.initDriver(System.getProperty("browser"));
        trainingListBO = new TrainingListBO();
        new SignInBO().openSignInPage()
                .loginValid(ConfProperties.getProperty("LOGIN_MAIL"), ConfProperties.getProperty("LOGIN_PASS"))
                .openTrainingListPage();
    }

    @AfterMethod
    private void afterTest(ITestResult testResult){
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
        setTempTestId(6);
        new SlackIntegration().sendTestExecutionStatusToSlack("test");
    }
}
