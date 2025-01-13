package dasturlash.uz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CompanyUpdateRequest(
        @NotBlank(message = "Name must be provided")
        String name,

        @NotBlank(message = "Address must be provided")
        String address,

        @NotBlank(message = "Contact must be provided")
        String contact,

        @Pattern(regexp = "^[0-9]{4,}$", message = "Code must be numeric and at least 4 digits long")
        String code
) {}