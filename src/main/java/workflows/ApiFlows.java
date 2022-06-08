package workflows;

import extensions.ApiActions;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utilities.commonOps;

public class ApiFlows extends commonOps {

    @Step("Business flow: Get team property")
    public static String getTeamProperty(String jPath) {
        response = ApiActions.get("/api/teams/search");
        return ApiActions.extractFromJSON(response, jPath);
    }

    @Step("Business flow: Create new team in grafana")
    public static void postTeam(String name, String email) {
        params.put("name", name);
        params.put("email", email);
        ApiActions.post(params, "/api/teams");
    }

    @Step("Business flow: Create existing team in grafana")
    public static void updateTeam(String name, String email, String id) {
        params.put("name", name);
        params.put("email", email);
        ApiActions.put(params, "/api/teams/" + id);
    }

    @Step("Business flow: Delete existing team in grafana")
    public static void deleteTeam(String id) {
        ApiActions.Delete(id);
    }
}

