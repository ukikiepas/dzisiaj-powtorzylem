package ukikiepas.dzisiajpowtorzylem.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ukikiepas.dzisiajpowtorzylem.user.models.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String city;
    private String parentEmail;
    private String bio;
    private Role role;
    private boolean isPublicAccount;

}
