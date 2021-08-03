package pageObjects.businessObjects;

import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.TrainingListPage;

public class TrainingListBO {
    private static final Logger LOG = Logger.getLogger(TrainingListBO.class);
    private TrainingListPage trainingListPage;
    private HomePage homePage;

    public TrainingListBO(){
        trainingListPage = new TrainingListPage();
        homePage = new HomePage();
    }

    public TrainingListBO activateCheckboxFilterBySkill(String skill){
        trainingListPage.clickOnSearchInput()
                .clickBtnBySkills()
                .setCheckboxSearchBySkill(skill, true)
                .clickOnSearchInput();
        LOG.info(String.format("'Training List' sorted by '%s'", skill));
        return this;
    }

    public TrainingListBO deactivateCheckboxFilterBySkill(String skill){
        trainingListPage.clickOnSearchInput()
                .clickBtnBySkills()
                .setCheckboxSearchBySkill(skill,false)
                .clickOnSearchInput();
        LOG.info(String.format("'Training List' unsorted by '%s'", skill));
        return this;
    }

    public TrainingListBO activateCheckboxFilterByCity(String country, String city){
        trainingListPage.clickOnSearchInput()
                .clickBtnByLocations()
                .selectCountry(country)
                .setCheckboxByCity(city,true)
                .clickOnSearchInput();
        LOG.info(String.format("'Training List' sorted by '%s - %s'", country, city));
        return this;
    }

    public TrainingListBO deactivateCheckboxFilterByCity(String country, String city){
        trainingListPage.clickOnSearchInput()
                .clickBtnByLocations()
                .selectCountry(country)
                .setCheckboxChooseAllCities(false)
                .setCheckboxByCity(city,false)
                .clickOnSearchInput();
        LOG.info(String.format("'Training List' unsorted by '%s - %s'", country, city));
        return this;
    }

    public TrainingListBO clearAllFilters(){
        trainingListPage.clickBtnDeactivateSelectedSkills()
                .clickBtnDeactivateSelectedLocations();
        LOG.info("Clear Filters");
        return this;
    }

    public TrainingListBO verifySearchWithBySkillWordOnly(String skill){
        Assert.assertTrue(trainingListPage.isTrainingListContainsOnlySkill(skill));
        return this;
    }

    public TrainingListBO verifyInfoMessageDisplayed(){
        Assert.assertTrue(trainingListPage.isInfoMessageDisplayed());
        return this;
    }

    public TrainingListBO verifySearchWithCountry(String country){
        Assert.assertTrue(trainingListPage.isTrainingListContainsOnlyCountry(country));
        return this;
    }

    public TrainingListBO verifySearchWithCountryAndMultiLocation(String country){
        Assert.assertTrue(trainingListPage.isTrainingListContainsOnlyCountryAndMultiLocation(country));
        return this;
    }
}
