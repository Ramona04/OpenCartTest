package com.endava.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionPage {

    public void DBconnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO oc_address " + "VALUES(null, (SELECT customer_id FROM oc_customer WHERE email='raciuciuc@gmail.com'), 'Ramona', 'Ciuciuc', 'Endava', '11 street name', '2 street name', 'Bucharest', '137035', 40, 33, 'cust')");
    }

    public void ResetCartQuantity() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE oc_cart" + " " + "SET quantity = 0" + " " + "WHERE customer_id=(SELECT customer_id FROM oc_customer WHERE email='raciuciuc@gmail.com')");
    }

    public void EditQuantityValue() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE oc_cart" + " " + "SET quantity = quantity+1" + " " + "WHERE customer_id=(SELECT customer_id FROM oc_customer WHERE email='raciuciuc@gmail.com')");
    }
}
