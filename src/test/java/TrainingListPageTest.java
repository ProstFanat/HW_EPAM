import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.businessObjects.SignInBO;
import pageObjects.businessObjects.TrainingListBO;
import resources.ConfProperties;

public class TrainingListPageTest extends BaseTest {

    private TrainingListBO trainingListBO;

    @BeforeClass
    private void setUp(){
        trainingListBO = new TrainingListBO();
        new SignInBO().openSignInPage()
                .loginValid(ConfProperties.getProperty("LOGIN_MAIL"), ConfProperties.getProperty("LOGIN_PASS"))
                .openTrainingListPage();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Skills’.")
    public void testTrainingsSearchBySkills(){
        trainingListBO.clearAllFilters()
                .activateCheckboxFilterBySkill("Java")
                .verifySearchWithBySkillWordOnly("Java")

                .deactivateCheckboxFilterBySkill("Java")
                .activateCheckboxFilterBySkill("Ruby")
                .verifyInfoMessageDisplayed();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Locations’.")
    public void testTrainingsSearchByLocations(){
        trainingListBO.clearAllFilters()
                .activateCheckboxFilterByCity("Ukraine", "Lviv")
                .verifySearchWithCountryAndMultiLocation("Ukraine");
    }
}
