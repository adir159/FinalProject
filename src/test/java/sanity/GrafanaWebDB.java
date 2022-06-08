package sanity;

import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utilities.commonOps;
import workflows.WebFlows;




@Listeners(utilities.Lisenters.class)
public class GrafanaWebDB extends commonOps {

    @Test(description = "Test01 - Login to grafana with DB credentials")
    @Description("This test Login to grafana with DB credentials")
    public static void test01_LoginDB(){
        WebFlows.logDB();
        Verifications.VerifyTextInElement(grafanaMain.head_title, "Welcome to Grafana");
    }

}
