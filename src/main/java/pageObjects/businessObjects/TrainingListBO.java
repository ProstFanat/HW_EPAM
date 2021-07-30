package pageObjects.businessObjects;

import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.TrainingListPage;

public class TrainingListBO {
    private TrainingListPage trainingListPage;
    private HomePage homePage;

    public TrainingListBO(){
        trainingListPage = new TrainingListPage();
        homePage = new HomePage();
    }

    public TrainingListBO openTrainingListPage(){
        if(homePage.isRightUserNameDisplayed()){
            homePage.clickBtnTrainingList();
        }
        return this;
    }

    public TrainingListBO activateCheckboxFilterByJava(){
        trainingListPage.clickOnSearchInput()
                .clickBtnBySkills()
                .setCheckboxSearchByJava(true)
                .clickOnSearchInput();
        return this;
    }

    public TrainingListBO deactivateCheckboxFilterByJava(){
        trainingListPage.clickOnSearchInput()
                .clickBtnBySkills()
                .setCheckboxSearchByJava(false)
                .clickOnSearchInput();
        return this;
    }

    public TrainingListBO activateCheckboxFilterByRuby(){
        trainingListPage.clickOnSearchInput()
                .clickBtnBySkills()
                .setCheckboxSearchByRuby(true)
                .clickOnSearchInput();
        return this;
    }

    public TrainingListBO deactivateCheckboxFilterByRuby(){
        trainingListPage.clickOnSearchInput()
                .clickBtnBySkills()
                .setCheckboxSearchByRuby(false)
                .clickOnSearchInput();
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
}
