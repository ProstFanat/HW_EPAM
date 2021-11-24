package driver;

import org.openqa.selenium.WebDriver;

public class DriverDirector {
    public WebDriver getDriverWithDefaultParams(){
         return new DriverBuilder()
                .setBrowserName("chrome")
                .setWindowMaximize()
                .setImplicitlyWait(10)
                 .getDriver();
    }
}
