package dasturlash.uz.controller;

import dasturlash.uz.dto.JwtResponseDTO;
import dasturlash.uz.dto.LoginDTO;
import dasturlash.uz.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/profile/login")
    public ResponseEntity<JwtResponseDTO> login(
            @RequestBody @Valid LoginDTO loginDTO
    ) {
        return ResponseEntity.ok(authService.loginProfile(loginDTO.login(), loginDTO.password()));
    }

    @PostMapping("/company/login")
    public ResponseEntity<JwtResponseDTO> loginForCompany(
            @RequestBody @Valid LoginDTO loginDTO
    ) {
        return ResponseEntity.ok(authService.loginCompany(loginDTO.login(), loginDTO.password()));
    }
}
