package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddNewUserPage {

    @FindBy(how = How.XPATH, using = "//input[@class='css-1bjepp-input-input']")
    public WebElement txt_name;

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    public WebElement txt_email;

    @FindBy(how = How.XPATH, using = "//input[@name='login']")
    public WebElement txt_user;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    public WebElement txt_password;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    public WebElement btn_create;
}
