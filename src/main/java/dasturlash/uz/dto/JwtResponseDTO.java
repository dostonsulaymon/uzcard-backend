package dasturlash.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record JwtResponseDTO(
        String token,
        String type,
        String refreshToken,
        String username,
        List<String> roles
) {
    public JwtResponseDTO {
        if (type == null) {
            type = "Bearer";
        }
    }
}