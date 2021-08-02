package pageObjects;

import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public WebDriver driver;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println("Timeout waiting for Page Load Request to complete.");
        }
    }

    public void waitToVisibilityOf (int timeOut, WebElement element){
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitToBeClickable (int timeOut, WebElement element){
        try {
            new WebDriverWait(driver, timeOut).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions
                            .elementToBeClickable(element));
        } catch (TimeoutException e){
            System.out.println("Test failed. Time to wait is end " + e);
        }
    }

    public boolean existsElement(WebElement element) {
        try {
            element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
