package project.Kiki_Marti.auth.dto;

import project.Kiki_Marti.user.dto.UserResponse;

public record AuthenticationResponse(String token,
                                     UserResponse user
) {
}
