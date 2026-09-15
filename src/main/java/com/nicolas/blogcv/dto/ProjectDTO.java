package com.nicolas.blogcv.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 150)
    private String title;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    private String imageUrl;
    private String repoUrl;
    private String demoUrl;
    private String techStack;

    private Boolean featured = false;

    private String authorName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}