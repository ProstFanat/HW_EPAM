package pageObjects.businessObjects;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageObjects.BlogPage;

public class BlogBO {
    private BlogPage blogPage;

    public BlogBO(){
        blogPage = new BlogPage();
    }

    public BlogBO verifyLinkDisplayed(SoftAssert sa, String link){
        Assert.assertTrue(blogPage.isLinkDisplayed(link), String.format("Test failed on link - %s", link));
        return this;
    }


}
