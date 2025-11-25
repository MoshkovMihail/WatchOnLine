package entity;

import java.util.ArrayList;
import java.util.List;

public class RoomEntity {
    String name;
    List<UserEntity> users = new ArrayList<>();
    List<ToDoListEntity> toDoLists = new ArrayList<>();

}
