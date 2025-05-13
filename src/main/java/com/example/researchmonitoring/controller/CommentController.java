// src/main/java/com/example/researchmonitoring/controller/CommentController.java
package com.example.researchmonitoring.controller;

import com.example.researchmonitoring.dto.CommentDto;
import com.example.researchmonitoring.mapper.AppMapper;
import com.example.researchmonitoring.security.JwtUserDetails;
import com.example.researchmonitoring.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")    // <— свой корректный путь для комментов
public class CommentController {
    private final CommentService commentService;
    private final AppMapper mapper;

    @GetMapping("/{workId}")
    public List<CommentDto> list(@PathVariable Long workId) {
        return commentService.list(workId);
    }

    @PostMapping
    public CommentDto add(
            @RequestBody @Valid CommentDto dto,
            @AuthenticationPrincipal JwtUserDetails userDetails
    ) {
        return commentService.add(dto, userDetails.getUsername());
    }
}
