import org.testng.annotations.Test;
import pageObjects.businessObjects.BlogBO;
import pageObjects.businessObjects.SignInBO;

public class BlogPageTest extends BaseTest{

    @Test(description = "Verify links on ‘Blog’ Page ")
    private void testLinksOnPage(){
        BlogBO blogBO = new BlogBO();
        new SignInBO().openSignInPage()
                .login("prostfanat2001@gmail.com", "Mylife2001");
        blogBO.openBlogPage()
            .verifyAllLinksDisplayed();
    }
}
