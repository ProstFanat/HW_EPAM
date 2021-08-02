import org.testng.annotations.Test;
import pageObjects.businessObjects.BlogBO;
import pageObjects.businessObjects.SignInBO;
import resources.ConfProperties;

public class BlogPageTest extends BaseTest{

    @Test(description = "Verify links on ‘Blog’ Page ")
    private void testLinksOnPage(){
        //TODO софт асёрты
        new SignInBO()
                .openSignInPage()
                .login(ConfProperties.getProperty("LOGIN_MAIL"),ConfProperties.getProperty("LOGIN_PASS"));
        new BlogBO()
                .openBlogPage()
                .verifyAllLinksDisplayed();
    }
}
