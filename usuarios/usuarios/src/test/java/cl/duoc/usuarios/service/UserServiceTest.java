package cl.duoc.usuarios.service;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.usuarios.dto.responses.UserResponseDTO;
import cl.duoc.usuarios.enums.AccountStatus;
import cl.duoc.usuarios.enums.Role;
import cl.duoc.usuarios.mapper.UserMapper;
import cl.duoc.usuarios.model.User;
import cl.duoc.usuarios.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {
    @Test
    void testGetAllUsersDTO() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserMapper mapper = Mockito.mock(UserMapper.class); // ← faltaba este
        UserService userService = new UserService(userRepository, mapper);

        User user = new User(1L, "Benjamin", "Benjamin123", "Benja123@gmail.com", "Duoc uc", Role.CLIENT, AccountStatus.ACTIVE, LocalDateTime.now());
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponseDTO> result = userService.getAllUsers();

        assertThat(result).hasSize(1); // verifica que la lista resultante tenga un tamaño de 1
    }
}
