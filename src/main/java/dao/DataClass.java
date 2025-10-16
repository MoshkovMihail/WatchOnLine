package dao;

import models.User;

import java.sql.*;

public class DataClass {
    public void test(String username) {
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement("select * from usr where username = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void createUserTable() throws SQLException {
        getStatement().executeUpdate("""
                create table if not exists usr (
                id bigserial primary key,
                username varchar(255) not null,
                email varchar(255));""");
    }

    public User getUser(long id) throws SQLException {
        return convertFromResultSet(getStatement()
                .executeQuery("select * from usr where id = " + id + ";"));
    }

    public void saveNewUser() throws SQLException {
        getStatement().executeUpdate("""
                insert into usr (username, email)
                values ('Maxim', 'super.maxa2001@yandex.ru');""");
    }

    public void updateUser(String username) throws SQLException {
        getStatement().executeUpdate("""
                update usr
                set username = '""" + username + "' where id = 1;");
    }

    public void deleteUser() throws SQLException {
        getStatement().executeUpdate("delete from usr where id = 1");
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/test",
                "postgres", "qwerty007");
    }

    private User convertFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new User(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("email")
            );
        }

        return null;
    }


    private Statement getStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("ваще всё поломалось, Наташа, мы ваще всё уронили");
            throw new IllegalStateException(e);
        }
    }
}
