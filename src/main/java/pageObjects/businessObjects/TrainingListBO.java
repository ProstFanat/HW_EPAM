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

    public TrainingListBO openTrainingListPage(){
        if(homePage.isRightUserNameDisplayed()){
            homePage.clickBtnTrainingList();
            LOG.info("'Training List' page opened");
        }
        return this;
    }

    public TrainingListBO activateCheckboxFilterByJava(){
        trainingListPage.clickOnSearchInput()
                .clickBtnBySkills()
                .setCheckboxSearchByJava(true)
                .clickOnSearchInput();
        LOG.info("'Training List' sorted by 'Java'");
        return this;
    }

    public TrainingListBO deactivateCheckboxFilterByJava(){
        trainingListPage.clickOnSearchInput()
                .clickBtnBySkills()
                .setCheckboxSearchByJava(false)
                .clickOnSearchInput();
        LOG.info("'Training List' unsorted by 'Java'");
        return this;
    }

    public TrainingListBO activateCheckboxFilterByRuby(){
        trainingListPage.clickOnSearchInput()
                .clickBtnBySkills()
                .setCheckboxSearchByRuby(true)
                .clickOnSearchInput();
        LOG.info("'Training List' sorted by 'Ruby'");
        return this;
    }

    public TrainingListBO deactivateCheckboxFilterByRuby(){
        trainingListPage.clickOnSearchInput()
                .clickBtnBySkills()
                .setCheckboxSearchByRuby(false)
                .clickOnSearchInput();
        LOG.info("'Training List' unsorted by 'Ruby'");
        return this;
    }

    public TrainingListBO activateCheckboxFilterByLviv(){
        trainingListPage.clickOnSearchInput()
                .clickBtnByLocations()
                .clickBtnUkraine()
                .setCheckboxByLviv(true)
                .clickOnSearchInput();
        LOG.info("'Training List' sorted by 'Ukraine - Lviv'");
        return this;
    }

    public TrainingListBO deactivateCheckboxFilterByLviv(){
        trainingListPage.clickOnSearchInput()
                .clickBtnByLocations()
                .clickBtnUkraine()
                .setCheckboxChooseAllCities(false)
                .setCheckboxByLviv(false)
                .clickOnSearchInput();
        LOG.info("'Training List' unsorted by 'Ukraine - Lviv'");
        return this;
    }

    public TrainingListBO clearAllFilters(){
        trainingListPage.clickBtnDeactivateSelectedSkills()
                .clickBtnDeactivateSelectedLocations();
        LOG.info("Clear Filters");
        return this;
    }

    public TrainingListBO verifySearchWithJavaWordOnly(){
        Assert.assertTrue(trainingListPage.isTrainingListContainsOnlyJava());
        return this;
    }

    public TrainingListBO verifyInfoMessageDisplayed(){
        Assert.assertTrue(trainingListPage.isInfoMessageDisplayed());
        return this;
    }

    public TrainingListBO verifySearchWithUkraineOrMultiLocation(){
        Assert.assertTrue(trainingListPage.isTrainingListContainsOnlyUkraineAndMultiLocation());
        return this;
    }
}
