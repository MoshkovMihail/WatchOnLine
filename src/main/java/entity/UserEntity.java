package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserEntity {
    private Long id;
    private String username;
    private String email;
    private String hashPassword;
}
