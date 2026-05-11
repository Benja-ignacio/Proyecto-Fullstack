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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

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
    @Column(name = "rating")
    private Integer rating; // 1-5 

    @Column(name = "title")
    private String title; // opcional. puede ser null

    @Column(name = "comment")
    private String comment;  // aquí va todo el2 texto

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
