package dao;

import config.ConnectionManager;
import entity.UserEntity;

import java.sql.*;

public class UserDAO {
    private static final String GET_USER_BY_USERNAME_SQL =
            "SELECT id, username, email, hash_password, avatar_path FROM usr WHERE username = ?";

    private static final String USER_EXISTS_BY_USERNAME_SQL =
            "SELECT 1 FROM usr WHERE username = ?";

    private static final String INSERT_USER_SQL =
            "INSERT INTO usr (username, email, hash_password) VALUES (?, ?, ?)";

    private static final String DELETE_USER_BY_ID_SQL =
            "DELETE FROM usr WHERE id = ?";

    private static final String UPDATE_USERNAME_SQL = """
            UPDATE usr
            SET username = ?
            WHERE id = ?
            """;
    private static final String UPDATE_AVATAR_SQL =
            "UPDATE usr SET avatar_path = ? WHERE id = ?";


    public boolean updateAvatarPath(long userId, String avatarPath) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_AVATAR_SQL)) {

            ps.setString(1, avatarPath);
            ps.setLong(2, userId);

            int updated = ps.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при обновлении аватара", e);
        }
    }

    public boolean isUserExist(String username) {
        try (Connection conn = ConnectionManager.getConnection();
                PreparedStatement ps =  conn.prepareStatement(USER_EXISTS_BY_USERNAME_SQL)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()){
                    return rs.next();
                }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка с проверки существования пользователя по username", e);
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


    public void deleteUserById(Long id) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_USER_BY_ID_SQL)) {

            ps.setLong(1, id);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new IllegalStateException("Пользователь не найден");
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка удаления пользователя", e);
        }

    }

    private UserEntity convertFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new UserEntity(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("hash_password"),
                    rs.getString("avatar_path")      );
        }

        return null;
    }


    public void updateUsername(String newUsername, long userId) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_USERNAME_SQL)) {

            ps.setString(1, newUsername);
            ps.setLong(2, userId);

            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new IllegalStateException("Пользователь не найден для обновления username");
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при обновлении username", e);
        }
    }
}
