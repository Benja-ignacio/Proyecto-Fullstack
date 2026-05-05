package cl.duoc.feedback.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "feedbacks")

public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Column(name = "user_id")
    private Long userId;     // referencia externa a user service

    @Column(name = "product_id")
    private Long productId;  // referencia externa a producto service

    @Min(1)
    @Max(5)
    private Integer rating; // 1-5 

    private String title; // opcional. puede ser null

    private String comment;  // aquí va todo el2 texto
    private LocalDateTime createdAt;
}
