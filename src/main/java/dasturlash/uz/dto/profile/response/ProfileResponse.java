package dasturlash.uz.dto.profile.response;


import dasturlash.uz.enums.Role;

import java.time.LocalDateTime;

public record ProfileResponse(
        String id,
        String name,
        String surname,
        Role role,
        String username,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        Boolean visible
) {}
