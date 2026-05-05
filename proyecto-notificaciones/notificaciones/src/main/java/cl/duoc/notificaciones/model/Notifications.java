package cl.duoc.notificaciones.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notificacions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Notifications {

    private Long id; // PRIMARY KEY
    private Long userID; // fk

    private String title; // opcional
    private String message;
    private LocalDateTime createdAt;
    private Boolean read;
}
