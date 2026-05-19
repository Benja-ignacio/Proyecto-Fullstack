package cl.duoc.notificaciones.dto;

import cl.duoc.notificaciones.enums.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDTO {

    @NotNull(message = "EL userID es requerido")
    private Long userId;

    @NotBlank(message = "El titulo no puede estar vacio")
    @Size(min = 6, max = 40)
    private String title;

    @NotBlank(message = "El titulo no puede estar vacio")
    @Size(min = 6, max = 500)
    private String message;

    @NotNull(message = "El tipo no puede ser nulo")
    private Type type;
}
