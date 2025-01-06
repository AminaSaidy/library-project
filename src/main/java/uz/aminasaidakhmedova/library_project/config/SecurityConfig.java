package uz.aminasaidakhmedova.library_project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import uz.aminasaidakhmedova.library_project.model.User;
import uz.aminasaidakhmedova.library_project.service.UserService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/book").hasRole("USER")
                                .requestMatchers("/book/v2").hasRole("ADMIN")
                                .requestMatchers("/books").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userService.getUserByUsername(username);
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        };
    }
}

