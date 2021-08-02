package pageObjects;

import decorator.elements.Checkbox;
import decorator.elements.Element;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TrainingListPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(TrainingListPage.class);

    @FindBy(xpath = "//input[@name = 'training-filter-input']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[contains(text(), 'By skills') and contains(@class, 'navigation-item')]")
    private WebElement btnBySkills;

    @FindBy(xpath = "//div[contains(@class, 'location__skills')]//label[normalize-space()='Java']/span")
    private WebElement checkboxSearchByJava;

    @FindBy(xpath = "//div[contains(@class, 'location__skills')]//label[normalize-space()='Ruby']/span")
    private WebElement checkboxSearchByRuby;

    @FindBy(xpath = "//div[contains(text(), 'By locations') and contains(@class, 'navigation-item')]")
    private WebElement btnByLocations;

    @FindBy(xpath = "//div[@class= 'location__countries']//div[contains(text(), 'Ukraine')]")
    private WebElement btnUkraine;

    @FindBy(xpath = "//input[@ng-checked = 'isCountryChecked(activeCountryTab)']//following-sibling::span")
    private WebElement checkboxChooseAllCities;

    @FindBy(xpath = "//div[@class= 'location__cities']//label[normalize-space()='Lviv']/span")
    private WebElement checkboxByLviv;

    @FindBy(xpath = "//div[contains(@class, 'training-list__desktop')]//a[@class = 'training-item__link']")
    private List<WebElement> trainingsList;

    @FindBy(xpath = "//div[contains(@class, 'training-list__desktop')]//a[@class = 'training-item__link']//div[contains(text(), 'Java')]")
    private List<WebElement> trainingsListJavaOnly;

    @FindBy(xpath = "//div[contains(@class, 'training-list__desktop')]//*[self::div or self::span][contains(text(), 'Ukraine') or contains(text(), 'Multi-location')]")
    private List<WebElement> trainingsListUkraineAndMultiLocationOnly;

    @FindBy(xpath = "//span[contains(text() , 'No training are available.')]")
    private WebElement infoMessage;

    @FindBy(xpath = "//div[contains(text(), 'Selected locations')]//span")
    private WebElement btnDeactivateFiltersByLocations;

    @FindBy(xpath = "//div[@ng-if = 'selectedSkills.length > 0']//span[@ng-click = 'clearAllSkills(selectedSkills)']")
    private WebElement btnDeactivateFiltersBySkills;

    public TrainingListPage clickOnSearchInput(){
        Element.click(searchInput);
        LOG.info("Search input clicked");
        return this;
    }

    public TrainingListPage clickBtnBySkills(){
        Element.click(btnBySkills);
        LOG.info("'By skills' button clicked");
        return this;
    }

    public TrainingListPage setCheckboxSearchByJava(Boolean value){
        Checkbox.setSelected(checkboxSearchByJava, value, "Java");
        return this;
    }

    public TrainingListPage setCheckboxSearchByCourse(String course, Boolean value){
        Checkbox.setSelected(driver.findElement(By.xpath(String.format("//div[contains(@class, 'location__skills')]//label[normalize-space()='%s']/span", course))), value, "Java");
        return this;
    }

    public TrainingListPage setCheckboxSearchByRuby(Boolean value){
        Checkbox.setSelected(checkboxSearchByRuby, value, "Ruby");
        return this;
    }

    public TrainingListPage clickBtnByLocations(){
        Element.click(btnByLocations);
        LOG.info("'By Locations' button clicked");
        return this;
    }

    public TrainingListPage clickBtnUkraine(){
        Element.click(btnUkraine);
        LOG.info("'Ukraine' button clicked");
        return this;
    }

    public TrainingListPage setCheckboxChooseAllCities(Boolean value){
        Checkbox.setSelected(checkboxChooseAllCities, value, "Choose All Cities");
        return this;
    }

    public TrainingListPage setCheckboxByLviv(Boolean value){
        Checkbox.setSelected(checkboxByLviv, value, "Lviv");
        return this;
    }

    public boolean isTrainingListContainsOnlyJava(){
        boolean isContains = trainingsList.size() == trainingsListJavaOnly.size();
        LOG.info(String.format("Is 'Training List' contains only java': '%s'", isContains));
        return isContains;
    }

    public boolean isTrainingListContainsOnlyUkraineAndMultiLocation(){
        boolean isContains = trainingsList.size() == trainingsListUkraineAndMultiLocationOnly.size();
        LOG.info(String.format("Is 'Training List' contains only Ukraine and Multi-location': '%s'", isContains));
        return isContains;
    }

    public boolean isInfoMessageDisplayed(){
        waitForPageLoaded();
        boolean isDisplayed = infoMessage.isDisplayed();
        LOG.info(String.format("Is 'Info Message' displayed': '%s'", isDisplayed));
        return isDisplayed;
    }

    public TrainingListPage clickBtnDeactivateSelectedLocations(){
        if(existsElement(btnDeactivateFiltersByLocations)){
            Element.click(btnDeactivateFiltersByLocations);
            LOG.info("'Deactivate selected locations' button clicked");
        }
        return this;
    }

    public TrainingListPage clickBtnDeactivateSelectedSkills(){
        if(existsElement(btnDeactivateFiltersBySkills)){
            Element.click(btnDeactivateFiltersBySkills);
            LOG.info("'Deactivate selected skills' button clicked");
        }
        return this;
    }
}
