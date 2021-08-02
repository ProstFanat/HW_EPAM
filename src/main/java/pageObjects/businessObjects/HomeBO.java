package pageObjects.businessObjects;

import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObjects.HomePage;

public class HomeBO {
    private HomePage homePage;
    private static final Logger LOG = Logger.getLogger(HomeBO.class);

    public HomeBO(){
         homePage = new HomePage();
    }

    public void logOut(){
        homePage.clickUserPhoto()
                .clickBtnLogOut();
        LOG.info("User was Logout");
    }

    public HomeBO verifyThatRightUserNameDisplayed(){
        Assert.assertTrue(homePage.isRightUserNameDisplayed());
        return this;
    }



}
