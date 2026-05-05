package cl.duoc.feedback.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedbacks")

public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key

    @Column(name = "user_id")
    private Long userId;     // quién comenta FK 

    @Column(name = "product_id")
    private Long productId;  // sobre qué producto fk

    private Integer rating; // 1-5 
    private String title; // opcional. puede ser null
    private String comment;  // aquí va todo el texto
    private LocalDateTime createdAt;
}
