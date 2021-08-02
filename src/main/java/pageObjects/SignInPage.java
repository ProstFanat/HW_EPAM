package pageObjects;


import decorator.elements.Element;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(SignInPage.class);

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
        Element.click(btnSighIn);
        LOG.info("'Sign In' button clicked");
        return this;
    }

    public SignInPage clickBtnUseAnotherAccount(){
        waitForPageLoaded();
        try {
            btnUseAnotherAccount.click();
            LOG.info("'Use Another Account' button clicked");
        } catch (Exception ignored) {}
        return this;
    }

    public SignInPage inputEmail(String email){
        waitToVisibilityOf(5000, emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
        LOG.info("Mail was entered.");
        return this;
    }

    public SignInPage inputPassword(String pass){
        waitToBeClickable(5000, passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(pass);
        LOG.info("Password was entered.");
        return this;
    }

    public SignInPage clickBtnContinue(){
        Element.click(btnContinue);
        LOG.info("'Continue' button clicked");
        return this;
    }

    public SignInPage clickBtnLogin(){
        Element.click(btnLogin);
        LOG.info("'Log In' button clicked");
        return this;
    }

    public boolean isLoginFailedErrorMessageDisplayed(){
        waitForPageLoaded();
        boolean isDisplayed = "We can't find user with such credentials.".equals(errorMessage.getText().trim());
        LOG.info(String.format("Is 'Login Failed Error Message' displayed': '%s'", isDisplayed));
        return isDisplayed;
    }

    public boolean isContinueBtnEnabled(){
        waitForPageLoaded();
        boolean isEnabled = btnContinue.isEnabled();
        LOG.info(String.format("Is  button 'Continue' enabled : '%s'", isEnabled));
        return btnContinue.isEnabled();
    }

}
