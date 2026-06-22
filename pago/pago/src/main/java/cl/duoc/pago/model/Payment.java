package cl.duoc.pago.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.duoc.pago.enums.PaymentMethod;
import cl.duoc.pago.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId; // referencia a order service

    @Column(name = "user_id", nullable = false)
    private Long userId; // Referencia a user service

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status; // crear enum

    @Column(name = "card_last_4", nullable = true) 
    private String cardLast4;

    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "transaction_id")
    private String transactionId;
    
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /* @PrePersist se utiliza para marcar un metodo que debe ejecutarse automaticamente 
    antes de que una entidad se guarde(persista) por primera vez en la base de datos
    */
    @PrePersist 
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
