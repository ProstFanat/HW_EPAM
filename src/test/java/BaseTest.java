import driver.DriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest extends DriverFactory {

    @BeforeClass
    public void setup(){
        initDriver();
    }

    @AfterClass
    public void exit(){
        quitDriver();
    }
}
