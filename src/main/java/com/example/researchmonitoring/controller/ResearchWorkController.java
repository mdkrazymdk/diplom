package com.example.researchmonitoring.controller;

import com.example.researchmonitoring.dto.ResearchWorkDto;
import com.example.researchmonitoring.mapper.AppMapper;
import com.example.researchmonitoring.model.ResearchWork;
import com.example.researchmonitoring.model.WorkStatus;
import com.example.researchmonitoring.security.JwtUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.researchmonitoring.service.ResearchWorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/research")       // <— вот сюда новый базовый путь
public class ResearchWorkController {
    private final ResearchWorkService researchWorkService;
    private final AppMapper appMapper;

    @GetMapping
    public List<ResearchWorkDto> getAll() {
        return researchWorkService.findAll()
                .stream().map(appMapper::toDto).toList();
    }

    @PostMapping
    public ResponseEntity<ResearchWorkDto> create(
            @RequestBody @Valid ResearchWorkDto dto,
            @AuthenticationPrincipal JwtUserDetails userDetails     // или просто Principal
    ) {
        var entity = appMapper.toEntity(dto);
        var saved = researchWorkService.create(entity, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(appMapper.toDto(saved));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String,String> body
    ) {
        var newStatus = WorkStatus.valueOf(body.get("status"));
        researchWorkService.updateStatus(id, newStatus);
        return ResponseEntity.ok().build();
    }
}

