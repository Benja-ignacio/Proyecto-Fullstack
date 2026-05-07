package cl.duoc.pago.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private Long id;

    private Long orderId; // referencia a order service
    private Long userId; // Referencia a user service

    private String status; // crear enum

    private String cardLast4;
    private String paymentMethod;
    private String transactionId;
    
    private BigDecimal amount;
    private LocalDateTime createdAt;

}
