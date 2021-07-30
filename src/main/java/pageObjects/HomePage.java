package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {
    @FindBy(css = ".header-controls .user-info__name")
    private WebElement infoUserName;

    @FindBy(css = ".header-controls .profile-photo")
    private WebElement infoUserPhoto;

    @FindBy(css = ".header-controls a[href= '/Auth/LogoutExt']")
    private WebElement btnLogOut;

    public HomePage clickUserPhoto(){
        waitToBeClickable(5000, infoUserPhoto);
        infoUserPhoto.click();
        return this;
    }

    public HomePage clickBtnLogOut(){
        waitToBeClickable(5000, btnLogOut);
        btnLogOut.click();
        return this;
    }

    public boolean isRightUserNameDisplayed(){
        return "Adrian Boychuk".equals(infoUserName.getText().trim());
    }
}
