package com.endava.tests;

import com.endava.pages.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;


public class RegisterTest extends TestBaseClass {
    DBConnectionPage dbConnectionPage = new DBConnectionPage();

    @Test
    public void TestRegister() {
        RegisterPage registerPage = mainPage.Register("Register");
        Assert.assertEquals("Account", registerPage.CheckPageTitle());
        registerPage.CompleteRegisterForm("No");
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("C:\\Users\\rciuciuc\\Desktop\\Homework and Materials\\Open_Cart_ Test\\OpenCartTest\\src\\main\\resources\\config.properties");
            properties.load(input);

            Assert.assertEquals(properties.getProperty("firstName") + " "
                                        + properties.getProperty("lastName") + " "
                                        + properties.getProperty("emailAddress") + " "
                                        + properties.getProperty("telephone"), registerPage.CheckUser());

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void TestLogin() {
        LoginPage loginPage = mainPage.Login("Login");
        loginPage.SendCredentials();
        Assert.assertTrue(loginPage.checkIfLoggedIn());
    }

    @Test
    public void connectDB() throws SQLException {

        dbConnectionPage.DBconnection("INSERT INTO oc_address " + "VALUES(null, (SELECT customer_id FROM oc_customer WHERE email='raciuciuc@gmail.com'), 'Ramona', 'Ciuciuc', 'Endava', '11 street name', '2 street name', 'Bucharest', '137035', 40, 33, 'cust')");

        LoginPage loginPage = mainPage.Login("Login");
        loginPage.SendCredentials();

        LoggedInUserPage loggedInUserPage = loginPage.GoToAddressBookPage();
        loggedInUserPage.AccessAddressBook();

        Assert.assertEquals("Ramona Ciuciuc\n" +
                "Endava\n" +
                "11 street name\n" +
                "2 street name\n" +
                "Bucharest 137035\n" +
                "Berat\n" +
                "Cayman Islands", loggedInUserPage.CheckInsertedAddress());
    }

    @Before
    public void ResetCart() throws SQLException {
        dbConnectionPage.DBconnection("UPDATE oc_cart" + " " + "SET quantity = 0" + " " + "WHERE customer_id=(SELECT customer_id FROM oc_customer WHERE email='raciuciuc@gmail.com')");
    }

    @Test
    public void TestProductsPage() throws SQLException {
        LoginPage loginPage = mainPage.Login("Login");
        loginPage.SendCredentials();

        ProductsPage productsPage = loginPage.GoToProductsPage();

//        iPhonePage iphonePage = productsPage.GoToiPhonePage();
//        iphonePage.AddProductToCart();
        MacBookPage macBookPage = productsPage.GoToMacBookPage();
        macBookPage.AddProductToCart();


//        String initialQuantity = iphonePage.getFirstQuantity();
        String initialQuantity = macBookPage.getFirstQuantity();

        dbConnectionPage.DBconnection("UPDATE oc_cart" + " " + "SET quantity = quantity+1" + " " + "WHERE customer_id=(SELECT customer_id FROM oc_customer WHERE email='raciuciuc@gmail.com')");

//        Assert.assertEquals(initialQuantity, iphonePage.getFinalQuantity());
        Assert.assertEquals(initialQuantity, macBookPage.getFinalQuantity());
    }
}
