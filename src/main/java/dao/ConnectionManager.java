package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private static final String URL = "jdbc:postgresql://localhost:5432/ToDo2Gether";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    static {
        try {
            Class.forName("org.postgresql.Driver"); // загружаем драйвер один раз
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Не удалось загрузить драйвер БД", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}