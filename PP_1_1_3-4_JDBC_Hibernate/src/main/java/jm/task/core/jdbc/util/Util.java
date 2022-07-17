package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/myusers";
    private static final String USERNAME = "Temerlan";
    private static final String PASSWORD = "2345t";
    public static Connection getMyConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
}
