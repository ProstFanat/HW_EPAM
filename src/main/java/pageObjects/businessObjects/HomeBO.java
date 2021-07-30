package pageObjects.businessObjects;

import org.testng.Assert;
import pageObjects.HomePage;

public class HomeBO {
    private HomePage homePage;

    public HomeBO(){
         homePage = new HomePage();
    }

    public void logOut(){
        homePage.clickUserPhoto()
                .clickBtnLogOut();
    }

    public HomeBO verifyThatRightUserNameDisplayed(){
        Assert.assertTrue(homePage.isRightUserNameDisplayed());
        return this;
    }



}
