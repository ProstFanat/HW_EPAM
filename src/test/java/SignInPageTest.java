import com.epam.reportportal.testng.ReportPortalTestNGListener;
import dataProvider.EmailValues;
import driver.DriverFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageObjects.businessObjects.SignInBO;
import resources.ConfProperties;

@Listeners({ReportPortalTestNGListener.class})
public class SignInPageTest extends BaseTest {

    @BeforeMethod
    private void setUp(){
        DriverFactory.initDriver(System.getProperty("browser"));
        setSuiteId(Integer.parseInt(ConfProperties.getProperty("TEST_RAIL_SUIT_ID_SIGN_IN_PAGE")));
        new SignInBO()
                .openSignInPage();
    }

    @AfterMethod
    private void tearDown(ITestResult testResult){
        new BaseTest().afterMethod(testResult);
        DriverFactory.quitDriver();
    }

    @Test(description = "Verify login with appropriate credentials.", threadPoolSize = 30)
    private void testLoginWithValidCredentials(){
        setTempTestId(5);
        new SignInBO().loginValid(ConfProperties.getProperty("LOGIN_MAIL"),
                ConfProperties.getProperty("LOGIN_PASS"))
                .verifyThatRightUserNameDisplayed()
                .logOut();
    }

    @Test(description = "Verify login with invalid password.", threadPoolSize = 30)
    private void testLoginWithInValidPassword(){
        setTempTestId(4);
        new SignInBO().loginInvalid(ConfProperties.getProperty("LOGIN_MAIL"),
                ConfProperties.getProperty("LOGIN_INCORRECT_PASS"))
                .verifyFailedLoginErrorMessageDisplayed();
    }

    @Test(description = "positive scenario test", threadPoolSize = 30)
    private void testPositiveScenarioForEnteringMail(){
        setTempTestId(1);
        new SignInBO().inputLoginName(ConfProperties.getProperty("LOGIN_MAIL"))
                        .verifyBtnContinueIsEnabled();
    }

    @Test(dataProvider = "valid-emails", dataProviderClass = EmailValues.class,
            description = "positive scenario test which will be verifying only 'boundary values' criteria",
            threadPoolSize = 30)
    private void testPositiveScenarioForEnteringMailOnlyBoundaryValues(String mail){
        setTempTestId(2);
        new SignInBO().inputLoginName(mail)
                .verifyBtnContinueIsEnabled();
    }

    @Test(dataProvider = "invalid-emails", dataProviderClass = EmailValues.class,
            description = "negative scenario test with verifying all the criteria",
            threadPoolSize = 30)
    private void testNegativeScenarioForEnteringMail(String mail){
        setTempTestId(3);
        new SignInBO().inputLoginName(mail)
                .verifyBtnContinueIsDisabled();
    }


}
