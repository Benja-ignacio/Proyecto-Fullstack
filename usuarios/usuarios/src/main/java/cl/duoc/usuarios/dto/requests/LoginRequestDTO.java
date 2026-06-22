package cl.duoc.usuarios.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "El username no puede estar vacio")
    @Size(min = 4, max = 16, message = "El username debe tener entre 4 y 16 caracteres")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 6, max = 64, message = "La contraseña debe tener entre 6 y 64 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[0-9]).{6,64}$",
            message = "Contraseña invalida. Debe contener al menos 6 caracteres, una mayuscula y un numero"
    )
    private String password;
}