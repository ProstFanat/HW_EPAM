import com.gurock.testrail.APIException;
import dataProvider.EmailValues;
import driver.DriverFactory;
import org.jsoup.Connection;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageObjects.BasePage;
import pageObjects.businessObjects.SignInBO;
import resources.ConfProperties;
import resources.TestRailAnnotations;

public class SignInPageTest extends BaseTest{

    @BeforeMethod
    private void setUp(){
        DriverFactory.initDriver(System.getProperty("browser"));
        new SignInBO()
                .openSignInPage();
    }

    @AfterMethod
    private void tearDown(ITestResult testResult){
        new BaseTest().afterMethod(testResult);
        DriverFactory.quitDriver();
    }

    @TestRailAnnotations.UseAsTestRailId(testRailId = 5)
    @Test(description = "Verify login with appropriate credentials.", threadPoolSize = 30)
    private void testLoginWithValidCredentials(){
        new SignInBO().loginValid(ConfProperties.getProperty("LOGIN_MAIL"),
                ConfProperties.getProperty("LOGIN_PASS"))
                .verifyThatRightUserNameDisplayed()
                .logOut();
    }

    @TestRailAnnotations.UseAsTestRailId(testRailId = 4)
    @Test(description = "Verify login with invalid password.", threadPoolSize = 30)
    private void testLoginWithInValidPassword(){
        new SignInBO().loginInvalid(ConfProperties.getProperty("LOGIN_MAIL"),
                ConfProperties.getProperty("LOGIN_INCORRECT_PASS"))
                .verifyFailedLoginErrorMessageDisplayed();
    }

    @TestRailAnnotations.UseAsTestRailId(testRailId = 1)
    @Test(description = "positive scenario test", threadPoolSize = 30)
    private void testPositiveScenarioForEnteringMail(){
        new SignInBO().inputLoginName(ConfProperties.getProperty("LOGIN_MAIL"))
                        .verifyBtnContinueIsEnabled();
    }

    @TestRailAnnotations.UseAsTestRailId(testRailId = 2)
    @Test(dataProvider = "valid-emails", dataProviderClass = EmailValues.class,
            description = "positive scenario test which will be verifying only 'boundary values' criteria",
            threadPoolSize = 30)
    private void testPositiveScenarioForEnteringMailOnlyBoundaryValues(String mail){
        new SignInBO().inputLoginName(mail)
                .verifyBtnContinueIsEnabled();
    }

    @TestRailAnnotations.UseAsTestRailId(testRailId = 3)
    @Test(dataProvider = "invalid-emails", dataProviderClass = EmailValues.class,
            description = "negative scenario test with verifying all the criteria",
            threadPoolSize = 30)
    private void testNegativeScenarioForEnteringMail(String mail){
        new SignInBO().inputLoginName(mail)
                .verifyBtnContinueIsDisabled();
    }


}
