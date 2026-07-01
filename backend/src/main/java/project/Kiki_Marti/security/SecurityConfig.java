package project.Kiki_Marti.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final CustomUserDetailsService userDetailsService; // Duplicate line, no changes made
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(JwtFilter jwtFilter,
                          CustomUserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {

        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authenticationProvider(authenticationProvider())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()

                        .anyRequest()
                        .authenticated())

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

  
        @Bean
        public DaoAuthenticationProvider authenticationProvider () {

            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

            authenticationProvider.setUserDetailsService(userDetailsService);
            authenticationProvider.setPasswordEncoder(passwordEncoder);

            return authenticationProvider;
        }

    

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
