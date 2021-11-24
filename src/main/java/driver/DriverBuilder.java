package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import resources.ConfProperties;

import java.util.concurrent.TimeUnit;

public class DriverBuilder {
    private static final Logger LOG = Logger.getLogger(DriverBuilder.class);
    private static WebDriver driver;

    //TODO Пример Билдера

    public DriverBuilder setBrowserName(String browser){
        switch (browser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                LOG.info("Set system property: 'firefox'");
                driver = new FirefoxDriver();
                LOG.info("Driver started");
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                LOG.info(String.format("Set system property: '%s' , '%s'", ConfProperties.getProperty("CHROME_NAME"), ConfProperties.getProperty("CHROME_DRIVER_LOCATION")));
                driver = new ChromeDriver();
                LOG.info("Driver started");
            }
        }
        return this;
    }

    public DriverBuilder setWindowMaximize(){
        driver.manage().window().maximize();
        LOG.info("Driver set maximize");
        return this;
    }

    public DriverBuilder setImplicitlyWait(int seconds){
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        LOG.info(String.format("Set Implicitly wait %s second", seconds));
        return this;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
