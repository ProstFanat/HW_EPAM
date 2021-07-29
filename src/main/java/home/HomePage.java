package home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.BasePage;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".header-controls .user-info__name")
    public WebElement infoUserName;

    @FindBy(css = ".header-controls .profile-photo")
    WebElement infoUserPhoto;

    @FindBy(css = ".header-controls a[href= '/Auth/LogoutExt']")
    WebElement btnLogOut;

    public void logOut(){
        waitToBeClickable(5000, infoUserPhoto);
        infoUserPhoto.click();
        waitToBeClickable(5000, btnLogOut);
        btnLogOut.click();
    }
}
