package cl.duoc.notificaciones.model;

import java.time.LocalDateTime;

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
    private Long userID; // referencia externa a user service

    private String title; // opcional

    private String message;
    private LocalDateTime createdAt;

    // true = leido, false = no leido
    private Boolean read;
}
