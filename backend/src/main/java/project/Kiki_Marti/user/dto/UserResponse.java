package project.Kiki_Marti.user.dto;

public record UserResponse (
        Long id,
        String username,
        String email,
        Long level,
        Long xp,
        Long coins) {
}
