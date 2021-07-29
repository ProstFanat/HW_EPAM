import home.HomePage;
import login.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import resources.DriverSetUp;

public class Login{

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private final SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    private void setup(){
        driver = DriverSetUp.getChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    private void setupForTest(){
        loginPage.openLoginPage();
    }

    @Test(description = "Verify login with appropriate credentials.")
    private void testLoginWithValidCredentials(){
        loginPage.inputEmail("prostfanat2001@gmail.com");
        loginPage.clickBtnContinue();
        loginPage.inputPassword("Mylife2001");
        loginPage.clickBtnSignIn();
        Assert.assertEquals("Adrian Boychuk", homePage.infoUserName.getText().trim());
        homePage.logOut();
    }

    @Test(description = "Verify login with invalid password.")
    private void testLoginWithInValidPassword(){
        loginPage.inputEmail("prostfanat2001@gmail.com");
        loginPage.clickBtnContinue();
        loginPage.inputPassword("test");
        loginPage.clickBtnSignIn();
        Assert.assertEquals("We can't find user with such credentials.", loginPage.getErrorMessage());
    }

    @Test(description = "positive scenario test")
    private void testPositiveScenarioForEnteringMail(){
        loginPage.inputEmail("prostfanat2001@gmail.com");
        Assert.assertTrue(loginPage.btnContinueIsEnabled());
    }

    @Test(description = "positive scenario test which will be verifying only 'boundary values' criteria")
    private void testPositiveScenarioForEnteringMailOnlyBoundaryValues(){
        loginPage.inputEmail("this.is.a.63.character.text.this.is.a.63.character.text.this.is@gmail.com");
        Assert.assertTrue(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("this.is.a.64.character.text.this.is.a.64.character.text.this.is.@gmail.com");
        Assert.assertTrue(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("prostfanat2001@gmail.co");
        Assert.assertTrue(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("prostfanat2001@gmail.com");
        Assert.assertTrue(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("prostfanat2001@gmail.comcomcom");
        Assert.assertTrue(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("prostfanat2001@gmail.comcomcomc");
        Assert.assertTrue(loginPage.btnContinueIsEnabled());
    }

    @Test(description = "negative scenario test with verifying all the criteria using hard asserts")
    private void testNegativeScenarioForEnteringMailWithHardAsserts(){
        loginPage.inputEmail("prostfanat2001gmail.com");
        Assert.assertFalse(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("@prostfanat2001gmail.com");
        Assert.assertFalse(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("prostfanat2001@gmailcom");
        Assert.assertFalse(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("this.is.a.65.character.text.this.is.a.65.character.text.this.is.a@gmail.com");
        Assert.assertFalse(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("prostfanat2001@gmail.c");
        Assert.assertFalse(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("prostfanat2001@gmail.comcomcomco");
        Assert.assertFalse(loginPage.btnContinueIsEnabled());
    }

    @Test(description = "negative scenario test with verifying all the criteria using soft asserts")
    private void testNegativeScenarioForEnteringMailWithSoftAsserts(){
        loginPage.inputEmail("prostfanat2001gmail.com");
        softAssert.assertFalse(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("@prostfanat2001gmail.com");
        softAssert.assertFalse(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("prostfanat2001@gmailcom");
        softAssert.assertFalse(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("this.is.a.65.character.text.this.is.a.65.character.text.this.is.a@gmail.com");
        softAssert.assertFalse(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("prostfanat2001@gmail.c");
        softAssert.assertFalse(loginPage.btnContinueIsEnabled());

        loginPage.inputEmail("prostfanat2001@gmail.comcomcomco");
        softAssert.assertFalse(loginPage.btnContinueIsEnabled());

        softAssert.assertAll();
    }

    @AfterClass
    private void exit(){
        driver.quit();
    }

}
