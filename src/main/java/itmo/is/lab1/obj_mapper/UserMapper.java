package itmo.is.lab1.obj_mapper;

import itmo.is.lab1.DTO.model.auth.UserDTO;
import itmo.is.lab1.model.auth.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserMapper implements GenericMapper<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
    }
}
