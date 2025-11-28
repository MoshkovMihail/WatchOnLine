package dao;

import entity.RoomEntity;
import entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private static final String FIND_ROOMS_BY_USER_ID_SQL = """
        SELECT r.id, r.name, r.owner_id, r.created_at
        FROM room r
        JOIN room_member rm ON r.id = rm.room_id
        WHERE rm.user_id = ?
        ORDER BY r.created_at DESC
        """;

    private static final String CREATE_ROOM_SQL = """
        INSERT INTO room (name, owner_id)
        VALUES (?, ?)
        RETURNING id, name, owner_id, created_at
        """;

    private static final String GET_ROOM_BY_ID_SQL =
            "SELECT r.id, r.name, r.owner_id, r.created_at FROM room r WHERE r.id = ?";

    private static final String INSERT_ROOM_MEMBER_SQL = """
        INSERT INTO room_member (user_id, room_id, role)
        VALUES (?, ?, ?)
        """;

    private static final String DELETE_ROOM_SQL = """
        DELETE FROM room
        WHERE id = ? AND owner_id = ?
        """;

    private static final String ROOM_EXISTS_BY_ID_SQL =
            "SELECT 1 FROM room r WHERE r.id = ?";

    private static final String FIND_MEMBERS_SQL = """
        SELECT u.id,
               u.username,
               u.email,
               u.hash_password,
               u.avatar_path
        FROM room_member rm
        JOIN usr u ON rm.user_id = u.id
        WHERE rm.room_id = ?
        """;

    public RoomEntity createRoom(String name, long userId) {
        try (Connection conn = ConnectionManager.getConnection()) {
            conn.setAutoCommit(false);

            RoomEntity room;

            // 1. создаём комнату и забираем её обратно
            try (PreparedStatement ps = conn.prepareStatement(CREATE_ROOM_SQL)) {
                ps.setString(1, name);
                ps.setLong(2, userId);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        conn.rollback();
                        throw new IllegalStateException("Комната не создалась (пустой ResultSet)");
                    }

                    room = new RoomEntity(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getLong("owner_id"),
                            rs.getTimestamp("created_at")
                    );
                }
            }

            try (PreparedStatement ps2 = conn.prepareStatement(INSERT_ROOM_MEMBER_SQL)) {
                ps2.setLong(1, userId);
                ps2.setLong(2, room.getId());
                ps2.setString(3, "OWNER");
                ps2.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);

            return room;

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка в создании комнаты", e);
        }
    }

    public List<RoomEntity> findRoomsByUserId(long userId) {
        List<RoomEntity> rooms = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ROOMS_BY_USER_ID_SQL)) {

            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rooms.add(new RoomEntity(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getLong("owner_id"),
                            rs.getTimestamp("created_at")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при получении комнат пользователя", e);
        }

        return rooms;
    }

    public RoomEntity getRoomById(long id){
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ROOM_BY_ID_SQL)){

            ps.setLong(1, id);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new RoomEntity(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getLong("owner_id"),
                            rs.getTimestamp("created_at")
                    );
                }
                return null;
            }


        } catch (SQLException e){
            throw new IllegalStateException("Ошибка в получении комнаты по id", e);
        }

    }


    public boolean deleteRoom(long roomId, long ownerId) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ROOM_SQL)) {

            ps.setLong(1, roomId);
            ps.setLong(2, ownerId);
            int updated = ps.executeUpdate();

            System.out.println("DELETE roomId=" + roomId + ", ownerId=" + ownerId);
            System.out.println("updated rows = " + updated);


            return updated > 0; // true – комнату удалили, false – нет прав или не существует

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при удалении комнаты", e);
        }
    }

    public void joinRoom(long roomId, long memberId) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_ROOM_MEMBER_SQL)) {
            ps.setLong(1, memberId);
            ps.setLong(2, roomId);
            ps.setString(3, "MEMBER");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка присоединения к комнате", e);
        }
    }

    public boolean isRoomExist(Long id) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps =  conn.prepareStatement(ROOM_EXISTS_BY_ID_SQL)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка с проверки существования комнаты по id", e);
        }
    }

    public List<UserEntity> findMembers(long roomId) {
        List<UserEntity> users = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_MEMBERS_SQL)) {

            ps.setLong(1, roomId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(new UserEntity(
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("hash_password"),
                            rs.getString("avatar_path")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при получении участников комнаты", e);
        }

        return users;
    }
}
