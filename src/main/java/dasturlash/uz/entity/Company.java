package dasturlash.uz.entity;

import dasturlash.uz.enums.CompanyRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    private String address;

    private String contact;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private CompanyRole role;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    private Boolean visible;

    private String code;

    @Column(unique = true)
    private String username;

    private String password;
}