package dasturlash.uz.dto.request;

import dasturlash.uz.enums.CompanyRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;


public record CompanyRequest(

        @NotBlank(message = "Name must be provided")
        String name,

        @NotBlank(message = "Address must be provided")
        String address,

        @NotBlank(message = "Contact must be provided")
        String contact,

        CompanyRole role,

        String code,

        @NotBlank(message = "Username must be provided")
        String username,

        @NotBlank(message = "Password must be provided")
        String password

        ) {
}
