import dataProvider.BlogLinks;
import driver.DriverFactory;
import org.bouncycastle.util.test.TestResult;
import org.testng.annotations.*;
import pageObjects.businessObjects.BlogBO;
import pageObjects.businessObjects.SignInBO;
import resources.ConfProperties;

public class BlogPageTest extends BaseTest {

    @BeforeClass
    public void setup(){
        DriverFactory.initDriver(System.getProperty("browser"));
    }

    @BeforeGroups("Links test")
    private void setUp(){
        new SignInBO().openSignInPage()
                .loginValid(ConfProperties.getProperty("LOGIN_MAIL"),ConfProperties.getProperty("LOGIN_PASS"))
                .openBlogPage();
    }

    @Test(dataProvider = "blog-links", dataProviderClass = BlogLinks.class, groups = {"Links test"}, description = "Verify links on ‘Blog’ Page ")
    private void testLinksOnPage(String link){
        new BlogBO().verifyLinkDisplayed(link);
    }

}
