package cl.duoc.usuarios.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cl.duoc.usuarios.enums.AccountStatus;
import cl.duoc.usuarios.enums.Role;
import cl.duoc.usuarios.model.User;
import cl.duoc.usuarios.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // evitar duplicados
        if (userRepository.findByUsername("admin01").isPresent()) {
            return;
        }

        User admin = new User();
        admin.setUsername("admin01");
        admin.setPassword(passwordEncoder.encode("Admin123"));
        admin.setEmail("admin@test.com");
        admin.setAddress("System Init");
        admin.setRole(Role.ADMIN);
        admin.setStatus(AccountStatus.ACTIVE);
        admin.setCreatedAt(LocalDateTime.now());

        userRepository.save(admin);

        System.out.println("✔ Admin inicial creado");
    }
}