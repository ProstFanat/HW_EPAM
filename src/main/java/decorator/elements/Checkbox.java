package decorator.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import pageObjects.BasePage;


public class Checkbox {
    private static Logger LOG = Logger.getLogger(Checkbox.class);
    public static void setSelected(WebElement element, Boolean value, String name){
        new BasePage().waitToBeClickable(5000, element);
        if(value){
            if(!element.isSelected()){
                element.click();
                LOG.info(String.format("'%s' checkbox activated", name));
            }
        } else {
            if(element.isSelected()){
                element.click();
                LOG.info(String.format("'%s' checkbox deactivated", name));
            }
        }
    }

    public static void setSelectedInvlaidCheckBox(WebElement element, Boolean value, String name){
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
