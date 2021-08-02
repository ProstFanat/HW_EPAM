package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TrainingListPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(TrainingListPage.class);

    @FindBy(xpath = "//input[@name = 'training-filter-input']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@ng-click = " + (char) 34 + "changeTab('Skill')" + (char) 34 + "]")
    private WebElement btnBySkills;

    @FindBy(xpath = "//div[@ng-show = " + (char) 34 + "checkTab('Skill')" + (char) 34 + "]//li[16]//input")
    private WebElement checkboxSearchByJava;

    @FindBy(xpath = "//div[@ng-show = " + (char) 34 + "checkTab('Skill')" + (char) 34 + "]//li[23]//input")
    private WebElement checkboxSearchByRuby;

    @FindBy(xpath = "//div[contains(@class, 'training-list__desktop')]//a[@class = 'training-item__link']")
    private List<WebElement> trainingsList;

    @FindBy(xpath = "//div[contains(@class, 'training-list__desktop')]//a[@class = 'training-item__link']//div[contains(text(), 'Java')]")
    private List<WebElement> trainingsListJavaOnly;

    @FindBy(xpath = "//span[contains(text() , 'No training are available.')]")
    private WebElement infoMessage;

    public TrainingListPage clickOnSearchInput(){
        waitToBeClickable(5000, searchInput);
        searchInput.click();
        LOG.info("Search input clicked");
        return this;
    }

    public TrainingListPage clickBtnBySkills(){
        waitToBeClickable(5000, btnBySkills);
        btnBySkills.click();
        LOG.info("'By skills' button clicked");
        return this;
    }

    public TrainingListPage setCheckboxSearchByJava(Boolean value){
        System.out.println(checkboxSearchByJava.isEnabled());
        System.out.println(checkboxSearchByJava.isDisplayed());
        System.out.println(checkboxSearchByJava.isSelected());
        waitToBeClickable(5000, checkboxSearchByJava);
        if(value){
            if(!checkboxSearchByJava.isSelected()){
                checkboxSearchByJava.click();
                LOG.info("'By Java' checkbox activated");
            }
        } else {
            if(checkboxSearchByJava.isSelected()){
                checkboxSearchByJava.click();
                LOG.info("'By Java' checkbox deactivated");
            }
        }
        return this;
    }

    public TrainingListPage setCheckboxSearchByRuby(Boolean value){
        if(value){
            if(!checkboxSearchByRuby.isSelected()){
                checkboxSearchByRuby.click();
                LOG.info("'By Ruby' checkbox activated");
            }
        } else {
            if(checkboxSearchByRuby.isSelected()){
                checkboxSearchByRuby.click();
                LOG.info("'By Ruby' checkbox deactivated");
            }
        }
        return this;
    }

    public boolean isTrainingListContainsOnlyJava(){
        boolean isContains = trainingsList == trainingsListJavaOnly;
        LOG.info(String.format("Is 'Training List' contains only java': '%s'", isContains));
        return isContains;
    }

    public boolean isInfoMessageDisplayed(){
        waitForPageLoaded();
        boolean isDisplayed = infoMessage.isDisplayed();
        LOG.info(String.format("Is 'Info Message' displayed': '%s'", isDisplayed));
        return isDisplayed;
    }
}
