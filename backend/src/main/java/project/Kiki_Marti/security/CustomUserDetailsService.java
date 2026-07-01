package project.Kiki_Marti.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.Kiki_Marti.user.entity.UserEntity;
import project.Kiki_Marti.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.loadUserByUsername(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with this email does not exist"));
        return new CustomUserDetails(user);
    }
}
