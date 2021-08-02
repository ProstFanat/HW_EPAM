package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class BlogPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(BlogPage.class);

    @FindBy(xpath = "//div[@class= 'tab-nav']//a[contains(@ng-repeat, 'category')]//span")
    private List<WebElement> listOfLinks;

    public boolean isAllLinksDisplayed(){
        //TODO переделать под софт асёрты

        List<String> links = new ArrayList<>();
        links.add("News"); links.add("Real Stories"); links.add("Materials"); links.add("Hard Skills"); links.add("Soft Skills"); links.add("Events");
        boolean isDisplayed = false;
        int result = 0;
        for(int i = 0; i < links.size(); i++){
            if(links.get(i).equalsIgnoreCase(listOfLinks.get(i).getText().trim())){
                result++;
            }
        }
        if(result == links.size()){
            isDisplayed = true;
        }
        LOG.info(String.format("Is All Links displayed: '%s'", isDisplayed));
        return isDisplayed;
    }

}
