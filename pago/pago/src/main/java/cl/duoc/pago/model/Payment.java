package cl.duoc.pago.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.duoc.pago.enums.PaymentMethod;
import cl.duoc.pago.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Payment {
    private Long id;

    @Column(name = "order_id")
    private Long orderId; // referencia a order service

    @Column(name = "user_id")
    private Long userId; // Referencia a user service

    @Column(name = "status")
    private Status status; // crear enum

    @Column(name = "card_last_4")
    private String cardLast4;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "transaction_id")
    private String transactionId;
    
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
