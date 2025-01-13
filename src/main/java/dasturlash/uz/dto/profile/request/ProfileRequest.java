package dasturlash.uz.dto.profile.request;


import dasturlash.uz.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfileRequest(
        @NotBlank String name,
        @NotBlank String surname,
        @NotNull Role role,
        @NotBlank String username,
        @NotBlank String password
) {}

