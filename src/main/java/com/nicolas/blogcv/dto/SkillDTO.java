package com.nicolas.blogcv.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80)
    private String name;

    private String category;

    @Min(1) @Max(100)
    private Integer level;

    private String iconUrl;

    private Integer displayOrder = 0;
}