package sanity;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import utilities.commonOps;
import workflows.WebFlows;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Listeners(utilities.Lisenters.class)
public class grafanaWeb extends commonOps {

    @Test(description = "Test01 - Verify Header")
    @Description("This test login and verifies the main title")
    public static void test01_VerifyHeader() throws ParserConfigurationException, IOException, SAXException {
        WebFlows.log(getData("Username"),getData( "Password"));
        Verifications.VerifyTextInElement(grafanaMain.head_title, "Welcome to Grafana");
    }

    @Test(description = "Test02 - Verify Default Users")
    @Description("This test verifies the default users")
    public static void test02_VerifyDefaultUsers() {
        UIActions.mouse_hover(grafanaLeftMenu.btn_server, grafanaServerAdmin.link_users);
        Verifications.NumberOfElements(grafanaServerAdminMain.rows, 1);
    }

    @Test(description = "Test03 - Verify New User")
    @Description("This test verifies a new user has been added")
    public static void test03_VerifyNewUser() {
        UIActions.mouse_hover(grafanaLeftMenu.btn_server, grafanaServerAdmin.link_users);
        WebFlows.createNewUser("Adir", "Adir1234", "adir@gmail.com", "adirbeg159");
        Verifications.NumberOfElements(grafanaServerAdminMain.rows, 2);
    }

    @Test(description = "Test04 - Verify Deletion of user")
    @Description("This test verifies the deletion of a user")
    public static void test04_VerifyUserDeletion() {
        UIActions.mouse_hover(grafanaLeftMenu.btn_server, grafanaServerAdmin.link_users);
        WebFlows.deleteLastUser();
        Verifications.NumberOfElements(grafanaServerAdminMain.rows, 1);
    }

    @Test(description = "Test05 - Verify Main Guide lines")
    @Description("This test verifies That the main guidelines are displayed(using soft assertion")
    public static void test05_VerifyMainGuidelines() {
        Verifications.VisibilityOfElements(grafanaMain.list_headGuideLines);
    }

    @Test(description = "Test06 - Verify Avatar Icon")
    @Description("This test verifies the avatar image using Sikuli tool")
    public static void test06_VerifyAvatarIcon() throws ParserConfigurationException, IOException, SAXException {
        Verifications.visualElement("GrafanaAvatar");
    }

    @Test(description = "Test07 - Search Users", dataProvider = "data-provider-users",dataProviderClass = utilities.manageDDT.class)
    @Description("This test searches users in a table using data driven testing")
    public static void test07_SearchUsers(String user, String shouldExist) {
        UIActions.mouse_hover(grafanaLeftMenu.btn_server, grafanaServerAdmin.link_users);
        WebFlows.searchAndVerifyUser(user,shouldExist);
    }
}
