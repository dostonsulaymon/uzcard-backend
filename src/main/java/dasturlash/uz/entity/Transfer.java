package dasturlash.uz.entity;

import dasturlash.uz.enums.TransferStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "from_card_id", length = 36)
    private String fromCardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_card_id", insertable = false, updatable = false)
    private Card fromCard;

    @Column(name = "to_card_id", length = 36)
    private String toCardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_card_id", insertable = false, updatable = false)
    private Card toCard;

    @Column(name = "total_amount", precision = 19, scale = 2)
    private BigDecimal totalAmount;

    @Column(precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "service_amount", precision = 19, scale = 2)
    private BigDecimal serviceAmount;

    @Column(name = "service_percentage", precision = 19, scale = 2)
    private BigDecimal servicePercentage;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private TransferStatus status;

    @Column(name = "company_id", length = 36)
    private String companyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;
}