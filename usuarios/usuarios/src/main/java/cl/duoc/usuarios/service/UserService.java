package cl.duoc.usuarios.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.duoc.usuarios.dto.LoginRequestDTO;
import cl.duoc.usuarios.dto.RegisterRequestDTO;
import cl.duoc.usuarios.dto.UserDTO;
import cl.duoc.usuarios.enums.Role;
import cl.duoc.usuarios.enums.Status;
import cl.duoc.usuarios.model.User;
import cl.duoc.usuarios.repository.UserRepository;
import cl.duoc.usuarios.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor


public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final JwtUtil jwtUtil;

    // private RegisterRequestDTO toDTO(User user) {
    //     return new RegisterRequestDTO(
    //         user.getUsername(),
    //         user.getPassword(),D
    //         user.getEmail(),
    //         user.getAddress()
    //     );
    // }


    // registrar usuario con contraseña hasheada
    public User registerUser(RegisterRequestDTO request) {

        // validar si existe username registrado
        // if (userRepository.findByUsername(request.getUsername()).isPresent()) {
        //     throw new RuntimeException("error"); // crear personalizacion personalizada - userAlreadyRegistered
        // }

        // // validar si existe email registrado
        // if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        //     throw new RuntimeException("error"); // crear personalizacion personalizada - EmailAlreadyRegistered
        // }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El username ya está registrado");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        // hashear password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        //crear usuario
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setRole(Role.CLIENT);
        user.setStatus(Status.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    // validar login 
    public String login (LoginRequestDTO request ) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isPresent() && passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            return jwtUtil.generateToken(request.getUsername());
        }
        return null;
    } // userOpt.get().getPassword --> PASSWORD HASHEADA



    // validar token (EXPIRATION_TIME)
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }


    // listar todos los usuarios
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getUserId(), user.getUsername()))
                .toList();
    }
}
