package com.example.researchmonitoring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Комментарий к работе")
public class CommentDto {

    private Long id;            // в ответах

    @NotBlank
    private String text;

    private String timestamp;   // ISO‑строка

    /* --- ссылки --- */
    private Long workId;        // обязательно!
    private Long authorId;
}
