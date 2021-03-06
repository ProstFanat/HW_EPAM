package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import resources.ConfProperties;

import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final Logger LOG = Logger.getLogger(DriverFactory.class);
    private static WebDriver driver;

    public static void initDriver(String browser){
        //TODO Это пример пройстого фабричного метода
        if(browser == null) browser = "chrome";
        switch (browser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                LOG.info("Set system property: 'firefox'");
                driver = new FirefoxDriver();
                LOG.info("Driver started");
                setDefaultDriverConfiguration(driver);
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                LOG.info(String.format("Set system property: '%s' , '%s'", ConfProperties.getProperty("CHROME_NAME"), ConfProperties.getProperty("CHROME_DRIVER_LOCATION")));
                driver = new ChromeDriver();
                LOG.info("Driver started");
                setDefaultDriverConfiguration(driver);
            }
        }
    }

    public static WebDriver getDriver(){
        return driver;
    }

    private static WebDriver setDefaultDriverConfiguration(WebDriver driver){
        driver.manage().window().maximize();
        LOG.info("Driver set maximize");
        driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfProperties.getProperty("IMPLICITLY_WAIT_VALUE")), TimeUnit.SECONDS);
        LOG.info("Set Implicitly wait 10 second");
        return driver;
    }

    public static void quitDriver(){
        if(driver != null){
            driver.quit();
            LOG.info("Driver was closed");
            driver = null;
        }
    }
}
