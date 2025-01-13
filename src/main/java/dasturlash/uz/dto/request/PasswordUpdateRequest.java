package dasturlash.uz.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PasswordUpdateRequest(
        @NotBlank(message = "Old password must be provided")
        String oldPassword,

        @NotBlank(message = "New password must be provided")
        String newPassword
) {}
