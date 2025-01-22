package dasturlash.uz.dto.client;

import dasturlash.uz.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientRequestDTO(
        @NotBlank(message = "Name must be provided")
        String name,

        @NotBlank(message = "Surname must be provided")
        String surname,

        @NotBlank(message = "MiddleName must be provided")
        String middleName,

        @NotBlank(message = "phoneNumber must be provided")
        String phoneNumber,

        @Pattern(regexp = "^[A-Z]{2}$", message = "Passport seria must be exactly two uppercase letters.")
        String passportSeria,

        @Pattern(regexp = "^[0-9]{6}$", message = "Passport number must be exactly six digits.")
        String passportNumber,

        @NotBlank(message = "Address must be provided")
        String address
) {


}
