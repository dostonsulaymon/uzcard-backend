package dasturlash.uz.dto.request;

import dasturlash.uz.enums.Role;

import java.time.LocalDateTime;

public record CompanyResponse(
        String id,
        String name,
        String address,
        String contact,
        Role role,
        String code,
        String username,
        LocalDateTime createdDate
) {
}
