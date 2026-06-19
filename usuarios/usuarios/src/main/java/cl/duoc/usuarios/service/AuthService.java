package cl.duoc.usuarios.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.duoc.usuarios.dto.requests.LoginRequestDTO;
import cl.duoc.usuarios.dto.requests.RegisterRequestDTO;
import cl.duoc.usuarios.enums.AccountStatus;
import cl.duoc.usuarios.enums.Role;
import cl.duoc.usuarios.exception.custom.EmailAlreadyUsedException;
import cl.duoc.usuarios.exception.custom.UserAlreadyExistsException;
import cl.duoc.usuarios.exception.custom.UserNotFoundException;
import cl.duoc.usuarios.model.User;
import cl.duoc.usuarios.repository.UserRepository;
import cl.duoc.usuarios.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;


    // registrar usuario con contraseña hasheada
    public User registerUser(RegisterRequestDTO request) {

        log.info("Iniciando registro de usuario: {} con email: {}", request.getUsername(), request.getEmail());

        // validar si existe username registrado
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            
            log.warn("Intento de registro con username existente: {}", request.getUsername());

            throw new UserAlreadyExistsException("error: Usuario con nombre " + request.getUsername() + " ya existe"); 
        };

        // validar si existe email registrado
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            log.warn("Intento de registro con email existente: {}", request.getEmail());

            throw new EmailAlreadyUsedException("error: El email " + request.getEmail() + " ya esta registrado"); // crear personalizacion personalizada - EmailAlreadyRegistered
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
        user.setStatus(AccountStatus.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
        
        log.info("Usuario creado. id={}, username={}, email={}",
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );


        return user;
    }

    // validar login 
    public String login (LoginRequestDTO request ) {

        log.info("Iniciando Login de usuario: {}", request.getUsername());

        User user = userRepository.findByUsername(request.getUsername()).
                    orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            log.info("Login exitoso: {}", request.getUsername() );

            return jwtUtil.generateToken(user.getUserId(), user.getUsername(), user.getRole());
        }

        log.warn("Error: Login no valido.");
        return null;
    } 



}
