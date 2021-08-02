import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.businessObjects.SignInBO;
import pageObjects.businessObjects.TrainingListBO;

public class TrainingListPageTest extends BaseTest {

    private TrainingListBO trainingListBO;

    @BeforeClass
    private void setUp(){
        trainingListBO = new TrainingListBO();
        new SignInBO().openSignInPage()
                .login("prostfanat2001@gmail.com", "Mylife2001");
        trainingListBO.openTrainingListPage();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Skills’.")
    public void testTrainingsSearchBySkills(){
        trainingListBO.clearAllFilters()
                .activateCheckboxFilterByJava()
                .verifySearchWithJavaWordOnly()

                .deactivateCheckboxFilterByJava()
                .activateCheckboxFilterByRuby()
                .verifyInfoMessageDisplayed();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Locations’.")
    public void testTrainingsSearchByLocations(){
        trainingListBO.clearAllFilters()
                .activateCheckboxFilterByLviv()
                .verifySearchWithUkraineOrMultiLocation();
    }
}
