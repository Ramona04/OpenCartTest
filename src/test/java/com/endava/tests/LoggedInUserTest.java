package com.endava.tests;

import com.endava.pages.LoggedInUserPage;
import com.endava.pages.LoginPage;
import com.endava.pages.ProductsPage;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoggedInUserTest extends TestBaseClass{
    @Test
    public void connectDB() throws SQLException{
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

        //ProductsPage productsPage = loggedInUserPage.GoToProductsPage();
    }
}
