package com.nicolas.blogcv.service;

import com.nicolas.blogcv.dto.ProjectDTO;
import com.nicolas.blogcv.entity.Project;
import com.nicolas.blogcv.entity.User;
import com.nicolas.blogcv.repository.ProjectRepository;
import com.nicolas.blogcv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> findFeatured() {
        return projectRepository.findByFeaturedTrueOrderByCreatedAtDesc().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        return toDTO(project);
    }

    public ProjectDTO create(ProjectDTO dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Project project = toEntity(dto);
        project.setUser(user);

        return toDTO(projectRepository.save(project));
    }

    public ProjectDTO update(Long id, ProjectDTO dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setImageUrl(dto.getImageUrl());
        project.setRepoUrl(dto.getRepoUrl());
        project.setDemoUrl(dto.getDemoUrl());
        project.setTechStack(dto.getTechStack());
        project.setFeatured(dto.getFeatured());

        return toDTO(projectRepository.save(project));
    }

    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        projectRepository.deleteById(id);
    }

    private ProjectDTO toDTO(Project p) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(p.getId());
        dto.setTitle(p.getTitle());
        dto.setDescription(p.getDescription());
        dto.setImageUrl(p.getImageUrl());
        dto.setRepoUrl(p.getRepoUrl());
        dto.setDemoUrl(p.getDemoUrl());
        dto.setTechStack(p.getTechStack());
        dto.setFeatured(p.getFeatured());
        if (p.getUser() != null) {
            dto.setAuthorName(p.getUser().getFullName());
        }
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());
        return dto;
    }

    private Project toEntity(ProjectDTO dto) {
        Project p = new Project();
        p.setTitle(dto.getTitle());
        p.setDescription(dto.getDescription());
        p.setImageUrl(dto.getImageUrl());
        p.setRepoUrl(dto.getRepoUrl());
        p.setDemoUrl(dto.getDemoUrl());
        p.setTechStack(dto.getTechStack());
        p.setFeatured(dto.getFeatured() != null ? dto.getFeatured() : false);
        return p;
    }
}