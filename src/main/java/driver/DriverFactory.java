package driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import resources.ConfProperties;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final Logger LOG = Logger.getLogger(DriverFactory.class);
    private static WebDriver driver;

    protected void initDriver(){
        System.setProperty(ConfProperties.getProperty("CHROME_NAME"), ConfProperties.getProperty("CHROME_DRIVER_LOCATION_LINUX"));
        LOG.info(String.format("Set system property: '%s' , '%s'", ConfProperties.getProperty("CHROME_NAME"), ConfProperties.getProperty("CHROME_DRIVER_LOCATION")));
        driver = new ChromeDriver();
        LOG.info("Driver started");
        driver.manage().window().maximize();
        LOG.info("Driver set maximize");
        driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfProperties.getProperty("IMPLICITLY_WAIT_VALUE")), TimeUnit.SECONDS);
        LOG.info("Set Implicitly wait 10 second");
    }

    public static WebDriver getDriver(){
        return driver;
    }

    protected void quitDriver(){
        if(driver != null){
            driver.quit();
            LOG.info("Driver was closed");
            driver = null;
        }
    }
}
