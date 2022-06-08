package extensions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import utilities.commonOps;

public class ApiActions extends commonOps {


    @Step("Get data from server")
    public static Response get(String paramValues) {
        response = httpRequest.get(paramValues);
        response.prettyPrint();
        return response;
    }

    @Step("Extract value from JSON format")
    public static String extractFromJSON(Response response, String path) {
        jp = response.jsonPath();
        return jp.get(path).toString();
    }

    @Step("Post data to server")
    public static void post(JSONObject params,String resource) {
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(params.toJSONString());
        response = httpRequest.post(resource);
        response.prettyPrint();
    }

    @Step("Update data in server")
    public static void put(JSONObject params,String resource) {
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(params.toJSONString());
        response = httpRequest.put(resource);
        response.prettyPrint();
}
    @Step("Delete data from server")
    public static void Delete(String id) {
        response = httpRequest.delete("/api/teams/" + id);
        response.prettyPrint();
    }

}
