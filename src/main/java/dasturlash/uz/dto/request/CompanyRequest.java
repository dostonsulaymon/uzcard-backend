package dasturlash.uz.dto.request;

import dasturlash.uz.enums.CompanyRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyRequest {

    @NotBlank(message = "Name must be provided")
    private String name;

    @NotBlank(message = "Address must be provided")
    private String address;

    @NotBlank(message = "Contact must be provided")
    private String contact;

    private CompanyRole role;

    private String code;

    @NotBlank(message = "Username must be provided")
    private String username;

    @NotBlank(message = "Password must be provided")
    private String password;


}
