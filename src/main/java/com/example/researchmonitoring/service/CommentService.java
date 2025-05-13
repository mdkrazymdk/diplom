package com.example.researchmonitoring.service;
import com.example.researchmonitoring.model.ResearchWork;
import com.example.researchmonitoring.model.User;
import org.springframework.transaction.annotation.Transactional;
import com.example.researchmonitoring.dto.CommentDto;
import com.example.researchmonitoring.mapper.AppMapper;
import com.example.researchmonitoring.model.Comment;
import com.example.researchmonitoring.repository.CommentRepository;
import com.example.researchmonitoring.repository.ResearchWorkRepository;
import com.example.researchmonitoring.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository      commentRepo;
    private final ResearchWorkRepository workRepo;
    private final UserRepository         userRepo;     // <-- было userRepository
    private final AppMapper              mapper;

    /* ----------- получить список ------------- */
    @Transactional(readOnly = true)
    public List<CommentDto> list(Long workId) {
        ResearchWork work = workRepo.findById(workId)
                .orElseThrow(() -> new EntityNotFoundException("Work not found"));
        return commentRepo.findByWork(work)
                .stream()
                .map(mapper::toCommentDto)
                .toList();
    }

    /* ----------- добавить комментарий -------- */
    public CommentDto add(CommentDto dto, String username) {

        User author = userRepo.findByUsername(username)   // <-- userRepo
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        ResearchWork work = workRepo.findById(dto.getWorkId())
                .orElseThrow(() -> new EntityNotFoundException("Work not found"));

        Comment c = mapper.dtoToComment(dto);
        c.setAuthor(author);
        c.setWork(work);
        c.setTimestamp(LocalDateTime.now());

        return mapper.toCommentDto(commentRepo.save(c));
    }
}
