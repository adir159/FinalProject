package extensions;

import io.qameta.allure.Step;
import utilities.commonOps;

import java.util.ArrayList;
import java.util.List;

public class DBActions extends commonOps {

    @Step("Get credentials from database ")
    public static List<String> getCredentials(String query) {
        List<String> credentials = new ArrayList<String>();
        try {
            result = stmt.executeQuery(query);
            result.next();
            credentials.add(result.getString(1));
            credentials.add(result.getString(2));
        } catch (Exception e) {
            System.out.println("Error while printing table data,see details: " + e);
        }
        return credentials;
    }
}


