package ukikiepas.dzisiajpowtorzylem.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.exception.types.TokenNotFoundException;
import ukikiepas.dzisiajpowtorzylem.user.mappers.UserMapper;
import ukikiepas.dzisiajpowtorzylem.user.models.ChangePasswordRequest;
import ukikiepas.dzisiajpowtorzylem.user.models.Role;
import ukikiepas.dzisiajpowtorzylem.user.models.User;
import ukikiepas.dzisiajpowtorzylem.security.auth.JwtService;
import ukikiepas.dzisiajpowtorzylem.user.models.UserDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;


    public void changePassword(HttpServletRequest request, ChangePasswordRequest changePasswordRequest ) {

        Optional<User> user = userRepository.findByUsername(getUsernameFromToken(request));

        // check if the current password is correct
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.get().getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.get().setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

        // save the new password
        userRepository.save(user.get());
    }

    public void changeUserDetails(HttpServletRequest request, UserDto userDto){
        Optional<User> user = userRepository.findByUsername(getUsernameFromToken(request));
        userMapper.updateUserFromDto(userDto, user.get());
        System.out.println(user);
        userRepository.save(user.get());
    }

    public UserDto getUserData(HttpServletRequest request){
        String username = getUsernameFromToken(request);
        Optional<User> user = userRepository.findByUsername(username);
        return userMapper.userToDto(user.get());
    }

    public Role getRoleFromToken(HttpServletRequest request) {
        String username = getUsernameFromToken(request);
        return userRepository.findByUsername(username)
                .map(User::getRole)
                .orElse(null);
    }

    public boolean setNewUserImage(HttpServletRequest request, String base64Image){
        String username = getUsernameFromToken(request);
        int updatedRows = userRepository.updateImageByUsername(username, base64Image);
        return updatedRows > 0;
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
