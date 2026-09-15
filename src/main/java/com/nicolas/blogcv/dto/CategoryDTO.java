package com.nicolas.blogcv.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "El slug es obligatorio")
    @Size(max = 50)
    private String slug;

    private String description;
}