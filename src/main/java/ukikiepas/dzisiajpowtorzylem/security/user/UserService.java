package ukikiepas.dzisiajpowtorzylem.security.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.exception.TokenNotFoundException;
import ukikiepas.dzisiajpowtorzylem.security.user.mappers.UserMapper;
import ukikiepas.dzisiajpowtorzylem.security.user.models.ChangePasswordRequest;
import ukikiepas.dzisiajpowtorzylem.security.user.models.User;
import ukikiepas.dzisiajpowtorzylem.security.auth.JwtService;
import ukikiepas.dzisiajpowtorzylem.security.user.models.UserDto;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;


    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    public UserDto getUserData(HttpServletRequest request){
        String username = getUsernameFromToken(request);
        return userMapper.userToDto(userRepository.findByUsername(username).orElseThrow());
    }

    public String getUsernameFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            return jwtService.extractUsername(token);
        }
        throw new TokenNotFoundException("Token not provided or invalid");
    }
}
