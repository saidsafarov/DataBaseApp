package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public class AbstractDao {
    public Connection connect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/resume?serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

}
