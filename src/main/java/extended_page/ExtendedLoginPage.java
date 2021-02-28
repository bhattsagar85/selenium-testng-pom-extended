package extended_page;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static base.Enums.PageTitle.LOGIN_PAGE_TITLE;
import static org.testng.Assert.assertEquals;


/**
 * Project Name    : selenium-testng-page-factory-demo
 * Developer       : Sagar Bhatt
 * Version         : 1.0.0
 * Date            : 02/28/2020
 * Time            : 12:44 AM
 * Description     :
 **/

public class ExtendedLoginPage extends LoginPage {
    public ExtendedLoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage verifyLoginPageTitle(){
        assertEquals(driver.getTitle(), LOGIN_PAGE_TITLE.getTitle());
        return this;
    }
}
