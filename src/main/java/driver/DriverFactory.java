package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import resources.ConfProperties;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;

    protected void initDriver(){
        System.setProperty(ConfProperties.getProperty("CHROME_NAME"), ConfProperties.getProperty("CHROME_DRIVER_LOCATION"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfProperties.getProperty("IMPLICITLY_WAIT_VALUE")), TimeUnit.SECONDS);
    }

    public static WebDriver getDriver(){
        return driver;
    }

    protected void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
