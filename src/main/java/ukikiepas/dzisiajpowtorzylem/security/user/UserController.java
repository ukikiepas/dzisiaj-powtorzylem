package ukikiepas.dzisiajpowtorzylem.security.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.security.user.models.ChangePasswordRequest;
import ukikiepas.dzisiajpowtorzylem.security.user.models.UserDto;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Principal connectedUser) {
        userService.changePassword(changePasswordRequest, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/user")
    public UserDto getUserData(HttpServletRequest request){
        return userService.getUserData(request);
    }

}
