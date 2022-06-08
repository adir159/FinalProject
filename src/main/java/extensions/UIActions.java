package extensions;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.commonOps;

import java.util.concurrent.TimeUnit;

public class UIActions extends commonOps {

    @Step("Click on element")
    public static void click(WebElement elem){
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }
    @Step("Update text element")
    public static void updateText(WebElement elem,String text) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        elem.sendKeys(text);}

    @Step("Insert key")
    public static void insertKey(WebElement elem, Keys value) {
    elem.sendKeys(value);
    }

    @Step("Update text element as human")
    public static void updateTextHumanSpeed(WebElement elem,String text) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        for (char ch : text.toCharArray())
        {
            Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
            elem.sendKeys(ch + "");
        };
    }

    @Step("Update dropdown Elements")
    public static void updateDropDown (WebElement elem, String text){
        wait.until(ExpectedConditions.visibilityOf(elem));
        Select DropDown = new Select(elem);
        DropDown.selectByVisibleText(text);
        }
        @Step("Mouse hover elements")
        public static void mouse_hover(WebElement elem1, WebElement elem2){
        action.moveToElement(elem1).moveToElement(elem2).click().build().perform();
        }
    @Step("Mouse hover elements")
    public static void mouse_hover(WebElement elem1) {
        action.moveToElement(elem1).click().build().perform();
    }
    }
