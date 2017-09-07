package com.endava.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionPage {

    public void DBconnection(String query) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}
