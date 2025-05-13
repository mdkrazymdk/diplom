package com.example.researchmonitoring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class ResearchWork {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User student;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private WorkStatus status = WorkStatus.DRAFT;

    private Integer grade;          // 0–100, null пока нет оценки
    private String filePath;        // uploads/…
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

