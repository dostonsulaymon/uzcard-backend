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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String address;

    private String contact;

    @Enumerated(EnumType.STRING)
    private CompanyRole role;

    private LocalDateTime createdDate;

    private Boolean visible;

    private String code;

    @Column(name = "username", unique = true)
    private String username;

    private String password;


}
