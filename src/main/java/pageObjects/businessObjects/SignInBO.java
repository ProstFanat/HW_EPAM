package pageObjects.businessObjects;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageObjects.BasePage;
import pageObjects.SignInPage;
import resources.ConfProperties;

public class SignInBO extends BasePage {

    private SignInPage signInPage;
    private static final Logger LOG = Logger.getLogger(SignInBO.class);
    public SignInBO(){
        signInPage = new SignInPage();
    }

    public SignInBO openSignInPage(){
        driver.get(ConfProperties.getProperty("BASE_URL"));
        LOG.info(String.format("Page opened - %s", ConfProperties.getProperty("BASE_URL")));
        signInPage.clickBtnSignIn()
                .clickBtnUseAnotherAccount();
        LOG.info("'Log In' page opened");
        return this;
    }

    public HomeBO loginValid(String email, String pass){
        signInPage.inputEmail(email)
                .clickBtnContinue()
                .inputPassword(pass)
                .clickBtnLogin();
        LOG.info(String.format("Logging user with credentials: 'Mail: %s' 'Pass: %s'", email, pass));
        return new HomeBO();
    }

    public SignInBO loginInvalid(String email, String pass){
        signInPage.inputEmail(email)
                .clickBtnContinue()
                .inputPassword(pass)
                .clickBtnLogin();
        LOG.info(String.format("Logging user with credentials: 'Mail: %s' 'Pass: %s'", email, pass));
        return this;
    }

    public SignInBO inputLoginName(String email){
        signInPage.inputEmail(email);
        LOG.info(String.format("Input email: '%s'", email));
        return this;
    }

    public SignInBO verifyFailedLoginErrorMessageDisplayed(){
        Assert.assertTrue(signInPage.isLoginFailedErrorMessageDisplayed());
        return this;
    }

    public SignInBO verifyBtnContinueIsDisabled(){
        Assert.assertFalse(signInPage.isContinueBtnEnabled());
        return this;
    }

    public SignInBO verifyBtnContinueIsEnabled(){
        Assert.assertTrue(signInPage.isContinueBtnEnabled());
        return this;
    }

}
