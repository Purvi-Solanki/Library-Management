package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()  // ✅ Enable CORS for frontend requests
            .csrf().disable()  // ✅ Disable CSRF for API access
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ Stateless API
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/books/**").permitAll() // ✅ Allow public access to books
                .requestMatchers("/api/users/register").permitAll() // ✅ Allow user registration
                .requestMatchers("/api/users/getUser").permitAll() // ✅ Allow user retrieval
                .requestMatchers("/api/transactions/**").permitAll() // ✅ Allow all transaction-related endpoints
                .requestMatchers("/api/transactions/**").permitAll()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll() // ✅ Allow H2 Console
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions().disable()); // ✅ Fix for H2 Console access

        return http.build();
    }
}
