package utilities;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.lang.reflect.Method;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class commonOps extends base {

    /* ######################################
    Method name: getData
    Method description:This method gets the data from xml configuration file.
    Method parameter: String
    Method return: String
    #########################################*/
    public static String getData(String nodeName) throws ParserConfigurationException, IOException, SAXException {
        File fXmlFile;
        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            fXmlFile = new File("./Configuration/DataConfig.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        }
        catch (Exception e)
        {
            System.out.println("Error Reading XML file: " + e);
            }
        finally {
            return doc.getElementsByTagName(nodeName).item(0).getTextContent();
        }
    }

    public static void initBrowser(String BrowserType) throws ParserConfigurationException, IOException, SAXException {
        if (BrowserType.equalsIgnoreCase("Chrome"))
            driver = initChromeDriver();
        else if (BrowserType.equalsIgnoreCase("firefox"))
            driver = initFirefoxDriver();
        else if (BrowserType.equalsIgnoreCase("ie"))
            driver = initIeDriver();
        else
            throw new RuntimeException("Invaild browser type");
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("Timeout")),TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,Long.parseLong(getData("Timeout")));
        driver.get(getData("url"));
        ManagePages.initGrafana();
        action = new Actions(driver);
    }

    public static WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver initFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    public static WebDriver initIeDriver() {
        WebDriverManager.iedriver().setup();
        WebDriver driver = new InternetExplorerDriver();
        return driver;
    }

    public static void  initMobile() throws ParserConfigurationException, IOException, SAXException {
        dc.setCapability(MobileCapabilityType.UDID, getData("UDID"));
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,getData("APP_PACKAGE"));
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,getData("APP_ACTIVITY"));
        try {
           mobileDriver = new AndroidDriver<>(new URL(getData("AppiumServer")), dc);
        } catch (Exception e) {
            System.out.println("Cannot connect to Appium server, see details: " + e);
        }
        ManagePages.initMortgage();
       mobileDriver.manage().timeouts().implicitlyWait(Long.parseLong(getData("Timeout")),TimeUnit.SECONDS);
        wait = new WebDriverWait(mobileDriver,Long.parseLong(getData("Timeout")));
        action = new Actions(driver);
    }

    public static void initAPI() throws ParserConfigurationException, IOException, SAXException {
        RestAssured.baseURI = getData("urlAPI");
        httpRequest = RestAssured.given().given().auth().preemptive().basic(getData("Username"), getData("Password"));
    }

    public static void initElectron() throws ParserConfigurationException, IOException, SAXException {
        System.setProperty("webdriver.chrome.driver", getData("ElectronDriverPath"));
        ChromeOptions OPT = new ChromeOptions();
        OPT.setBinary(getData("ElectronAppPath"));
        dc.setCapability("chromeOptions", OPT);
        dc.setBrowserName("chrome");
        driver = new ChromeDriver(dc);
        ManagePages.initToDo();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("Timeout")),TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,Long.parseLong(getData("Timeout")));
        action = new Actions(driver);
    }
    public static void initDesktop() throws ParserConfigurationException, IOException, SAXException {
        dc.setCapability("app", getData("calculatorApp"));
        driver = new WindowsDriver(new URL(getData("AppiumServerDesktop")),dc);
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("Timeout")),TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,Long.parseLong(getData("Timeout")));
        ManagePages.initCalculator();
    }
        @BeforeClass
        @Parameters({"PlatformName"})
    public void StartSession(String PlatformName) throws ParserConfigurationException, IOException, SAXException {
       platform = PlatformName;
        if (platform.equalsIgnoreCase("web"))
            initBrowser(getData("BrowserName"));
               else if (platform.equalsIgnoreCase("mobile"))
              initMobile();
        else if (platform.equalsIgnoreCase("api"))
            initAPI();
        else if (platform.equalsIgnoreCase("electron"))
            initElectron();
        else if (platform.equalsIgnoreCase("windows"))
            initDesktop();
        else
            throw new RuntimeException("Invalid platform name");
        softAssert = new SoftAssert();
        screen = new Screen();
        manageDB.openConnection(getData("DBURL"), getData("DBUsername"),getData("DBPassword"));
    }

    @AfterClass
    public void CloseSession() throws ParserConfigurationException, IOException, SAXException {
        manageDB.closeConnection();
        if (!platform.equalsIgnoreCase("api")){
        if ((!platform.equalsIgnoreCase("mobile")))
        driver.quit();
        else
            mobileDriver.quit();}
    }

    @AfterMethod
    public void AfterMethod() throws ParserConfigurationException, IOException, SAXException {
        if ((platform.equalsIgnoreCase("web")))
        driver.get(getData("url"));
    }

    @BeforeMethod
    public void BeforeMethod(Method method) throws ParserConfigurationException, IOException, SAXException {
        if (!platform.equalsIgnoreCase("api")) {
            try {
                MonteScreenRecorder.startRecord(method.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


