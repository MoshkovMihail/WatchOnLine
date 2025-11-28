package service;

import dao.RoomDAO;
import entity.RoomEntity;
import entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Equals;

import java.util.List;

@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomDAO roomDAO;

    @Override
    public RoomEntity createRoom(String name, long ownerId) {
        return roomDAO.createRoom(name, ownerId);
    }

    @Override
    public List<RoomEntity> getRoomsForUser(long userId) {
        return roomDAO.findRoomsByUserId(userId);
    }

    @Override
    public RoomEntity getRoomById(long roomId) {
        return roomDAO.getRoomById(roomId);
    }

    @Override
    public boolean deleteRoom(long roomId, long ownerId) {
        return roomDAO.deleteRoom(roomId, ownerId);
    }

    @Override
    public boolean joinRoom(long roomId, long memberId) {
        if (roomDAO.isRoomExist(roomId)){
            roomDAO.joinRoom(roomId, memberId);
            return true;
        }
        return false;
    }

    @Override
    public List<UserEntity> getRoomMembers(long roomId) {
        return roomDAO.findMembers(roomId);
    }
}

