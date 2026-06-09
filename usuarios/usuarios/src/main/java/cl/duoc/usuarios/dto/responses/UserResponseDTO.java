package cl.duoc.usuarios.dto.responses;

import cl.duoc.usuarios.enums.AccountStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private AccountStatus status; 
}
