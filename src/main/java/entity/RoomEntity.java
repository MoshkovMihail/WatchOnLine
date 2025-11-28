package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {
    private Long id;
    private String name;
    private Long ownerId;
    private Timestamp createdAt;
}
