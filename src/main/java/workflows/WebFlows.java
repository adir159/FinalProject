package workflows;

import extensions.DBActions;
import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Step;
import utilities.commonOps;

import java.util.List;

public class WebFlows extends commonOps {

    @Step("Business flow: login")
    public static void log(String user, String password) {
        UIActions.updateText(grafanaLogin.txt_username, user);
        UIActions.updateText(grafanaLogin.txt_password, password);
        UIActions.click(grafanaLogin.btn_login);
        UIActions.click(grafanaLogin.btn_skip);
        //Uninterruptibles.sleepUninterruptibly(2000, TimeUnit.MILLISECONDS);
    }
        @Step("Business flow: login to Grafana with DB credentials")
        public static void logDB(){
        String query = "Select name,password from NewTable where id = '2'";
           List<String> cred = DBActions.getCredentials(query);
            UIActions.updateText(grafanaLogin.txt_username, cred.get(0));
            UIActions.updateText(grafanaLogin.txt_password, cred.get(1));
            UIActions.click(grafanaLogin.btn_login);
            UIActions.click(grafanaLogin.btn_skip);
    }
    @Step("Business flow: CreateNewUser")
    public static void createNewUser(String name, String user, String email, String password){
        UIActions.click(grafanaServerAdminMain.btn_newUser);
        UIActions.updateText(grafanaAddNewUser.txt_name, name);
        UIActions.updateText(grafanaAddNewUser.txt_email, email);
        UIActions.updateText(grafanaAddNewUser.txt_user, user);
        UIActions.updateText(grafanaAddNewUser.txt_password, password);
        UIActions.click(grafanaAddNewUser.btn_create);
    }
    @Step("Business flow: DeleteLastUser")
    public static void deleteLastUser(){
        UIActions.click(grafanaServerAdminMain.rows.get(grafanaServerAdminMain.rows.size()-2));
        UIActions.click(grafanaEditUser.btn_deleteUser);
        UIActions.click(grafanaEditUser.btn_confirmDeletionUser);
    }

    @Step("Business flow: Search and verify user")
    public static void searchAndVerifyUser(String user, String ShouldExist){
        UIActions.updateTextHumanSpeed(grafanaServerAdminMain.txt_search,user);
        if (ShouldExist.equalsIgnoreCase("exists"))
            Verifications.existanceOfElement(grafanaServerAdminMain.rows);
        else if (ShouldExist.equalsIgnoreCase("not-exist"))
            Verifications.NonExistanceOfElement(grafanaServerAdminMain.rows);
        else
            throw new RuntimeException(("Invalid Expected option in data driven testing"));
    }
}
