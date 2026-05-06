package cl.duoc.usuarios.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.duoc.usuarios.dto.LoginRequestDTO;
import cl.duoc.usuarios.dto.RegisterRequestDTO;
import cl.duoc.usuarios.enums.Role;
import cl.duoc.usuarios.enums.Status;
import cl.duoc.usuarios.model.User;
import cl.duoc.usuarios.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // registrar usuario con contraseña encriptada
    public User registerUser(RegisterRequestDTO request) {

        // validar username 
        if (userRepository.findByUsername(request.username).isPresent()) {
            throw new RuntimeException("error"); // crear personalizacion personalizada
        }

        // validar email
        if (userRepository.findByEmail(request.email).isPresent()) {
            throw new RuntimeException("error"); // crear personalizacion personalizada
        }

        // encriptar password
        String encondedPassword = passwordEncoder.encode(request.password);

        //crear usuario
        User user = new User();

        user.setUsername(request.username);
        user.setPassword(encondedPassword);
        user.setEmail(request.email);
        user.setAddress(request.address);
        user.setRole(Role.CLIENT);
        user.setStatus(Status.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }


    // validar login 
    public boolean login (LoginRequestDTO request ) {
        Optional<User> userOpt = userRepository.findByUsername(request.username);
        return userOpt.isPresent() && passwordEncoder.matches(request.password, userOpt.get().getPassword());
    }
}
