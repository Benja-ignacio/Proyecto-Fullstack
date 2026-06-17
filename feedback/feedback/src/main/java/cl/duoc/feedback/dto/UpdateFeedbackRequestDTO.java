package cl.duoc.feedback.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateFeedbackRequestDTO {
    @NotNull(message = "Rating es requerido")
    @Min(value = 1, message = "Rating debe ser al menos 1")
    @Max(value = 5, message = "Rating debe ser como máximo 5")
    private Integer rating;

    private String title;

    @NotBlank(message = "el comentario es obligatorio")
    @Size(min = 2, max = 1000, message = "el mensaje debe tener entre 2 y 1000 caracteres")
    private String comment;
}
