package cl.duoc.usuarios.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.usuarios.dto.requests.ChangeStatusRequestDTO;
import cl.duoc.usuarios.dto.responses.ApiResponse;
import cl.duoc.usuarios.dto.responses.UserResponseDTO;
import cl.duoc.usuarios.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ADMIN
    //endpoint para listar todos los usuarios 
    @GetMapping("/list")
    @Operation(summary = "Listar Usuarios", description = "Permite Listar todos los usuarios")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> list = userService.getAllUsers();

        ApiResponse<List<UserResponseDTO>> response = 
                new ApiResponse<List<UserResponseDTO>>(400, "Lista de usuarios", list);
        
        return ResponseEntity.ok(response);
    }   
    
    //ACCESO: ADMIN
    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por id", description = "Permite buscar un usuario por id")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getById(
        @PathVariable Long userId) {

        UserResponseDTO data = userService.getById(userId);

        return ResponseEntity.ok(new ApiResponse<>(200, "Usuario encontrado", data));
    }

    //ACCESO: ADMIN
    @PatchMapping("/status/{userId}") 
    @Operation(summary = "Cambiar el estado de la cuenta de un usuario", description = "Permite cambiar el estado de una cuenta. Requiere el id del usuario y el estado")
    public ResponseEntity<ApiResponse<UserResponseDTO>> changeAccountStatus(
        @PathVariable Long userId,
        @Valid @RequestBody ChangeStatusRequestDTO status) {

            UserResponseDTO data = userService.changeAccountStatus(userId, status);

            return ResponseEntity.ok(new ApiResponse<>(200, "Estado de la cuenta de usuario cambiado", data));
            
    }

    //ACCESO: USUARIO AUTENTICADO 
    @DeleteMapping("/{userId}")
    @Operation(summary = "Cambia el estado de la cuenta de un usuario a inactivo", description = "Permite desactivar la cuenta de un usuario")
    public ResponseEntity<Void> desactivateAccount(
        @PathVariable Long userId,
                      Authentication authentication) {

            Long authenticatedUserId = Long.parseLong((String) authentication.getPrincipal());

            userService.deleteUser(userId, authenticatedUserId);

            return ResponseEntity.noContent().build();
        }
    
    // ACCESO ADMIN
    @GetMapping("/exists/{userId}")
    @Operation(summary = "Verifica si un usuario existe", description = "Permite verificar si un usuario existe por su id. devuelve true si existe, falso sino")
    public ResponseEntity<Boolean> existsById(
        @PathVariable Long userId) {
        boolean exists = userService.existsById(userId);
        return ResponseEntity.ok(exists);
    }
    
    
}
