package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    private static final Logger LOG = Logger.getLogger(HomePage.class);

    @FindBy(css = ".header-controls .user-info__name")
    private WebElement infoUserName;

    @FindBy(css = ".header-controls .profile-photo")
    private WebElement infoUserPhoto;

    @FindBy(css = ".header-controls a[href= '/Auth/LogoutExt']")
    private WebElement btnLogOut;

    @FindBy(xpath = "//nav[@class= 'main-nav']//a[contains(@class, 'training')]")
    private WebElement btnTrainingList;

    public HomePage clickUserPhoto(){
        waitToBeClickable(5000, infoUserPhoto);
        infoUserPhoto.click();
        LOG.info("'User Photo' clicked");
        return this;
    }

    public HomePage clickBtnLogOut(){
        waitToBeClickable(5000, btnLogOut);
        btnLogOut.click();
        LOG.info("'Log out' button clicked");
        return this;
    }

    public HomePage clickBtnTrainingList(){
        waitToBeClickable(5000, btnTrainingList);
        btnTrainingList.click();
        LOG.info("'Training List' button clicked");
        return this;
    }

    public boolean isRightUserNameDisplayed(){
        boolean isRight = "Adrian Boychuk".equals(infoUserName.getText().trim());
        LOG.info(String.format("Is 'Right User Name' displayed : '%s'", isRight));
        return isRight;
    }
}
