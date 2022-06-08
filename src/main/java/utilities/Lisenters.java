package utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Lisenters extends commonOps implements ITestListener {

    public void onStart(ITestContext execution)
    {
        System.out.println( "------------Starting Execution------------");
    }

    public void onFinish(ITestContext execution)
    {
        System.out.println( "------------Ending Execution------------");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0)
    {
    // Wont use....
    }

    public void onTestSkipped(ITestResult test)
    {
        System.out.println("-------Skipping Test:" +test.getName() + "---------");
    }

    public void onTestStart(ITestResult test)
    {
        System.out.println("-------Starting Test:" +test.getName() + "---------");
    }

    public void onTestSuccess(ITestResult test)
    {
        System.out.println("-------Test Passed:" +test.getName() + "---------");
        if (!platform.equalsIgnoreCase("api")){
     //Stop recording
        try {
            MonteScreenRecorder.stopRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Deletes recorded file
        File file = new File("./test-recordings/" + test.getName() + ".avi");
        if (file.delete())
            System.out.println("File deleted successfully");
        else
            System.out.println("File to delete file");}
    }

    public void onTestFailure(ITestResult test) {
        System.out.println("-------Test Failed:" + test.getName() + "---------");
        if (!platform.equalsIgnoreCase("api")) {
            //Stop recording
            try {
                MonteScreenRecorder.stopRecord();
            } catch (Exception e) {
                e.printStackTrace();
            }
            saveScreenshot();
        }
    }
    @Attachment(value = "Page Screen-Shot", type ="image/png")
    public byte[] saveScreenshot()
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

}
