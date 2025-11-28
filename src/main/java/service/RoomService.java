package service;

import entity.RoomEntity;
import entity.UserEntity;

import java.util.List;

public interface RoomService {
    List<RoomEntity> getRoomsForUser(long userId);

    RoomEntity createRoom(String name, long ownerId);

    RoomEntity getRoomById(long roomId);

    boolean deleteRoom(long roomId, long ownerId);

    boolean joinRoom(long roomId, long memberId);

    List<UserEntity> getRoomMembers(long roomId);

}
