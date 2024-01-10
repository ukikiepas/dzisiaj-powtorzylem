package ukikiepas.dzisiajpowtorzylem.user.models;


import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String city;
    private String bio;
    private LocalDate creationDate;
    private Role role;
    private Boolean isPublicAccount;
    private String image;
    private String parentEmail;
    private Boolean isParentNotified;

}
