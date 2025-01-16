package dasturlash.uz.dto.profile.request;

import dasturlash.uz.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfileRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Surname is required")
        String surname,

        @NotNull(message = "Role is required")
        Role role,

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password
) {}
