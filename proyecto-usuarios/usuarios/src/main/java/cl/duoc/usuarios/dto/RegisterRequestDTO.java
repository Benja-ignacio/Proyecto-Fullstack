package cl.duoc.usuarios.dto;

import java.time.LocalDateTime;

public class RegisterRequestDTO {
    public String username;
    public String password;
    public String email;
    public String direccion;
    public LocalDateTime UserCreatedAt;
}
