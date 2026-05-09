package cl.duoc.notificaciones.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Notifications {

    private Long id; // pk

    @Column(name = "user_id")
    private Long userID; // referencia externa a user service

    @Column(name = "title")
    private String title; // opcional

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "read")
    // true = leido, false = no leido
    private Boolean read;
}
