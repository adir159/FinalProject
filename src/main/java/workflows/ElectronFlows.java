package workflows;

import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import utilities.commonOps;

public class ElectronFlows extends commonOps {

    @Step("Add new task to the list")
    public static void addNewTask(String taskName){
        UIActions.updateText(toDoMain.txt_create, taskName);
        UIActions.insertKey(toDoMain.txt_create, Keys.RETURN);
    }

    @Step("Count and return the number of tasks")
    public static int getNumberOfTasks(){
        return toDoMain.list_tasks.size();
    }
}
