package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class MainPage {
    @FindBy(how = How.XPATH, using = "//div[@class='css-15xxblz']/h1")
    public WebElement head_title;

    @FindBy(how = How.XPATH, using = "//div[@class='css-1vjouat']/a")
    public List<WebElement> list_headGuideLines;
}
