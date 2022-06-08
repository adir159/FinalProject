package sanity;

import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.commonOps;
import workflows.ElectronFlows;


@Listeners(utilities.Lisenters.class)
public class ToDoElectron extends commonOps {

    @Test(description = "Test01 - Add and verify new task")
    @Description("This test Add and verify new task")
    public static void test01_AddAndVerifyNewTask() {
        ElectronFlows.addNewTask("Learn Java");
        Verifications.verifyNumber(ElectronFlows.getNumberOfTasks(), 1);
    }
}
