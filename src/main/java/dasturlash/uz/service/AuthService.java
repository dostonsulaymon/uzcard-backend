package dasturlash.uz.service;

import dasturlash.uz.dto.JwtResponseDTO;
import dasturlash.uz.enums.Role;
import dasturlash.uz.enums.UserType;
import dasturlash.uz.exceptions.auth_related.UnauthorizedException;
import dasturlash.uz.security.CustomUserDetails;
import dasturlash.uz.security.CustomUserDetailsService;
import dasturlash.uz.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public JwtResponseDTO loginProfile(String username, String password) {
        return authenticate(username, password, UserType.PROFILE);
    }

    public JwtResponseDTO loginCompany(String username, String password) {
        return authenticate(username, password, UserType.COMPANY);
    }

    private JwtResponseDTO authenticate(String username, String password, UserType userType) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authentication.isAuthenticated()) {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

                // Verify user type matches the login endpoint
                if (userDetails.getUserType() != userType) {
                    throw new UnauthorizedException("Invalid login endpoint for user type");
                }

                Role role = userDetails.getRole();

                return new JwtResponseDTO(
                        jwtUtil.encode(username, role.name(), userType.name()),
                        "Bearer",
                        jwtUtil.refreshToken(username, role.name(), userType.name()),
                        username,
                        List.of(role.name())
                );
            }
            throw new UnauthorizedException("Login or password is wrong");
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Login or password is wrong");
        }
    }
}