package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "clients",
        uniqueConstraints = @UniqueConstraint(columnNames = {"passport_seria", "passport_number"})
)
public class Client {
    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "passport_seria", nullable = false, length = 10)
    private String passportSeria;

    @Column(name = "passport_number", nullable = false, length = 10)
    private String passportNum;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(nullable = false)
    private Boolean status;

    private Boolean visible;

}
