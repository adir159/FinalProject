package workflows;


import extensions.MobileActions;
import io.qameta.allure.Step;
import utilities.commonOps;

public class MobileFlows extends commonOps {

    @Step("Business flow: Fill form and calculate mortgage")
    public static void calculateMortgage(String amount, String term, String rate){
        MobileActions.updateText(mortgageMain.txt_amount, amount);
        MobileActions.updateText(mortgageMain.txt_term, term);
        MobileActions.updateText(mortgageMain.txt_rate, rate);
        MobileActions.tap(mortgageMain.btn_Calc);

    }

}
