package cl.duoc.feedback.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FeedbackRequestDTO {
    @NotNull(message = "User ID es requerido")
    private Long userId;

    @NotNull(message = "Product ID es requerido")
    private Long productId;

    @NotNull(message = "Rating es requerido")
    @Min(value = 1, message = "Rating debe ser al menos 1")
    @Max(value = 5, message = "Rating debe ser como máximo 5")
    private Integer rating;

    @NotBlank(message = "el titulo es obligatorio")
    private String title;

    @NotBlank(message = "el comentario es obligatorio")
    private String comment;
    
    
}
