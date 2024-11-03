package itmo.is.lab1.DTO.model.auth;

import itmo.is.lab1.model.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;

    private String password;

    private Role role;
}
