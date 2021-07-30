import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.businessObjects.HomeBO;
import pageObjects.businessObjects.SignInBO;
import resources.ConfProperties;

public class SignInPageTest extends BaseTest {

    @BeforeMethod
    private void setupForTest(){
        new SignInBO()
                .openSignInPage();
    }

    @Test(description = "Verify login with appropriate credentials.")
    private void testLoginWithValidCredentials(){
        new SignInBO().login("prostfanat2001@gmail.com", "Mylife2001");

        new HomeBO().verifyThatRightUserNameDisplayed()
                    .logOut();
    }

    @Test(description = "Verify login with invalid password.")
    private void testLoginWithInValidPassword(){
        new SignInBO().login("prostfanat2001@gmail.com", "test")
                        .verifyFailedLoginErrorMessageDisplayed();
    }

    @Test(description = "positive scenario test")
    private void testPositiveScenarioForEnteringMail(){
        new SignInBO().inputLoginName("prostfanat2001@gmail.com")
                        .verifyBtnContinueIsEnabled();
    }

    @Test(description = "positive scenario test which will be verifying only 'boundary values' criteria")
    private void testPositiveScenarioForEnteringMailOnlyBoundaryValues(){
        new SignInBO().inputLoginName(ConfProperties.getProperty("TEXT_63_CHARACTERS"))
                .verifyBtnContinueIsEnabled()

                .inputLoginName(ConfProperties.getProperty("TEXT_64_CHARACTERS"))
                .verifyBtnContinueIsEnabled()

                .inputLoginName("prostfanat2001@gmail.co")
                .verifyBtnContinueIsEnabled()

                .inputLoginName("prostfanat2001@gmail.com")
                .verifyBtnContinueIsEnabled()

                .inputLoginName("prostfanat2001@gmail.comcomcom")
                .verifyBtnContinueIsEnabled()

                .inputLoginName("prostfanat2001@gmail.comcomcomc")
                .verifyBtnContinueIsEnabled();
    }

    @Test(description = "negative scenario test with verifying all the criteria using hard asserts")
    private void testNegativeScenarioForEnteringMail(){
        new SignInBO().inputLoginName("prostfanat2001gmail.com")
                .verifyBtnContinueIsDisabled()

                .inputLoginName("@prostfanat2001gmail.com")
                .verifyBtnContinueIsDisabled()

                .inputLoginName("prostfanat2001@gmailcom")
                .verifyBtnContinueIsDisabled()

                .inputLoginName(ConfProperties.getProperty("TEXT_65_CHARACTERS"))
                .verifyBtnContinueIsDisabled()

                .inputLoginName("prostfanat2001@gmail.c")
                .verifyBtnContinueIsDisabled()

                .inputLoginName("prostfanat2001@gmail.comcomcomco")
                .verifyBtnContinueIsDisabled();
    }
}
