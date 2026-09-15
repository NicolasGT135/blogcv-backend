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
public class PostDTO {

    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200)
    private String title;

    @NotBlank(message = "El slug es obligatorio")
    @Size(max = 200)
    private String slug;

    private String excerpt;

    @NotBlank(message = "El contenido es obligatorio")
    private String content;

    private String coverImageUrl;

    private Boolean published = false;

    private Long categoryId;

    private String categoryName;

    private String authorName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
}