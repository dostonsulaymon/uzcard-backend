package dasturlash.uz.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangeUsernameRequest(
        @NotBlank String newUsername
) {}
