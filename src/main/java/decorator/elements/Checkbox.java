package decorator.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import pageObjects.BasePage;


public class Checkbox {
    public static void setSelected(WebElement element, Boolean value, String name){
        Logger LOG = Logger.getLogger(Checkbox.class);
        new BasePage().waitToBeClickable(5000, element);
        if(value){
            if(!element.isSelected()){
                element.click();
                LOG.info(String.format("'%s' checkbox activated", name));
            }
        } else {
            element.click();
            LOG.info(String.format("'%s' checkbox deactivated", name));
        }
    }
}
