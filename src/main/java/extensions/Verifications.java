package extensions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sikuli.script.FindFailed;
import org.xml.sax.SAXException;
import utilities.commonOps;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.fail;

public class Verifications extends commonOps {

    @Step("Verify text in elements")
    public static void VerifyTextInElement(WebElement elem, String expected)
    {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertEquals(elem.getText(),expected);
    }
    @Step("Verify number of elements")
    public static void NumberOfElements(List<WebElement> elem, int Expected){
        wait.until(ExpectedConditions.visibilityOf(elem.get(elem.size()-1)));
        assertEquals(elem.size(),Expected);
    }

    @Step("Verify visibility of elements (soft assertion)")
    public static void VisibilityOfElements(List<WebElement> elems){
        for (WebElement elem: elems) {
            softAssert.assertTrue(elem.isDisplayed(),"Sorry" + elem.getText() + "not displayed");
        }
        softAssert.assertAll("Some elements are not displayed");
    }

    @Step("Verify Element Visually")
    public static void visualElement(String expectedImageName) throws ParserConfigurationException, IOException, SAXException {
        try {
            screen.find(getData("ImageRepo") + expectedImageName + ".png");
        } catch (FindFailed findFailed) {
            System.out.println("Error comparing Image File:" + findFailed);
            fail("Error comparing Image File:" + findFailed);
        }
    }
    @Step("Veirfy Element Displayed")
    public static void existanceOfElement(List<WebElement> elements)
    {
        assertTrue(elements.size() > 0);
    }

    @Step("Veirfy Element Not Displayed")
    public static void NonExistanceOfElement(List<WebElement> elements)
    {
        assertFalse(elements.size() > 0);
    }
    @Step("Verify text with text")
    public static void verifyText(String actual, String expected){
        assertEquals(actual, expected);
    }
    @Step("Verify number with number")
    public static void verifyNumber(int actual, int expected){
        assertEquals(actual, expected);
    }

}
