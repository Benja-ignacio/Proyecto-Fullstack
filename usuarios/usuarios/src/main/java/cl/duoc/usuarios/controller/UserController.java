package cl.duoc.usuarios.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.usuarios.dto.ApiResponse;
import cl.duoc.usuarios.dto.LoginRequestDTO;
import cl.duoc.usuarios.dto.RegisterRequestDTO;
import cl.duoc.usuarios.dto.UserDTO;
import cl.duoc.usuarios.model.User;
import cl.duoc.usuarios.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService UserService;

    //endpoint para registrar usuario
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@Valid @RequestBody RegisterRequestDTO dto) {
        User newUser = UserService.registerUser(dto);

        UserDTO userDTO = new UserDTO(newUser.getUserId(), newUser.getUsername());


        ApiResponse<UserDTO> response = new ApiResponse<UserDTO>(200, "Usuario registrado", userDTO);
        
        return ResponseEntity.ok(response);
    }

    //endpoint para login 
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login (@Valid @RequestBody LoginRequestDTO loginDTO) {
        String token = UserService.login(loginDTO);

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

    //endpoint para listar todos los usuarios 
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> list = UserService.getAllUsers();

        ApiResponse<List<UserDTO>> response = 
                new ApiResponse<List<UserDTO>>(400, "Lista de usuarios", list);
        
        return ResponseEntity.ok(response);
    }

    //endpoint para validar token 
    @GetMapping("/validate")
    public ResponseEntity<ApiResponse<String>> validateToken (@RequestParam String token) {
        boolean valid = UserService.validateToken(token);

        if (valid) {
            ApiResponse<String> response = 
                    new ApiResponse<String>(200, "Token valido", "OK");
            
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<String> response =
                    new ApiResponse<String>(401, "Token invalido", null);

            return ResponseEntity.status(401).body(response);
        }
    }
    
    
    
    
}
