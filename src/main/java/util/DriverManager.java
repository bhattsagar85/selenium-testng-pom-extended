package util;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


/**
 * Project Name    : selenium-testng-page-factory-demo
 * Developer       : Sagar Bhatt
 * Version         : 1.0.0
 * Date            : 2/27/2021
 * Time            : 03:55 PM
 * Description     :
 **/

public class DriverManager {

    private static final String WINDOW_WIDTH = System.getenv("window-width");
    private static final String WINDOW_HEIGHT = System.getenv("window-height");
    private static final String HEADLESS = "--headless";
    private static final String HEADLESS_CHROME = "headless-chrome";
    private static final String FIREFOX = "firefox";
    private static final String HEADLESS_FIREFOX = "headless-firefox";
    private static final String WINDOW_SIZE = "--window-size=" + WINDOW_WIDTH + "x" + WINDOW_HEIGHT;

    public static WebDriver getDriver(String browserName){
        if(browserName == null){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        switch (browserName.toLowerCase()){
            case HEADLESS_CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(HEADLESS);
                chromeOptions.addArguments(WINDOW_SIZE);
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                WebDriverManager.chromedriver().setup();
                return new FirefoxDriver();
            case HEADLESS_FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(HEADLESS);
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(firefoxOptions);
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

}
