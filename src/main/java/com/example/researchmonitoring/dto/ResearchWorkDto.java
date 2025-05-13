package com.example.researchmonitoring.dto;

import com.example.researchmonitoring.model.WorkStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Научная работа")
public class ResearchWorkDto {
    private Long id;
    private String title;
    private String description;
    private WorkStatus status;
    private Integer grade;
}

