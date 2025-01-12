package dasturlash.uz.security;

import dasturlash.uz.enums.Role;
import dasturlash.uz.enums.UserType;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
public class CustomUserDetails implements UserDetails {
    private String id;
    private String username;
    private String password;
    private UserType userType; // COMPANY or PROFILE
    private Role role; // ADMIN, BANK, PAYMENT, MODERATOR

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));

        System.out.println("Role from enum: " + role.name());
        return authorities;
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
}