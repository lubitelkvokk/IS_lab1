package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.data.AdminRequestsDTO;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AdminRequestsDTO>> admin() {
        List<AdminRequestsDTO> usersRequests = adminService.getUsersRequests();
        return ResponseEntity.status(HttpStatus.OK).body(usersRequests);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@AuthenticationPrincipal User user) {
        adminService.createRequest(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Заявка успешно отправлена");
    }

    @PutMapping("/{requestId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateUserRole(@PathVariable Integer requestId) {
        Integer userId = adminService.submitRequestById(requestId);
        return ResponseEntity.status(HttpStatus.CREATED).body("The %d user's role has been changed".formatted(userId));
    }
}
