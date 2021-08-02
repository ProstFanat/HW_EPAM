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
                .login(ConfProperties.getProperty("LOGIN_MAIL"), ConfProperties.getProperty("LOGIN_PASS"));
        trainingListBO.openTrainingListPage();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Skills’.")
    public void testTrainingsSearchBySkills(){
        trainingListBO.clearAllFilters()
                //TODO сделать так чтобы можно было выбрать любой из фильтров
                .activateCheckboxFilterByJava()
                .verifySearchWithJavaWordOnly()

                .deactivateCheckboxFilterByJava()
                .activateCheckboxFilterByRuby()
                .verifyInfoMessageDisplayed();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Locations’.")
    public void testTrainingsSearchByLocations(){
        trainingListBO.clearAllFilters()
                //TODO сделать чтобы можно было юзать для любого города
                .activateCheckboxFilterByLviv()
                .verifySearchWithUkraineOrMultiLocation();
    }
}
