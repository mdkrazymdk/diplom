package com.example.researchmonitoring.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter                     //  ← Lombok‑геттеры/сеттеры
@NoArgsConstructor @AllArgsConstructor
public class Comment {

    @Id @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime timestamp;

    @ManyToOne @JoinColumn(name = "work_id")
    private ResearchWork work;

    @ManyToOne @JoinColumn(name = "author_id")
    private User author;
}
