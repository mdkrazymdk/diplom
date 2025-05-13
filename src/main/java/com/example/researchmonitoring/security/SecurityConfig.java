package com.example.researchmonitoring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1) CORS
        http.cors(c -> {                           // современный стиль
            var source = new UrlBasedCorsConfigurationSource();
            var cfg    = new CorsConfiguration();
            cfg.addAllowedOriginPattern("*");
            cfg.addAllowedMethod("*");
            cfg.addAllowedHeader("*");
            cfg.setAllowCredentials(true);
            source.registerCorsConfiguration("/**", cfg);
            c.configurationSource(source);
        });

        // 2) CSRF выключаем (REST)
        http.csrf(AbstractHttpConfigurer::disable);

        // 3) Stateless‑сессия
        http.sessionManagement(m -> m.sessionCreationPolicy(STATELESS));

        // 4) Авторизация
        http.authorizeHttpRequests(reg -> reg
                .requestMatchers("/api/users/**", "/swagger-ui/**", "/v3/api-docs/**")
                .permitAll()
                .anyRequest().authenticated()
        );

        // 5) JWT‑фильтр
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

