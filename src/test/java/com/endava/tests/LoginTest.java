package com.endava.tests;

import com.endava.pages.LoginPage;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest extends TestBaseClass{
    @Test
    public void TestLogin(){
        LoginPage loginPage = mainPage.Login("Login");
        loginPage.SendCredentials("raciuciuc@gmail.com", "Password");
        Assert.assertTrue(loginPage.checkIfLoggedIn());
    }
}
