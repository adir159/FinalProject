package utilities;

import io.appium.java_client.AppiumDriver;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;
import pageObjects.grafana.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class base {
    //General
    protected static WebDriverWait wait;
    protected static Actions action;
    protected static SoftAssert softAssert;
    protected static Screen screen;
    protected static String platform;

    //Web
    protected static WebDriver driver;

    //Mobile
    protected static AppiumDriver mobileDriver ;
   protected static DesiredCapabilities dc = new DesiredCapabilities();


   //Rest API
    protected static RequestSpecification httpRequest;
   protected static Response response;
    protected static JSONObject params = new JSONObject();
    protected static JsonPath jp;

    //DataBase

    protected static Connection con;
    protected static Statement stmt;
    protected static ResultSet result;

    //page objects - web
    protected static LoginPage grafanaLogin;
    protected static pageObjects.grafana.MainPage grafanaMain;
    protected static LeftMenuPage grafanaLeftMenu;
    protected static ServerAdminMenuPage grafanaServerAdmin;
    protected static ServerAdminMainPage grafanaServerAdminMain;
    protected static AddNewUserPage grafanaAddNewUser;
    protected static EditUserPage grafanaEditUser;

    //page objects - mobile
    protected static pageObjects.mortgage.MainPage mortgageMain;

    //page objects - electron
    protected static pageObjects.todo.MainPage toDoMain;

    //page objects - Desktop
    protected static pageObjects.Calculator.MainPage CalcMain;
}
