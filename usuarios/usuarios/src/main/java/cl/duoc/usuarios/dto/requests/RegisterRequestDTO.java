package cl.duoc.usuarios.dto.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotBlank(message = "El username no puede estar vacio")
    @Size(min = 4, max = 16)
    private String username;

    
    @NotBlank(message = "La contraseña no puede estar vacia")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9]).{6,64}$", 
    message = "contraseña invalida. debe contener almenos 6 caracteres y maximo 64, una mayuscula y un numero")
    private String password;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email invalido")
    @Size(min = 6, max = 100, message = "El email no puede superar los 100 caracteres")
    private String email;

    @NotBlank(message = "La direccion no puede estar vacia")
    @Size(min = 6, max = 255)
    private String address;
}
