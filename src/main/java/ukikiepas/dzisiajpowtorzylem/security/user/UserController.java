package ukikiepas.dzisiajpowtorzylem.security.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.exception.TokenNotFoundException;
import ukikiepas.dzisiajpowtorzylem.security.auth.JwtService;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final JwtService jwtService;

    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUsername")
    public String getUsernameFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Usuń "Bearer " z początku tokenu
            return jwtService.extractUsername(token);
        }
        throw new TokenNotFoundException("Token not provided or invalid");
    }
}
