package com.endava.tests;

import com.endava.pages.LoggedInUserPage;
import com.endava.pages.LoginPage;
import com.endava.pages.ProductsPage;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.SourceType;
import sun.awt.windows.ThemeReader;

public class GoToProductsPageTest extends TestBaseClass{

    @Test
    public void TestProductsPage(){
        LoginPage loginPage = mainPage.Login("Login");
        loginPage.SendCredentials("raciuciuc@gmail.com", "Password");

        //ProductsPage productsPage = loggedInUserPage.GoToProductsPage();
        System.out.println("after");
    }
}
