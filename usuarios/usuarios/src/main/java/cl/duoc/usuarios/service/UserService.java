package cl.duoc.usuarios.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.duoc.usuarios.dto.requests.ChangeStatusRequestDTO;
import cl.duoc.usuarios.dto.responses.UserResponseDTO;
import cl.duoc.usuarios.enums.AccountStatus;
import cl.duoc.usuarios.exception.custom.UserAlreadyInStatusException;
import cl.duoc.usuarios.exception.custom.UserAlreadyInactiveException;
import cl.duoc.usuarios.exception.custom.UserNotFoundException;
import cl.duoc.usuarios.mapper.UserMapper;
import cl.duoc.usuarios.model.User;
import cl.duoc.usuarios.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    // listar todos los usuarios
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper::userEntityToUserResponseDTO)
                .toList();
    }

    // buscar usuario por id
    public UserResponseDTO getById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));

        return mapper.userEntityToUserResponseDTO(user);
    }

    // cambiar estado de una cuenta
    public UserResponseDTO changeAccountStatus(Long userId, ChangeStatusRequestDTO status) {
        log.info("Inciando cambio de estado para usuario {} ", userId);

        User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado."));

        if (user.getStatus() == status.getStatus()) {
            log.warn("Error: el usuario ya se encuentra con AccountStatus {}", status.getStatus());
            throw new UserAlreadyInStatusException("Error: el usuario ya se encuentra con AccountStatus: " + status.getStatus());
        }

        // validar que no sea cambiar estado a inactive

        user.setStatus(status.getStatus());
        userRepository.save(user);

        log.info("Estado de cuenta actualizada para usuario {} a {} ", 
                        user.getUsername(), user.getStatus());

        return mapper.userEntityToUserResponseDTO(user);
    }

    // desactivar una cuenta
    public void deleteUser(Long userId) {

        log.info("Iniciando desactivacion de la cuenta con id: {}", userId);

        User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado."));

        if (user.getStatus() == AccountStatus.INACTIVE) {
            log.warn("El usuario {} ya se encuentra inactivo", user.getUsername());
            throw new UserAlreadyInactiveException("El usuario ya se encuentra inactivo");
        }
        
        user.setStatus(AccountStatus.INACTIVE);
        userRepository.save(user);
        log.info("El usuario con id {} fue desactivado correctamente", userId);
    }

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }
}
