package base;

import extended_page.ExtendedHomePage;
import extended_page.ExtendedLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageProvider {

    private final WebDriver driver;

    public PageProvider(WebDriver driver) {
        this.driver = driver;
    }

    public ExtendedLoginPage getLoginPage(){
        return PageFactory.initElements(driver, ExtendedLoginPage.class);
    }

    public ExtendedHomePage getHomePage(){
        return PageFactory.initElements(driver, ExtendedHomePage.class);
    }
}
