package cl.duoc.usuarios.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.usuarios.dto.responses.UserResponseDTO;
import cl.duoc.usuarios.model.User;

@Component
public class UserMapper {
    public UserResponseDTO userEntityToUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .status(user.getStatus())
                .build();
    }
}
