package cl.duoc.usuarios.dto.requests;

import cl.duoc.usuarios.enums.AccountStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeStatusRequestDTO {
    @NotNull(message = "EL estado de la cuenta no puede ser nulo")
    private AccountStatus status;
}
