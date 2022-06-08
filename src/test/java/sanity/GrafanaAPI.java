package sanity;

import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.commonOps;
import workflows.ApiFlows;

@Listeners(utilities.Lisenters.class)
public class GrafanaAPI extends commonOps {

    @Test(description = "Test01: Get team from grafana")
    @Description("This test verify team property")
    public void test01_VerifyTeams() {
        Verifications.verifyText(ApiFlows.getTeamProperty("teams[0].name"), "Kuku");
    }

    @Test(description = "Test02: Add a team and verify")
    @Description("Add a team to grafana and verify it")
    public void test02_addTeamAndVerify() {
        ApiFlows.postTeam("ZWTeam", "z@team.com");
        Verifications.verifyText(ApiFlows.getTeamProperty("teams[1].name"), "ZWTeam");
    }

    @Test(description = "Test03: update existing team and verify")
    @Description("update a team in grafana and verify it")
    public void test03_updateTeamAndVerify() {
        String id=ApiFlows.getTeamProperty("teams[1].id");
        ApiFlows.updateTeam("ZWWTeam", "z@team.com", id);
      Verifications.verifyText(ApiFlows.getTeamProperty("teams[1].name"), "ZWWTeam");
    }
    @Test(description = "Test04: Delete existing team and verify")
    @Description("update a team in grafana and verify it")
    public void test04_deleteTeamAndVerify() {
        String id=ApiFlows.getTeamProperty("teams[1].id");
        ApiFlows.deleteTeam(id);
        Verifications.verifyText(ApiFlows.getTeamProperty("totalCount"), "1");
    }
}
