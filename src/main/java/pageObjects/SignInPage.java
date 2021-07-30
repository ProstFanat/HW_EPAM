package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {
    @FindBy(id = "username")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "kc-login-next")
    private WebElement btnContinue;

    @FindBy(id = "kc-login")
    private WebElement btnLogin;

    @FindBy(id = "kc-back-switch")
    private WebElement btnBack;

    @FindBy(id = "kc-login-prev")
    private WebElement btnBackToLogin;

    @FindBy(className = "header-auth__signin")
    private WebElement btnSighIn;

    @FindBy(css = ".identity-another div")
    private WebElement btnUseAnotherAccount;

    @FindBy(className = "error-text")
    private WebElement errorMessage;

    public SignInPage clickBtnSignIn(){
        waitToVisibilityOf(5000, btnSighIn);
        btnSighIn.click();
        return this;
    }

    public SignInPage clickBtnUseAnotherAccount(){
        waitForPageLoaded();
        try {
            btnUseAnotherAccount.click();
        } catch (Exception ignored) {}
        return this;
    }

    public SignInPage inputEmail(String email){
        waitToVisibilityOf(5000, emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public SignInPage inputPassword(String pass){
        waitToBeClickable(5000, passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(pass);
        return this;
    }

    public SignInPage clickBtnContinue(){
        waitToBeClickable(5000, btnContinue);
        btnContinue.click();
        return this;
    }

    public SignInPage clickBtnLogin(){
        waitToBeClickable(5000, btnLogin);
        btnLogin.click();
        return this;
    }

    public boolean isLoginFailedErrorMessageDisplayed(){
        waitForPageLoaded();
        return "We can't find user with such credentials.".equals(errorMessage.getText().trim());
    }

    public boolean isContinueBtnEnabled(){
        waitForPageLoaded();
        return btnContinue.isEnabled();
    }

}