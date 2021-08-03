import dataProvider.BlogLinks;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pageObjects.businessObjects.BlogBO;
import pageObjects.businessObjects.SignInBO;
import resources.ConfProperties;

public class BlogPageTest extends BaseTest{
    SoftAssert sa = new SoftAssert();

    @BeforeGroups("Links test")
    private void setUp(){
        new SignInBO().openSignInPage()
                .loginValid(ConfProperties.getProperty("LOGIN_MAIL"),ConfProperties.getProperty("LOGIN_PASS"))
                .openBlogPage();
    }

    @Test(dataProvider = "blog-links", dataProviderClass = BlogLinks.class, groups = {"Links test"}, description = "Verify links on ‘Blog’ Page ")
    private void testLinksOnPage(String link){
        new BlogBO().verifyLinkDisplayed(sa, link);
    }

}
