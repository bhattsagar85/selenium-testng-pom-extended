package base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.DriverManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Project Name    : selenium-testng-page-factory-demo
 * Developer       : Sagar Bhatt
 * Version         : 1.0.0
 * Date            : 27/2/2021
 * Time            : 1:59 PM
 * Description     :
 **/

public class BaseClass {

    private static final String fileSeparator = File.separator;
    private static WebDriver driver;
    public static Properties config = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    private ExtentReports extent;
    private static final String rootDirectory = System.getProperty("user.dir");

    @BeforeSuite
    public void setUp() throws IOException {
        fis = new FileInputStream(rootDirectory +fileSeparator + "src" + fileSeparator + "test" + fileSeparator + "resources" + fileSeparator
        + "properties" + fileSeparator + "Config.properties");
        config.load(fis);
        BasicConfigurator.configure();
        log.debug("Config file loaded !!!");
        String browserName = config.getProperty("browserName");
        String testUrl = config.getProperty("testUrl");
        int implicitTimeOut = Integer.parseInt(config.getProperty("implicitWait"));
        initializeDriver(browserName, testUrl, implicitTimeOut);
    }

    @BeforeTest
    public void extentReportSetup(){
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(rootDirectory + fileSeparator + "reports"+
                fileSeparator + "html-report" + fileSeparator + "execution-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setDocumentTitle("Test Execution Report - Automation Practise");
        htmlReporter.config().setReportName("Test Execution Report - Automation Practise");
        htmlReporter.config().setTheme(Theme.DARK);

        extent.setSystemInfo("Application Name", "Digital Fuel Admin Web");
        extent.setSystemInfo("Tester Name", "Sagar Bhatt");
        extent.setSystemInfo("Browser", "Chrome");
    }



    @AfterMethod
    public void generateReports(ITestResult result){
        ExtentTest test = extent.createTest(result.getName());
        switch (result.getStatus()){
            case ITestResult.FAILURE:
                test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
                test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable().getMessage(), ExtentColor.RED));
                try{
                    test.fail("Screenshot of the failed test is :"+
                            test.addScreenCaptureFromPath(takeScreenshot(driver, result.getName())));
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case ITestResult.SKIP:
                test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.GREY));
                break;
            case ITestResult.SUCCESS:
                test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
                break;
        }
    }

    @AfterSuite
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

    @AfterTest
    public void endReport(){
        extent.flush();
    }

    protected PageProvider pages() {
        return new PageProvider(driver);
    }


    public void initializeDriver(String browserName, String testUrl, int implicitTimeOut){
        driver = DriverManager.getDriver(browserName);
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(implicitTimeOut, TimeUnit.SECONDS);
        driver.get(testUrl);
    }

    private String takeScreenshot(WebDriver driver, String screenshotName){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = rootDirectory+fileSeparator+"screenshot"+fileSeparator+screenshotName
                +"-"+timeStamp+".png";
        File finalDestination = new File(destination);
        try{
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}
