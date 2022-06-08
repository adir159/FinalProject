package workflows;

import extensions.UIActions;
import io.qameta.allure.Step;
import utilities.commonOps;

public class DesktopFlows extends commonOps {
    @Step("Calculate addition: 1 plus 8")
    public static void CalcAdd(){
        UIActions.click(CalcMain.btn_clear);
        UIActions.click(CalcMain.btn_one);
        UIActions.click(CalcMain.btn_plus);
        UIActions.click(CalcMain.btn_eight);
        UIActions.click(CalcMain.btn_equals);
    }
}
