package dao;

import config.ConnectionManager;
import entity.ToDoItemEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoItemDAO {

    private static final String FIND_BY_TODO_LIST_ID_SQL = """
        SELECT id, list_id, text, deadline, created_at, done
        FROM todo_item
        WHERE list_id = ?
        ORDER BY created_at ASC
        """;

    private static final String INSERT_ITEM_SQL = """
        INSERT INTO todo_item (list_id, text, deadline, done)
        VALUES (?, ?, ?, false)
        RETURNING id, list_id, text, deadline, created_at, done;
        """;

    private static final String DELETE_TO_DO_ITEM_BY_ID_SQL =
            "DELETE FROM todo_item WHERE id = ?";

    private static final String TOGGLE_DONE_SQL =
            "UPDATE todo_item SET done = NOT done WHERE id = ?";

    public List<ToDoItemEntity> findByListId(long listId) {
        List<ToDoItemEntity> items = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_TODO_LIST_ID_SQL)) {

            ps.setLong(1, listId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(convertFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при получении пунктов ToDo для списка", e);
        }

        return items;
    }


    public ToDoItemEntity create(long listId, String text, Timestamp deadline) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_ITEM_SQL)) {

            ps.setLong(1, listId);
            ps.setString(2, text);
            ps.setTimestamp(3, deadline);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new IllegalStateException("ToDo Item не создался");
                }
                return convertFromResultSet(rs);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при создании ToDo Item", e);
        }
    }

    public boolean deleteById(long id) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_TO_DO_ITEM_BY_ID_SQL)) {

            ps.setLong(1, id);
            int updated = ps.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка удаления ToDo Item", e);
        }
    }



    public void toggleDone(long itemId) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(TOGGLE_DONE_SQL)) {

            ps.setLong(1, itemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при смене статуса задачи", e);
        }
    }

    private ToDoItemEntity convertFromResultSet(ResultSet rs) throws SQLException {
        return new ToDoItemEntity(
                rs.getLong("id"),
                rs.getLong("list_id"),
                rs.getString("text"),
                rs.getBoolean("done"),
                rs.getTimestamp("deadline"),
                rs.getTimestamp("created_at")
        );
    }
}
