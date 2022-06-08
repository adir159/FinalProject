package sanity;


import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.commonOps;
import workflows.MobileFlows;
import workflows.WebFlows;


@Listeners(utilities.Lisenters.class)
public class MortgageMobile extends commonOps {

    @Test(description = "Test01 - Verify Mortgage")
    @Description("This test fills in mortgage and calculate repayment")
    public static void test01_VerifyMortgage() {
        MobileFlows.calculateMortgage("1000", "3", "4");
      Verifications.VerifyTextInElement(mortgageMain.txt_repayment,"£30.03");
    }
}
