package cl.duoc.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "El username no puede estar vacio")
    @Size(min = 4, max = 32)
    private String username;
}
