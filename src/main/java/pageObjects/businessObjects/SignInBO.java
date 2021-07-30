package pageObjects.businessObjects;

import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.SignInPage;
import resources.ConfProperties;

public class SignInBO extends BasePage {

    private SignInPage signInPage;

    public SignInBO(){
        signInPage = new SignInPage();
    }

    public SignInBO openSignInPage(){
        driver.get(ConfProperties.getProperty("BASE_URL"));
        signInPage.clickBtnSignIn()
                .clickBtnUseAnotherAccount();
        return this;
    }

    public SignInBO login(String email, String pass){
        signInPage.inputEmail(email)
                .clickBtnContinue()
                .inputPassword(pass)
                .clickBtnLogin();
        return this;
    }

    public SignInBO inputLoginName(String email){
        signInPage.inputEmail(email);
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
