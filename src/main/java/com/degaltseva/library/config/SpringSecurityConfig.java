package com.degaltseva.library.config;

import com.degaltseva.library.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

/**
 * Конфигурационный класс Spring Security
 * Настраивает аутентификацию, авторизацию и безопасность приложения
 * <p>
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/registration", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/registration").permitAll()
                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()

                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/me").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/{id}/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/{id}/delete").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/{id}/edit").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/categories/{id}/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/categories/{id}/delete").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/books/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/books/{id}/edit").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/books/{id}/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/books/{id}/delete").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/categories").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/books").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/books/search").hasAnyRole("USER", "ADMIN")



                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/books", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
