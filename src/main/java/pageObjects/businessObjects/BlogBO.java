package pageObjects.businessObjects;

import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObjects.BlogPage;
import pageObjects.HomePage;

public class BlogBO {
    private BlogPage blogPage;
    private HomePage homePage;
    private static final Logger LOG = Logger.getLogger(BlogBO.class);

    public BlogBO(){
        blogPage = new BlogPage();
        homePage = new HomePage();
    }

    public BlogBO openBlogPage(){
        if(homePage.isRightUserNameDisplayed()){
            homePage.clickBtnBlogPage();
            LOG.info("'Blog' page opened");
        }
        return this;
    }

    public BlogBO verifyAllLinksDisplayed(){
        Assert.assertTrue(blogPage.isAllLinksDisplayed());
        return this;
    }


}
