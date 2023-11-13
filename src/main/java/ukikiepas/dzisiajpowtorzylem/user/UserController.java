package ukikiepas.dzisiajpowtorzylem.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.user.models.ChangePasswordRequest;
import ukikiepas.dzisiajpowtorzylem.user.models.UserDto;

@RestController
@RequestMapping("/api/v1/auth/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(request, changePasswordRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change-user-details")
    public ResponseEntity<?> changeUserDetails(HttpServletRequest request, @RequestBody UserDto userDto) {
        userService.changeUserDetails(request, userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/user")
    public UserDto getUserData(HttpServletRequest request){
        return userService.getUserData(request);
    }

    @PostMapping("/post/image")
    public ResponseEntity<?> getUserData(HttpServletRequest request, @RequestBody String base64Image){
        boolean updateSuccess = userService.setNewUserImage(request, base64Image);
        if (updateSuccess) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
