package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TrainingListPage extends BasePage {
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
        return this;
    }

    public TrainingListPage clickBtnBySkills(){
        waitToBeClickable(5000, btnBySkills);
        btnBySkills.click();
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
            }
        } else if(!value){
            if(checkboxSearchByJava.isSelected()){
                checkboxSearchByJava.click();
            }
        }
        return this;
    }

    public TrainingListPage setCheckboxSearchByRuby(Boolean value){
        //waitToVisibilityOf(5000, checkboxSearchByRuby);
        if(value){
            if(!checkboxSearchByRuby.isSelected()){
                checkboxSearchByRuby.click();
            }
        } else if(!value){
            if(checkboxSearchByRuby.isSelected()){
                checkboxSearchByRuby.click();
            }
        }
        return this;
    }

    public boolean isTrainingListContainsOnlyJava(){
        return trainingsList == trainingsListJavaOnly;
    }

    public boolean isInfoMessageDisplayed(){
        waitForPageLoaded();
        return infoMessage.isDisplayed();
    }
}
