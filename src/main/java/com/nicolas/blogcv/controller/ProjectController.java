package com.nicolas.blogcv.controller;

import com.nicolas.blogcv.dto.ProjectDTO;
import com.nicolas.blogcv.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<ProjectDTO> getAll() {
        return projectService.findAll();
    }

    @GetMapping("/featured")
    public List<ProjectDTO> getFeatured() {
        return projectService.findFeatured();
    }

    @GetMapping("/{id}")
    public ProjectDTO getById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    public ProjectDTO create(@Valid @RequestBody ProjectDTO dto,
                             @RequestParam(defaultValue = "1") Long userId) {
        return projectService.create(dto, userId);
    }

    @PutMapping("/{id}")
    public ProjectDTO update(@PathVariable Long id, @Valid @RequestBody ProjectDTO dto) {
        return projectService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}