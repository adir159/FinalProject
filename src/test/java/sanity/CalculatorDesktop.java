package sanity;

import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.commonOps;
import workflows.DesktopFlows;



@Listeners(utilities.Lisenters.class)
public class CalculatorDesktop extends commonOps {
    @Test(description = "Test01 - Verify addition command")
    @Description("This test Add and verify addition command")
    public static void test01_AddAndVerifyAddCommand() {
        DesktopFlows.CalcAdd();
        Verifications.VerifyTextInElement(CalcMain.field_Results,"Display is 9");
    }
}
