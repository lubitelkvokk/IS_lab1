package itmo.is.lab1.permission;

import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Creation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class PermissionChecker {

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public PermissionChecker(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    public void checkRUDPermission(Creation creation) throws NotEnoughAccessLevelToData {
        Authentication authentication = authenticationFacade.getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User user)) {
            throw new IllegalStateException("Authentication is not set or invalid");
        }

        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                || creation.getUser().getId().equals(user.getId())) {
            return;
        }

        throw new NotEnoughAccessLevelToData("Attempt to delete someone else's data");
    }
}
