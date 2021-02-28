package fragments;

import base.BaseClass;
import extended_page.ExtendedHomePage;
import extended_page.ExtendedLoginPage;
import pages.LoginPage;


/**
 * Project Name    : selenium-testng-page-factory-demo
 * Developer       : Sagar Bhatt
 * Version         : 1.0.0
 * Date            : 02/28/2020
 * Time            : 01:05 AM
 * Description     :
 **/

public class LoginFragment extends BaseClass {

    private final ExtendedLoginPage loginPage;
    private final ExtendedHomePage homePage;

    public LoginFragment() {
        loginPage = pages().getLoginPage();
        homePage = pages().getHomePage();
    }

    public ExtendedHomePage login(String email, String password){
        pages().getLoginPage()
                .setEmailAddress(email)
                .setPassword(password)
                .clickOnSignInButton();
        return homePage;
    }

    public LoginPage logout(){
        homePage.doLogout();
        return loginPage;
    }
}
