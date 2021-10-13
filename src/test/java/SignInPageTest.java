import dataProvider.EmailValues;
import driver.DriverFactory;
import org.testng.annotations.*;
import pageObjects.businessObjects.SignInBO;
import resources.ConfProperties;

public class SignInPageTest{
    @BeforeMethod
    private void setUp(){
        DriverFactory.initDriver();
        new SignInBO()
                .openSignInPage();
    }

    @AfterMethod
    private void tearDown(){
        DriverFactory.quitDriver();
    }

    @Test(description = "Verify login with appropriate credentials.", threadPoolSize = 30)
    private void testLoginWithValidCredentials(){
        new SignInBO().loginValid(ConfProperties.getProperty("LOGIN_MAIL"),
                ConfProperties.getProperty("LOGIN_PASS"))
                .verifyThatRightUserNameDisplayed()
                .logOut();
    }

    @Test(description = "Verify login with invalid password.", threadPoolSize = 30)
    private void testLoginWithInValidPassword(){
        new SignInBO().loginInvalid(ConfProperties.getProperty("LOGIN_MAIL"),
                ConfProperties.getProperty("LOGIN_INCORRECT_PASS"))
                .verifyFailedLoginErrorMessageDisplayed();
    }

    @Test(description = "positive scenario test", threadPoolSize = 30)
    private void testPositiveScenarioForEnteringMail(){
        new SignInBO().inputLoginName(ConfProperties.getProperty("LOGIN_MAIL"))
                        .verifyBtnContinueIsEnabled();
    }

    @Test(dataProvider = "valid-emails", dataProviderClass = EmailValues.class,
            description = "positive scenario test which will be verifying only 'boundary values' criteria",
            threadPoolSize = 30)
    private void testPositiveScenarioForEnteringMailOnlyBoundaryValues(String mail){
        new SignInBO().inputLoginName(mail)
                .verifyBtnContinueIsEnabled();
    }

    @Test(dataProvider = "invalid-emails", dataProviderClass = EmailValues.class,
            description = "negative scenario test with verifying all the criteria",
            threadPoolSize = 30)
    private void testNegativeScenarioForEnteringMail(String mail){
        new SignInBO().inputLoginName(mail)
                .verifyBtnContinueIsDisabled();
    }


}
