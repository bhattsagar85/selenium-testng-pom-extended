package pages;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


/**
 * Project Name    : selenium-testng-page-factory-demo
 * Developer       : Sagar Bhatt
 * Version         : 1.0.0
 * Date            : 2/27/2021
 * Time            : 08:00 AM
 * Description     :
 **/

public class LoginPage extends BaseClass {

    protected final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(how = How.ID, using = "email")
    public WebElement txt_email_address;

    @FindBy(how = How.ID, using = "passwd")
    public WebElement txt_password;

    @FindBy(how = How.ID, using = "SubmitLogin")
    public WebElement btn_Login;


    public LoginPage setEmailAddress(String emailAddress){
        txt_email_address.sendKeys(emailAddress);
        return this;
    }

    public LoginPage setPassword(String password){
        txt_password.sendKeys(password);
        return this;
    }

    public LoginPage clickOnSignInButton(){
        btn_Login.click();
        return this;
    }

}
