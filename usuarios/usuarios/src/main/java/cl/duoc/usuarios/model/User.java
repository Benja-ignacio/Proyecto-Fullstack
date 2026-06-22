package cl.duoc.usuarios.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import cl.duoc.usuarios.enums.AccountStatus;
import cl.duoc.usuarios.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "El username no puede estar vacio")
    @Size(min = 4, max = 16)
    @Column(nullable = false, name = "username")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Column(nullable = false, name = "password")
    private String password;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email invalido")
    @Size(min = 6, max = 100, message = "El email no puede superar los 100 caracteres")
    @Column(unique = true, nullable = false, name = "user_email")
    private String email;

    @NotBlank(message = "La direccion no puede estar vacia")
    @Size(min = 6, max = 264)
    @Column(nullable = false, name = "address")
    private String address;

    @NotNull(message = "El rol no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "user_rol")
    private Role role;

    @NotNull(message = "El status no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus status;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}