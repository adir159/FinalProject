package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ServerAdminMainPage  {
    @FindBy(how = How.XPATH, using = "//table[@class='filter-table form-inline filter-table--hover']/tbody/tr")
    public List<WebElement> rows;

    @FindBy(how = How.CLASS_NAME, using ="css-u6ysb0-button")
    public WebElement btn_newUser;

    @FindBy(how = How.CSS, using ="input[placeholder='Search user by login, email or name']")
    public WebElement txt_search;
}
