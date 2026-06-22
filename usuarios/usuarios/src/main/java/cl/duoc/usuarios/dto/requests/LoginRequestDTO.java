package cl.duoc.usuarios.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "El username no puede estar vacio")
    @Size(min = 4, max = 16)
    private String username;

<<<<<<< HEAD
<<<<<<< HEAD:usuarios/usuarios/src/main/java/cl/duoc/usuarios/dto/LoginRequestDTO.java
     @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 6, max = 64)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9]).{8,264}$", 
=======
    @NotBlank(message = "La contraseña no puede estar vacia")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9]).{6,264}$", 
>>>>>>> benja:usuarios/usuarios/src/main/java/cl/duoc/usuarios/dto/requests/LoginRequestDTO.java
=======
    @NotBlank(message = "La contraseña no puede estar vacia")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9]).{6,264}$", 
>>>>>>> eliascarcamo
    message = "contraseña invalida. debe contener almenos 8 caracteres y maximo 264, una mayuscula y un numero")
    private String password;
}
