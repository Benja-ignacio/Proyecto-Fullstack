package cl.duoc.usuarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.usuarios.dto.requests.LoginRequestDTO;
import cl.duoc.usuarios.dto.requests.RegisterRequestDTO;
import cl.duoc.usuarios.dto.responses.ApiResponse;
import cl.duoc.usuarios.dto.responses.RegisterResponseDTO;
import cl.duoc.usuarios.model.User;
import cl.duoc.usuarios.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    //ACCESO: PUBLICO
    //endpoint para registrar un usuario
    @PostMapping("/register")
    @Operation(summary = "Registrar un nuevo usuario", description = "Permite registrar un nuevo usuario pidiendo user, password, adress y email")
    public ResponseEntity<ApiResponse<RegisterResponseDTO>> register(
        @Valid @RequestBody RegisterRequestDTO dto) {

        User newUser = authService.registerUser(dto);

        RegisterResponseDTO userDTO = new RegisterResponseDTO(newUser.getUserId(), newUser.getUsername());

        ApiResponse<RegisterResponseDTO> response = new ApiResponse<RegisterResponseDTO>(200, "Usuario registrado", userDTO);
        
        return ResponseEntity.ok(response);
    }

    //ACCESO: PUBLICO
    //endpoint para login 
    @PostMapping("/login")
    @Operation(summary = "Login del usuario", description = "Permite logear a un usuario pidiendo su username y password")
    public ResponseEntity<ApiResponse<String>> login (@Valid @RequestBody LoginRequestDTO loginDTO) {
        String token = authService.login(loginDTO);

        if (token != null) {
            ApiResponse<String> response = 
                    new ApiResponse<String>(200, "Login exitoso", token);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<String> response = 
                    new ApiResponse<String>(401, "Credenciales invalidas", null);
            return ResponseEntity.status(401).body(response);
        }  
    }
}
