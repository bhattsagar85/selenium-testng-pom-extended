package com.login;


import base.BaseClass;
import extended_page.ExtendedLoginPage;
import fragments.LoginFragment;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Project Name    : selenium-testng-page-factory-demo
 * Developer       : Sagar Bhatt
 * Version         : 1.0.0
 * Date            : 02/28/2020
 * Time            : 01:25 AM
 * Description     :
 **/


public class LoginTest extends BaseClass {

    private LoginFragment loginFragment;

    @BeforeMethod
    public void before(){
        loginFragment = new LoginFragment();
    }

    @Test
    public void verifyValidUserLogin(){
        loginFragment
                .login("bhattsag@gmail.com", "Test@123")
                .checkHomePageTitle()
                .checkLoggedInUsername("Automation Tester");
        loginFragment
                .logout();
    }
}
