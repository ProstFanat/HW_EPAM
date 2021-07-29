package login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    WebElement emailInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "kc-login-next")
    WebElement btnContinue;

    @FindBy(id = "kc-login")
    WebElement btnSignIn;

    @FindBy(id = "kc-back-switch")
    WebElement btnBack;

    @FindBy(id = "kc-login-prev")
    WebElement btnBackToLogin;

    @FindBy(className = "header-auth__signin")
    WebElement btnLogin;

    @FindBy(css = ".identity-another div")
    WebElement btnUseAnotherAccount;

    @FindBy(className = "error-text")
    WebElement errorMessage;

    public void openLoginPage(){
        driver.get("https://training.by");
        waitToVisibilityOf(5000, btnLogin);
        btnLogin.click();
        waitForPageLoaded();
        try {
            btnUseAnotherAccount.click();
        } catch (Exception ignored) {}
    }

    public void inputEmail(String email){
        waitToVisibilityOf(5000, emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void inputPassword(String pass){
        waitToBeClickable(5000, passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(pass);
    }

    public void clickBtnContinue(){
        waitToBeClickable(5000, btnContinue);
        btnContinue.click();
    }

    public void clickBtnSignIn(){
        waitToBeClickable(5000, btnSignIn);
        btnSignIn.click();
    }

    public String getErrorMessage(){
        waitForPageLoaded();
        return errorMessage.getText().trim();
    }

    public boolean btnContinueIsEnabled(){
        return btnContinue.isEnabled();
    }

}
