package project.Kiki_Marti.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.Kiki_Marti.auth.dto.AuthenticationResponse;
import project.Kiki_Marti.security.CustomUserDetails;
import project.Kiki_Marti.security.JwtService;
import project.Kiki_Marti.user.dto.LoginRequest;
import project.Kiki_Marti.user.dto.RegisterRequest;
import project.Kiki_Marti.user.dto.UserResponse;
import project.Kiki_Marti.user.entity.UserEntity;
import project.Kiki_Marti.user.mapper.UserMapper;
import project.Kiki_Marti.user.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists.");
        }

        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already exists.");
        }

        UserEntity user = new UserEntity();

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        user.setLevel(1);
        user.setXp(0);
        user.setCoins(0);
        user.setEnabled(true);
        user.setLastLogin(LocalDateTime.now());

        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found."));

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        CustomUserDetails userDetails = new CustomUserDetails(user);

        String token = jwtService.generateToken(userDetails);

        UserResponse response = UserMapper.toResponse(user);

        return new AuthenticationResponse(token, response);
    }

}
