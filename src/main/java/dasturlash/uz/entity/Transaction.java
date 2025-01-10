package dasturlash.uz.entity;

import dasturlash.uz.enums.TransactionStatus;
import dasturlash.uz.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "card_id", length = 36)
    private String cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", insertable = false, updatable = false)
    private Card card;

    @Column(name = "transfer_id", length = 36)
    private String transferId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_id", insertable = false, updatable = false)
    private Transfer transfer;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", length = 50, nullable = false)
    private TransactionType transactionType;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private TransactionStatus status;
}