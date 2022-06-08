package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EditUserPage {
    @FindBy(how = How.XPATH, using = "(//button[@class='css-1scyk9l-button']/span/span)[1]")
    public WebElement btn_deleteUser;

    @FindBy(how = How.XPATH, using = "(//div[@class='css-1nrg97p'])[1]")
    public WebElement btn_confirmDeletionUser;
}
