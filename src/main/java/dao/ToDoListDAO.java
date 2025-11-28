package dao;

import entity.ToDoListEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoListDAO {

    private static final String FIND_BY_ROOM_ID_SQL = """
        SELECT id, room_id, name, created_by, created_at
        FROM todo_list
        WHERE room_id = ?
        ORDER BY created_at ASC
        """;

    private static final String INSERT_SQL = """
        INSERT INTO todo_list (room_id, name, created_by)
        VALUES (?, ?, ?)
        RETURNING id, room_id, name, created_by, created_at
        """;

    private static final String DELETE_TO_DO_BY_ID_SQL =
            "DELETE FROM todo_list WHERE id = ?";

    public List<ToDoListEntity> findByRoomId(long roomId) {
        List<ToDoListEntity> lists = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_ROOM_ID_SQL)) {

            ps.setLong(1, roomId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lists.add(convertFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при получении списков для комнаты", e);
        }

        return lists;
    }

    public ToDoListEntity create(long roomId, String name, long createdBy) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setLong(1, roomId);
            ps.setString(2, name);
            ps.setLong(3, createdBy);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new IllegalStateException("Список не создался");
                }
                return convertFromResultSet(rs);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при создании списка", e);
        }
    }

    private ToDoListEntity convertFromResultSet(ResultSet rs) throws SQLException {
        return new ToDoListEntity(
                rs.getLong("id"),
                rs.getLong("room_id"),
                rs.getString("name"),
                rs.getLong("created_by"),
                rs.getTimestamp("created_at")
        );
    }

    private void deleteById(long id) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_TO_DO_BY_ID_SQL)) {

            ps.setLong(1, id);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new IllegalStateException("ToDo лист не найден");
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка удаления ToDo листа", e);
        }

    }
}