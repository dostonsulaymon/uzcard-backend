package dasturlash.uz.dto.company.request;

import dasturlash.uz.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record CompanyRequest(

        @NotBlank(message = "Name must be provided")
        String name,

        @NotBlank(message = "Address must be provided")
        String address,

        @NotBlank(message = "Contact must be provided")
        String contact,

        Role role,

        @Pattern(regexp = "^[0-9]{4,}$", message = "Code must be numeric and at least 4 digits long")
        String code,

        @NotBlank(message = "Username must be provided")
        String username,

        @NotBlank(message = "Password must be provided")
        String password

) {
}
