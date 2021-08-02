package decorator.elements;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pageObjects.BasePage;

public class Element {
    public static void click(WebElement element){
        try {
            new BasePage().waitToBeClickable(5000, element);
            element.click();
        } catch (NoSuchElementException exception){
            System.out.println(exception);
        }
    }
}
