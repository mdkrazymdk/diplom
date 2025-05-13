package com.example.researchmonitoring.service;

import com.example.researchmonitoring.dto.UserDto;
import com.example.researchmonitoring.mapper.AppMapper;
import com.example.researchmonitoring.model.User;
import com.example.researchmonitoring.repository.UserRepository;
import com.example.researchmonitoring.security.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository   repo;
    private final PasswordEncoder  encoder;
    private final AppMapper        mapper;
    private final JwtUtil          jwtUtil;

    /* ---------- регистрация ---------- */
    public UserDto register(UserDto dto) {

        // маппим DTO → доменную сущность
        User u = mapper.toUser(dto);

        // шифруем пароль
        u.setPassword(encoder.encode(u.getPassword()));

        // сохраняем в БД и возвращаем DTO‑ответ
        return mapper.toUserDto(repo.save(u));
    }

    /* ---------- логин: проверка пароля и выдача JWT ---------- */
    public String login(String username, String rawPassword) {

        User user = repo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User"));

        if (!encoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        // генерируем и отдаём токен
        return jwtUtil.generate(username);
    }

    /* ---------- получить доменную модель по username ---------- */
    public User findDomainByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
