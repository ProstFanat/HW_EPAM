package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class BlogPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(BlogPage.class);

    public boolean isLinkDisplayed(String link){
        try {
            boolean isDisplayed = driver.findElement(By.xpath(String.format("//div[@class= 'tab-nav']//a[@href = '#!/News/Category/%s']", link))).isDisplayed();
            LOG.info(String.format("Is link '%s' displayed: %s", link, isDisplayed));
            return isDisplayed;
        } catch (NoSuchElementException exception) {
            return false;
        }

    }

}
