import driver.DriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest{

    @BeforeClass
    public void setup(){
        DriverFactory.initDriver();
    }

    @AfterClass
    public void exit(){
        DriverFactory.quitDriver();
    }
}
