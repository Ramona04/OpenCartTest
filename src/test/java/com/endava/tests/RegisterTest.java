package com.endava.tests;

import com.endava.pages.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterTest extends TestBaseClass {
    @Test
    public void TestRegister() {
        RegisterPage registerPage = mainPage.Register("Register");
        Assert.assertEquals("Account", registerPage.CheckPageTitle());
        registerPage.CompleteRegisterForm("ramona", "ciuciuc", "ramciuciuc@gmail.com", "0720330000", "Password", "No");
        Assert.assertEquals("ramona ciuciuc raciuciuc@gmail.com 0720330000", registerPage.CheckUser());
    }

    @Test
    public void TestLogin() {
        LoginPage loginPage = mainPage.Login("Login");
        loginPage.SendCredentials("raciuciuc@gmail.com", "Password");
        Assert.assertTrue(loginPage.checkIfLoggedIn());
    }

    @Test
    public void connectDB() throws SQLException {
        LoginPage loginPage = mainPage.Login("Login");
        loginPage.SendCredentials("raciuciuc@gmail.com", "Password");

        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO oc_address " + "VALUES(null, (SELECT customer_id FROM oc_customer WHERE email='raciuciuc@gmail.com'), 'Ramona', 'Ciuciuc', 'Endava', '1 street name', '2 street name', 'Bucharest', '137035', 40, 33, 'cust')");

        LoggedInUserPage loggedInUserPage = loginPage.GoToAddressBookPage();
        loggedInUserPage.AccessAddressBook();
        Assert.assertEquals("Ramona Ciuciuc\n" +
                "Endava\n" +
                "1 street name\n" +
                "2 street name\n" +
                "Bucharest 137035\n" +
                "Berat\n" +
                "Cayman Islands", loggedInUserPage.CheckInsertedAddress());
    }

    @Test
    public void TestProductsPage() throws SQLException{
        LoginPage loginPage = mainPage.Login("Login");
        loginPage.SendCredentials("raciuciuc@gmail.com", "Password");

        ProductsPage productsPage = loginPage.GoToProductsPage();

        iPhonePage iphonePage = productsPage.GoToiPhonePage();
        iphonePage.AddProductToCart();
//        MacBookPage macBookPage = productsPage.GoToMacBookPage();
//        macBookPage.AddProductToCart();


        String initialQuantity = iphonePage.getFirstQuantity();
//        String initialQuantity = macBookPage.getFirstQuantity();


        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE oc_cart" + " " + "SET quantity = quantity+1" + " " + "WHERE customer_id=(SELECT customer_id FROM oc_customer WHERE email='raciuciuc@gmail.com')");

        Assert.assertEquals(initialQuantity, iphonePage.getFinalQuantity());
//        Assert.assertEquals(initialQuantity, macBookPage.getFinalQuantity());
    }

    @Test
    public void ResetCart()throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE oc_cart" + " " + "SET quantity = 0" + " " + "WHERE customer_id=(SELECT customer_id FROM oc_customer WHERE email='raciuciuc@gmail.com')");
    }
}
