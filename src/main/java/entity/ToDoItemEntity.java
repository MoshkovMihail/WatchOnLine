package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ToDoItemEntity {
    private Long id;
    private Long listId;
    private String text;
    private boolean done;
    private Timestamp deadline;
    private Timestamp createdAt;
}
