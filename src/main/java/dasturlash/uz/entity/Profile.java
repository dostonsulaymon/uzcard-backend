package dasturlash.uz.entity;

import dasturlash.uz.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String username;

    private String password;

    private Boolean status;

    private LocalDateTime createdDate;
}
