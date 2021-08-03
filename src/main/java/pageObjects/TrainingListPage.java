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

    @FindBy(xpath = "//div[contains(text(), 'By locations') and contains(@class, 'navigation-item')]")
    private WebElement btnByLocations;

    @FindBy(xpath = "//input[@ng-checked = 'isCountryChecked(activeCountryTab)']//following-sibling::span")
    private WebElement checkboxChooseAllCities;

    @FindBy(xpath = "//div[contains(@class, 'training-list__desktop')]//a[@class = 'training-item__link']")
    private List<WebElement> trainingsList;

    @FindBy(xpath = "//span[contains(text() , 'No training are available.')]")
    private WebElement infoMessage;

    @FindBy(xpath = "//span[@ng-click = 'clearAllLocations(totalLocationList)']")
    private WebElement btnDeactivateFiltersByLocations;

    @FindBy(xpath = "//span[@ng-click = 'clearAllSkills(selectedSkills)']")
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

    public TrainingListPage setCheckboxSearchBySkill(String skill, Boolean value){
        Checkbox.setSelectedInvlaidCheckBox(driver.findElement(By.xpath(String.format("//div[contains(@class, 'location__skills')]//label[normalize-space()='%s']/span", skill))), value, skill);
        return this;
    }

    public TrainingListPage clickBtnByLocations(){
        Element.click(btnByLocations);
        LOG.info("'By Locations' button clicked");
        return this;
    }

    public TrainingListPage selectCountry(String country){
        Element.click(driver.findElement(By.xpath(String.format("//div[@class= 'location__countries']//div[contains(text(), '%s')]", country))));
        LOG.info(String.format("'%s' button clicked",country));
        return this;
    }

    public TrainingListPage setCheckboxChooseAllCities(Boolean value){
        Checkbox.setSelectedInvlaidCheckBox(checkboxChooseAllCities, value, "Choose All Cities");
        return this;
    }

    public TrainingListPage setCheckboxByCity(String city, Boolean value){
        Checkbox.setSelectedInvlaidCheckBox(driver.findElement(By.xpath(String.format("//div[@class= 'location__cities']//label[normalize-space()='%s']/span", city))), value, city);
        return this;
    }

    public boolean isTrainingListContainsOnlySkill(String skill){
        boolean isContains = trainingsList.size() ==
                driver.findElements(By.xpath(String.format("//div[contains(@class, 'training-list__desktop')]//a[@class = 'training-item__link']//div[contains(text(), '%s')]", skill))).size();
        LOG.info(String.format("Is 'Training List' contains only %s': '%s'", skill, isContains));
        return isContains;
    }

    public boolean isTrainingListContainsOnlyCountry(String country){
        boolean isContains = trainingsList.size() ==
                driver.findElements(By.xpath(String.format("//div[contains(@class, 'training-list__desktop')]//*[self::div or self::span][contains(text(), '%s')]",country))).size();
        LOG.info(String.format("Is 'Training List' contains only %s': '%s'", country, isContains));
        return isContains;
    }

    public boolean isTrainingListContainsOnlyCountryAndMultiLocation(String country){
        boolean isContains = trainingsList.size() ==
                driver.findElements(By.xpath(String.format("//div[contains(@class, 'training-list__desktop')]//*[self::div or self::span][contains(text(), '%s') or contains(text(), 'Multi-location')]",country))).size();
        LOG.info(String.format("Is 'Training List' contains only %s and Multi-location': '%s'", country, isContains));
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
