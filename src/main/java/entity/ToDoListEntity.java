package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class ToDoListEntity {
    private Long id;
    private Long roomId;
    private String name;
    private Long createdBy;
    private Timestamp createdAt;
}
