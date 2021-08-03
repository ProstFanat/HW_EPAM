import dataProvider.EmailValues;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.businessObjects.HomeBO;
import pageObjects.businessObjects.SignInBO;
import resources.ConfProperties;

public class SignInPageTest extends BaseTest {

    SoftAssert sa = new SoftAssert();

    @BeforeMethod
    private void setupForTest(){
        new SignInBO()
                .openSignInPage();
    }

    @Test(description = "Verify login with appropriate credentials.")
    private void testLoginWithValidCredentials(){
        new SignInBO().loginValid(ConfProperties.getProperty("LOGIN_MAIL"),
                ConfProperties.getProperty("LOGIN_PASS"))
                .verifyThatRightUserNameDisplayed()
                .logOut();
    }

    @Test(description = "Verify login with invalid password.")
    private void testLoginWithInValidPassword(){
        new SignInBO().loginInvalid(ConfProperties.getProperty("LOGIN_MAIL"),
                ConfProperties.getProperty("LOGIN_INCORRECT_PASS"))
                .verifyFailedLoginErrorMessageDisplayed();
    }

    @Test(description = "positive scenario test")
    private void testPositiveScenarioForEnteringMail(){
        new SignInBO().inputLoginName(ConfProperties.getProperty("LOGIN_MAIL"))
                        .verifyBtnContinueIsEnabled();
    }

    @Test(dataProvider = "valid-emails", dataProviderClass = EmailValues.class, description = "positive scenario test which will be verifying only 'boundary values' criteria")
    private void testPositiveScenarioForEnteringMailOnlyBoundaryValues(String mail){
        new SignInBO().inputLoginName(mail)
                .verifyBtnContinueIsEnabled();
    }

    @Test(dataProvider = "invalid-emails", dataProviderClass = EmailValues.class, description = "negative scenario test with verifying all the criteria")
    private void testNegativeScenarioForEnteringMail(String mail){
        new SignInBO().inputLoginName(mail)
                .verifyBtnContinueIsDisabled();
    }
}
