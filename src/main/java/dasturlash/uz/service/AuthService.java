package dasturlash.uz.service;

import dasturlash.uz.dto.JwtResponseDTO;
import dasturlash.uz.exceptions.UnauthorizedException;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.security.CustomUserDetails;
import dasturlash.uz.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;


    public JwtResponseDTO login(String username, String password) {


        profileRepository.findByUsernameAndVisibleTrue(username)
                .orElseThrow(() -> new UnauthorizedException("Login or password is wrong"));


        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authentication.isAuthenticated()) {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

                return new JwtResponseDTO(
                        jwtUtil.encode(username, userDetails.getRole().toString()),
                        "Bearer",
                        jwtUtil.refreshToken(username, userDetails.getRole().toString()),
                        username,
                        List.of(userDetails.getRole().toString())
                );
            }
            throw new UnauthorizedException("Login or password is wrong");
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Login or password is wrong");

        }
    }
}
