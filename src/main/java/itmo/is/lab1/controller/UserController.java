package itmo.is.lab1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import itmo.is.lab1.DTO.model.auth.UserDTO;
import itmo.is.lab1.model.auth.Role;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@Tag(name = "User", description = "The User API")
@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Operation(summary = "Gets all users", tags = "user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))
                    })
    })
    @GetMapping("/users")
    public List<UserDTO> getUsers(){
        List<UserDTO> users = new LinkedList<>();
        users.add(new UserDTO("AbobaUser", "qwerty123", Role.ROLE_USER));
        users.add(new UserDTO("AbobaAdmin", "123qwerty", Role.ROLE_ADMIN));
        return users;
    }
}