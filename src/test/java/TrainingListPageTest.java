import driver.DriverFactory;
import org.testng.annotations.AfterClass;
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
    public void testTrainingsSearch(){
        trainingListBO.activateCheckboxFilterByJava()
                .verifySearchWithJavaWordOnly()

                .deactivateCheckboxFilterByJava()
                .activateCheckboxFilterByRuby()
                .verifyInfoMessageDisplayed();
    }
}
