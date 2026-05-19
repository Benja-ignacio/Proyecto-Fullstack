package cl.duoc.feedback.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Column(name = "user_id", nullable = false)
    @NotNull(message = "El userId es requerido")
    private Long userId;     // referencia externa a user service

    @NotNull(message = "El productId es requerido")
    @Column(name = "product_id", nullable = false)
    private Long productId;  // referencia externa a producto service

    @NotNull(message = "El rating no puede ser nulo")
    @Min(1)
    @Max(5)
    @Column(name = "rating", nullable = false)
    private Integer rating; // 1-5 

    @Column(name = "title", nullable = true)
    private String title; // opcional. puede ser null

    @NotBlank(message = "EL comentario es requerido")
    @Size(min = 2, max = 1000, message = "el mensaje debe tener entre 2 y 1000 caracteres")
    @Column(name = "comment", nullable = false)
    private String comment;  // aquí va todo el2 texto

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true, updatable = true)
    private LocalDateTime updatedAt;


    /* 
    crea automaticamente el timestamp del momento en el que se crea un feedback usando la anotacion
    PrePersist 

    @PrePersist se utiliza para marcar un metodo que debe ejecutarse automaticamente 
    antes de que una entidad se guarde(persista) por primera vez en la base de datos
    */
    @PrePersist 
    public void PrePersist() {
        this.createdAt = LocalDateTime.now();
    }

    /* 
    crea automaticamente el timestamp del momento en el que se actualiza un feedback 
    usando la anotacion PreUpdate 

    @PreUpdate se utiliza para marcar un metodo que debe ejecutarse automaticamente 
    antes de que una entidad se actualice en la base de datos
    */
    @PreUpdate
    public void PreUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
