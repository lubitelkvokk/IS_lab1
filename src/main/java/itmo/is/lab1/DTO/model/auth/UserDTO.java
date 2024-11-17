package itmo.is.lab1.DTO.model.auth;

import itmo.is.lab1.model.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String username;

    private String password;

    private Role role;
}
