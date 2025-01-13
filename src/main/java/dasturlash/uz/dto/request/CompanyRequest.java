package dasturlash.uz.dto.request;

import dasturlash.uz.enums.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public record CompanyRequest(

        @NotBlank(message = "Name must be provided")
        String name,

        @NotBlank(message = "Address must be provided")
        String address,

        @NotBlank(message = "Contact must be provided")
        String contact,

        Role role,

        @Min(4)
        String code,

        @NotBlank(message = "Username must be provided")
        String username,

        @NotBlank(message = "Password must be provided")
        String password

        ) {
}
