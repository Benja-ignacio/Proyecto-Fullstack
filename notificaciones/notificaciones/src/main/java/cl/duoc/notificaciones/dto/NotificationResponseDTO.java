package cl.duoc.notificaciones.dto;

import java.time.LocalDateTime;

import cl.duoc.notificaciones.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponseDTO {

    private Long id; // PK
    private Long userId; // Referencia externa al microservicio users
    private String title;
    private String message;
    private Type type;
    private LocalDateTime createdAt;
}
