// src/main/java/com/example/researchmonitoring/config/AppBeans.java
package com.example.researchmonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppBeans {

    @Bean                           // 👉 теперь Spring найдёт его
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
