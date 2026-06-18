package cl.duoc.usuarios.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import cl.duoc.usuarios.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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

    @Column(nullable = false, name = "password")
    @NotBlank(message = "La contraseña no puede estar vacia")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9]).{6,64}$", 
            message = "contraseña invalida. debe contener almenos 8 caracteres y maximo 264, una mayuscula y un numero")
    private String password;

    @Column(unique = true, nullable = false, name = "user_email")
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email invalido")
    @Size(min = 6, max = 100, message = "El email no puede superar los 100 caracteres")
    private String email;


    @Column(nullable = false, name = "address")
    @NotBlank(message = "La direccion no puede estar vacia")
    @Size(min = 6, max = 264)
    private String address;

    @Column(nullable = false, unique = false, name = "user_rol")
    @NotNull(message = "El rol no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "account_status", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "El status no puede ser nulo")
    private AccountStatus status;

    @CreatedDate
    @Column(name= "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
        public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
