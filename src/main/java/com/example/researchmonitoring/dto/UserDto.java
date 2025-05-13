package com.example.researchmonitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
@Schema(description = "Пользователь (DTO)")
public class UserDto {


    @Schema(description = "ID пользователя", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    @Schema(description = "Логин", example = "student123")
    private String username;

    @NotBlank
    @Schema(description = "Пароль", example = "secret")
    private String password;

    @Schema(description = "Роль", example = "STUDENT")
    private String role;
}
