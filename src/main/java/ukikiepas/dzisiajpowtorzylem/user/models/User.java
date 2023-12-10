package ukikiepas.dzisiajpowtorzylem.user.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_user_id_seq", allocationSize = 1)
    private Long userId;
    @NotNull //TODO ogarnac notblank
    @Column(name = "first_name")
    private String firstname;
    @NotNull
    @Column(name = "last_name")
    private String lastname;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String city;
    private String bio;
    private boolean enabled;
    private boolean nonLocked;
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;
    @NotNull
    private Boolean isPublicAccount;
    private String image;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_vocabulary",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private Set<Vocabulary> favoriteWords;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
