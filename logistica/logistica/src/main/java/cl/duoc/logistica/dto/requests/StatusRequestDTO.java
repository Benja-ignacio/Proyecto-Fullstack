package cl.duoc.logistica.dto.requests;

import cl.duoc.logistica.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticStatusRequestDTO {
    @NotNull(message = "El status no puede ser nulo")
    private Status status;
}
