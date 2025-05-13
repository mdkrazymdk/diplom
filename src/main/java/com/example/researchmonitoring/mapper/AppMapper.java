package com.example.researchmonitoring.mapper;

import com.example.researchmonitoring.dto.CommentDto;
import com.example.researchmonitoring.dto.ResearchWorkDto;
import com.example.researchmonitoring.dto.UserDto;
import com.example.researchmonitoring.model.Comment;
import com.example.researchmonitoring.model.ResearchWork;
import com.example.researchmonitoring.model.User;
import org.springframework.stereotype.Component;
import com.example.researchmonitoring.dto.*;
import com.example.researchmonitoring.model.*;
import org.springframework.stereotype.Component;
/** Ручной маппер DTO ⇄ Entity */
@Component
public class AppMapper {

    /* ---------- Work ---------- */
    public ResearchWorkDto toDto(ResearchWork work) {
        if (work == null) return null;
        ResearchWorkDto dto = new ResearchWorkDto();
        dto.setId(work.getId());
        dto.setTitle(work.getTitle());
        dto.setDescription(work.getDescription());
        dto.setStatus(work.getStatus().name());
        if (work.getStudent() != null)    dto.setStudentId(work.getStudent().getId());
        if (work.getSupervisor() != null) dto.setSupervisorId(work.getSupervisor().getId());
        return dto;
    }

    public ResearchWork toEntity(ResearchWorkDto dto) {
        if (dto == null) return null;
        ResearchWork w = new ResearchWork();
        w.setTitle(dto.getTitle());
        w.setDescription(dto.getDescription());
        return w;
    }

    /* ---------- Comment (для CommentService.list) ---------- */
    public CommentDto toCommentDto(Comment comment) {
        if (comment == null) return null;
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setTimestamp(comment.getTimestamp().toString()); // или форматируйте как нужно
        dto.setWorkId(comment.getWork().getId());
        dto.setAuthorId(comment.getAuthor().getId());
        return dto;
    }

    public Comment dtoToComment(CommentDto dto) {
        if (dto == null) return null;
        Comment c = new Comment();
        c.setText(dto.getText());
        // timestamp и связи (work, author) вы заполните уже в сервисе,
        // т.к. нужно подгрузить сущности из БД
        return c;
    }

    /* ---------- User (для регистрации) ---------- */
    public User toUser(UserDto dto) {
        if (dto == null) return null;
        User u = new User();
        u.setUsername(dto.getUsername());
        u.setPassword(dto.getPassword());

        // ❶ Конвертируем строку → enum Role
        u.setRole(Role.valueOf(dto.getRole()));   // <‑‑ исправляет “String → Role”
        return u;
    }

    public UserDto toUserDto(User u) {
        if (u == null) return null;
        UserDto dto = new UserDto();

        // ❷ setId() теперь есть
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());

        // ❸ enum → строка
        dto.setRole(u.getRole().name());
        return dto;
    }
}
