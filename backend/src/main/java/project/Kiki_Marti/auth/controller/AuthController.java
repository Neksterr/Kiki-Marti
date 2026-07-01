package project.Kiki_Marti.auth.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Kiki_Marti.auth.dto.AuthenticationResponse;
import project.Kiki_Marti.auth.service.AuthService;
import project.Kiki_Marti.user.dto.LoginRequest;
import project.Kiki_Marti.user.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthenticationResponse response =
                authService.login(request);

        return ResponseEntity.ok(response);
    }
}
