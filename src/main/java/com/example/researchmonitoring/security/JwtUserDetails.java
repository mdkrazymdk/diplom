package com.example.researchmonitoring.security;

import com.example.researchmonitoring.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Простейшая обёртка, которая кладётся в SecurityContext после валидации токена.
 */
@AllArgsConstructor
@Getter
public class JwtUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final String role;

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return List.of(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    /* ---------- вспомогательное ---------- */
    public static JwtUserDetails fromUser(User u) {
        return new JwtUserDetails(
                u.getId(),
                u.getUsername(),
                u.getPassword(),
                u.getRole().name()
        );
    }
}
