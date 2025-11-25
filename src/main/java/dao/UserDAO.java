package dao;

import entity.UserEntity;

import java.sql.*;

public class UserDAO {

    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS usr (
                id BIGSERIAL PRIMARY KEY,
                username VARCHAR(255) UNIQUE,
                email VARCHAR(255) NOT NULL,
                hash_password VARCHAR(255) NOT NULL
            );
            """;

    private static final String GET_USER_BY_ID_SQL =
            "SELECT id, username, email, hash_password FROM usr WHERE id = ?";

    private static final String GET_USER_BY_USERNAME_SQL =
            "SELECT id, username, email, hash_password FROM usr WHERE username = ?";

    private static final String USER_EXISTS_BY_USERNAME_SQL =
            "SELECT 1 FROM usr WHERE username = ?";

    private static final String INSERT_USER_SQL =
            "INSERT INTO usr (username, email, hash_password) VALUES (?, ?, ?)";

    private static final String DELETE_USER_BY_ID_SQL =
            "DELETE FROM usr WHERE id = ?";

    private static final String GET_USER_BY_CREDS =
            "select * from usr where username = ? and hash_password = ?";


    public void createUserTable() {
        try(Connection conn = ConnectionManager.getConnection();
            Statement statement = conn.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_SQL);

        } catch (SQLException e){
            throw new IllegalStateException("Ошибка создания таблицы usr", e);
        }
    }

    public UserEntity getUser(long id) {
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_USER_BY_ID_SQL)) {
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                return convertFromResultSet(rs);
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка с получением пользователя по id", e);
        }
    }

    public boolean isUserExist(String username) {
        try (Connection conn = ConnectionManager.getConnection();
                PreparedStatement ps =  conn.prepareStatement(GET_USER_BY_USERNAME_SQL)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()){
                    return rs.next();
                }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка с проверки существования пользователя по id", e);
        }
    }

    public UserEntity getUser(String username) {
        try (Connection conn = ConnectionManager.getConnection();
                PreparedStatement ps= conn.prepareStatement(GET_USER_BY_USERNAME_SQL)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                return convertFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка с получением пользователя по username", e);
        }
    }

    public void saveNewUser(String username, String email, String hash_password) {
        try (Connection conn = ConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL)) {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, hash_password);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Пользователь не сохранился", e);
        }
    }

//не безопастно от sql инъекций
//    public void updateUser(String username) throws SQLException {
//        getStatement().executeUpdate("""
//                update usr
//                set username = '""" + username + "' where username = " + username + ";");
//    }

    public void deleteUserById(Long id) {
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_USER_BY_ID_SQL)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка удаления пользователя", e);
        }

    }

    private UserEntity convertFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new UserEntity(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("hash_password")
            );
        }

        return null;
    }
}
