import driver.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.businessObjects.SignInBO;
import pageObjects.businessObjects.TrainingListBO;
import resources.ConfProperties;

public class TrainingListPageTest{

    private TrainingListBO trainingListBO;

    @BeforeMethod
    private void setUp(){
        DriverFactory.initDriver();
        trainingListBO = new TrainingListBO();
        new SignInBO().openSignInPage()
                .loginValid(ConfProperties.getProperty("LOGIN_MAIL"), ConfProperties.getProperty("LOGIN_PASS"))
                .openTrainingListPage();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Skills’.")
    public void testTrainingsSearchBySkills(){
        trainingListBO.clearAllFilters()
                .activateCheckboxFilterBySkill(ConfProperties.getProperty("SKILL_JAVA"))
                .verifySearchWithBySkillWordOnly(ConfProperties.getProperty("SKILL_JAVA"))

                .deactivateCheckboxFilterBySkill(ConfProperties.getProperty("SKILL_JAVA"))
                .activateCheckboxFilterBySkill(ConfProperties.getProperty("SKILL_RUBY"))
                .verifyInfoMessageDisplayed();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Locations’.")
    public void testTrainingsSearchByLocations(){
        trainingListBO.clearAllFilters()
                .activateCheckboxFilterByCity(ConfProperties.getProperty("COUNTRY_UKRAINE"), ConfProperties.getProperty("CITY_LVIV"))
                .verifySearchWithCountryAndMultiLocation(ConfProperties.getProperty("COUNTRY_UKRAINE"));
    }

    @AfterMethod
    private void afterTest(){
        DriverFactory.quitDriver();
    }
}
